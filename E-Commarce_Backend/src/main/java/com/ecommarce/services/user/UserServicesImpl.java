package com.ecommarce.services.user;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Service;

import com.ecommarce.dto.DeliveryInfoDTO;
import com.ecommarce.dto.OrderInfoResponseDTO;
import com.ecommarce.dto.user.AddUserDTO;
import com.ecommarce.dto.user.UpdateUserDTO;
import com.ecommarce.dto.user.UserAuthDTO;
import com.ecommarce.dto.user.UserDTO;
import com.ecommarce.entity.DeliveryInformation;
import com.ecommarce.entity.user.UserInfo;
import com.ecommarce.enums.UserRole;
import com.ecommarce.enums.UserStatus;
import com.ecommarce.repository.DeliveryInfoRepository;
import com.ecommarce.repository.ProductInfoRepository;
import com.ecommarce.repository.user.UserRepository;
import com.ecommarce.security.jwt.JwtService;
import com.ecommarce.services.buyer.BuyerServicesInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServicesImpl implements UserServicesInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private DeliveryInfoRepository deliveryInfoRepository;

	@Autowired
	private CommonServices commonServices;
	
	@Autowired
	private BuyerServicesInterface buyerServicesInterface;

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Override
	public void addUser(AddUserDTO addUser) {

		String userName = addUser.getEmail().toString().split("@")[0];

		UserInfo user = userRepository.findByEmail(addUser.getEmail()).orElse(new UserInfo());

		if (user.getUserInfoId() == null) {
			user.setEmail(addUser.getEmail());
			user.setUserName(userName);
			user.setCreatedDate(new Date());
			user.setUserRole(UserRole.BUYER);
			user.setStatus(UserStatus.PANDING);
		}

		int otp = commonServices.generateOTP(addUser.getEmail());

		user.setOtp(otp);
		user.setOtpAttempt(0);
		user.setOptGenerationDate(new Date());
		user = userRepository.save(user);
	}

	@Override
	public String generateToken(UserAuthDTO userAuthDTO) {

//		String userName = userAuthDTO.getEmail().toString().split("@")[0];
		UserInfo userInfo = userRepository.findByEmail(userAuthDTO.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		String userName = userInfo.getUserName();

		try {

			if (userInfo.getOtpAttempt() > 2)
				throw new NonceExpiredException("Too Many Attempts try to Resend OTP");

			if (Duration.between(userInfo.getOptGenerationDate().toInstant(), new Date().toInstant()).toSeconds() > 60)
				throw new NonceExpiredException("Otp Expired try to Resend OTP");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userAuthDTO.getOtp()));
			if (userInfo.getStatus() == UserStatus.PANDING) {
				userInfo.setStatus(UserStatus.VERIFIED);
				userRepository.save(userInfo);
			}

			return jwtService.generateToken(userInfo);

		} catch (BadCredentialsException e) {
			userInfo.setOtpAttempt(userInfo.getOtpAttempt() + 1);
			userRepository.save(userInfo);
			throw e;
		}
	}

	@Override
	public UserDTO getUser() {

		UserInfo userInfo = commonServices.getCurrentUser();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userInfo, userDTO);
		
		userDTO.setCart(userInfo.getCart());

		userInfo.getDeliveryInformation().forEach(deliverInfo -> {
			DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
			BeanUtils.copyProperties(deliverInfo, deliveryInfoDTO);
			BeanUtils.copyProperties(deliverInfo.getAddress(), deliveryInfoDTO);
			userDTO.getDeliveryInformation().add(deliveryInfoDTO);
		});

		userInfo.getOrders().forEach(order -> {
			OrderInfoResponseDTO orderInfoResponseDTO = buyerServicesInterface.getOrderInformation(order.getOrderId());
			userDTO.getOrders().add(orderInfoResponseDTO);
		});

		return userDTO;
	}

	@Override
	public UserDTO updateUser(UpdateUserDTO updateUserDTO) throws IllegalArgumentException, IllegalAccessException {

		UserInfo userInfo = commonServices.getCurrentUser();

		BeanUtils.copyProperties(updateUserDTO, userInfo);
		
		userInfo = userRepository.save(userInfo);

		UserDTO responseUserDTO = new UserDTO();
		BeanUtils.copyProperties(userInfo, responseUserDTO);
		userInfo.getDeliveryInformation().forEach(deleveryinfo -> {
			DeliveryInfoDTO deliveryInfoDTO2 = new DeliveryInfoDTO();
			BeanUtils.copyProperties(deleveryinfo, deliveryInfoDTO2);
			BeanUtils.copyProperties(deleveryinfo.getAddress(), deliveryInfoDTO2);
			responseUserDTO.getDeliveryInformation().add(deliveryInfoDTO2);
		});
		
		responseUserDTO.setExtra(jwtService.generateToken(userInfo));

		return responseUserDTO;
	}

	@Override
	public UserDTO updateDeliveryInfo(DeliveryInformation deliveryInformation)
			throws IllegalArgumentException, IllegalAccessException {

		UserInfo userInfo = commonServices.getCurrentUser();
		UserDTO responseUserDTO = new UserDTO();
		
		boolean isIdNull = deliveryInformation.getDeliveryId() == null;

		if (deliveryInformation != null) {

			deliveryInformation = deliveryInfoRepository.save(deliveryInformation);

			if(isIdNull) {				
				userInfo.getDeliveryInformation().add(deliveryInformation);
				userInfo = userRepository.save(userInfo);
			}

		}

		BeanUtils.copyProperties(userInfo, responseUserDTO);
		userInfo.getDeliveryInformation().forEach(deleveryinfo -> {
			DeliveryInfoDTO deliveryInfoDTO2 = new DeliveryInfoDTO();
			BeanUtils.copyProperties(deleveryinfo, deliveryInfoDTO2);
			BeanUtils.copyProperties(deleveryinfo.getAddress(), deliveryInfoDTO2);
			responseUserDTO.getDeliveryInformation().add(deliveryInfoDTO2);
		});
		
		return responseUserDTO;
	}

	@Override
	public void addToCart(HashMap<String, Integer> cart) {

		UserInfo userInfo = commonServices.getCurrentUser();
		userInfo.setCart(new HashMap<>());
		
		cart.forEach((key, value) -> {			
			if(productInfoRepository.existsById(key))
				userInfo.getCart().put(key, value);
		});
		
		try {
			userRepository.save(userInfo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Your Cart is Full Please delete Unnessecarry Product ");
		}
		
	}

	@Override
	public void deleteDeliveryInfo(String deliveryInfoId) {
		
		UserInfo userInfo = commonServices.getCurrentUser();
		
		if(userInfo.hasDeliveryInformation(deliveryInfoId)) {
				
				userInfo.getDeliveryInformation().remove(deliveryInfoRepository.findById(deliveryInfoId).get());
				userRepository.save(userInfo);
				deliveryInfoRepository.deleteById(deliveryInfoId);
		}
		else {
			throw new EntityNotFoundException("Delivery Information Not Found");
		}
		
		
	}

}
