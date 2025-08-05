package com.ecommarce.services.buyer;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommarce.dto.OrderInfoResponseDTO;
import com.ecommarce.dto.OrderInformationDTO;
import com.ecommarce.dto.OrderPaymentDTO;
import com.ecommarce.entity.DeliveryAddress;
import com.ecommarce.entity.DeliveryInformation;
import com.ecommarce.entity.OrderDetail;
import com.ecommarce.entity.OrderProductInfo;
import com.ecommarce.entity.PaymentDetail;
import com.ecommarce.entity.ProductInfo;
import com.ecommarce.entity.user.UserInfo;
import com.ecommarce.enums.DeliveryStatus;
import com.ecommarce.enums.PaymentStatus;
import com.ecommarce.repository.DeliveryAddressRepository;
import com.ecommarce.repository.DeliveryInfoRepository;
import com.ecommarce.repository.OrderDetailsRepository;
import com.ecommarce.repository.OrderProductInfoRepository;
import com.ecommarce.repository.PaymentDetailRepository;
import com.ecommarce.repository.ProductInfoRepository;
import com.ecommarce.repository.ProductsCategoryRepository;
import com.ecommarce.repository.user.UserRepository;
import com.ecommarce.services.user.CommonServices;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class BuyerServicesImpl implements BuyerServicesInterface {

	@Autowired
	DeliveryInfoRepository deliveryInfoRepositor;

	@Autowired
	ProductInfoRepository productInfoRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	DeliveryAddressRepository deliveryAddressRepository;

	@Autowired
	PaymentDetailRepository pymentDetailRepository;

	@Autowired
	OrderProductInfoRepository orderProductInfoRepository;

	@Autowired
	ProductsCategoryRepository productsCategoryRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CommonServices commonServices;

	private static final String KEY = "rzp_test_LMBVJmznJvyN2j";
	private static final String SECRET_KEY = "ktjNrkZLS8turkXr6H2Aa1x2";

	@Override
	public OrderDetail order(OrderInformationDTO orderInformationDTO) {

		Iterable<ProductInfo> products = productInfoRepository.findAllById(orderInformationDTO.getProducts().keySet());

		Set<OrderProductInfo> orderProductInfos = new HashSet<>();

		products.forEach(product -> {

			int quantity = orderInformationDTO.getProducts().get(product.getProductId());

			OrderProductInfo orderProductDTO = new OrderProductInfo();

			orderProductDTO.setProductId(product.getProductId());
			orderProductDTO.setProductQuantity(quantity);
			orderProductDTO.setProductPrice(product.getProductSellingPrice());

			orderProductInfos.add(orderProductDTO);

			product.setProductQuantity(product.getProductQuantity() - quantity);

		});
		
		orderProductInfoRepository.saveAll(orderProductInfos);
		
		OrderDetail orderDetail = new OrderDetail();

		DeliveryInformation deliveryInformation = new DeliveryInformation();
		BeanUtils.copyProperties(orderInformationDTO, deliveryInformation);
		DeliveryAddress deliveryAddress = deliveryInformation.getAddress();
		deliveryInformation.setAddress(deliveryAddress);
		
		orderDetail.setDeliveryInformation(deliveryInformation);
		orderDetail.setPaymentStatus(PaymentStatus.SUCCESS);
		orderDetail.setDeliveryStatus(DeliveryStatus.INTRANSIT);
		
		orderDetail.setOrderProductInfos(orderProductInfos);
		
		orderDetail.setCreatedDate(new Date());

		PaymentDetail paymentDetail = orderInformationDTO.getPaymentDetail();
		paymentDetail = pymentDetailRepository.save(paymentDetail);
		orderDetail.setPaymentDetail(paymentDetail);

		orderDetail = orderDetailsRepository.save(orderDetail);

		productInfoRepository.saveAll(products);

		UserInfo userInfo = commonServices.getCurrentUser();
		
		if (userInfo != null) {
			
			if (userInfo.getOrders() == null)
				userInfo.setOrders(new HashSet<>());

			userInfo.getOrders().add(orderDetail);
			
//			if (userInfo.getDeliveryInformation() == null)
//				userInfo.setDeliveryInformation(new HashSet<>());
//			
//			deliveryInformation = deliveryInfoRepositor.save(deliveryInformation);
//			deliveryAddress = deliveryAddressRepository.save(deliveryAddress);
//			deliveryInformation.setAddress(deliveryAddress);
//			userInfo.getDeliveryInformation().add(orderDetail.getDeliveryInformation());
		
			userRepository.save(userInfo);
		}

		return orderDetail;
	}

	@Override
	public OrderPaymentDTO getPaymentFromRazorPay(HashMap<String, Integer> orderedProducts) throws RazorpayException {
		OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
		orderPaymentDTO.setAmount(0);

		List<ProductInfo> products = productInfoRepository.findAllById(orderedProducts.keySet());

		products.forEach(product -> {
			int quantity = orderedProducts.get(product.getProductId());
			product.setProductQuantity(product.getProductQuantity() - quantity);

			int amount = product.getProductSellingPrice() * quantity;
			orderPaymentDTO.setAmount(orderPaymentDTO.getAmount() + amount);
		});

		productInfoRepository.saveAll(products);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", orderPaymentDTO.getAmount() * 100);
		jsonObject.put("currency", "INR");

		RazorpayClient razorpayClient = new RazorpayClient(KEY, SECRET_KEY);
		Order order = razorpayClient.orders.create(jsonObject);

		orderPaymentDTO.setAmount(order.get("amount"));
		orderPaymentDTO.setCurrency(order.get("currency"));
		orderPaymentDTO.setPaymentId(order.get("id"));
		orderPaymentDTO.setKey(KEY);

		return orderPaymentDTO;
	}

	@Override
	public OrderInfoResponseDTO getOrderInformation(String orderId) {

		OrderInfoResponseDTO orderInfoResponseDTO = new OrderInfoResponseDTO();

		OrderDetail orderDetail = orderDetailsRepository.findById(orderId).orElse(null);

		if (orderDetail == null)
			return null;

		orderDetail.getOrderProductInfos().forEach(product -> {
			orderInfoResponseDTO.getProducts().put(product.getProductId(), product.getProductQuantity());
		});

		BeanUtils.copyProperties(orderDetail, orderInfoResponseDTO);
		BeanUtils.copyProperties(orderDetail.getDeliveryInformation(), orderInfoResponseDTO.getDeliveryInfo());
		BeanUtils.copyProperties(orderDetail.getDeliveryInformation().getAddress(), orderInfoResponseDTO.getDeliveryInfo());

		return orderInfoResponseDTO;
	}

	@Override
	public List<String> getAllProductCetegories() {
		return productsCategoryRepository.findAll().stream().map(category -> {
			return category.getProductCategoryName();
		}).collect(Collectors.toList());
	}

}
