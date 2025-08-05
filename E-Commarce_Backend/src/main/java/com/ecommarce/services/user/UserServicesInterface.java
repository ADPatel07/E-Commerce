package com.ecommarce.services.user;

import java.util.HashMap;

import com.ecommarce.dto.user.AddUserDTO;
import com.ecommarce.dto.user.UpdateUserDTO;
import com.ecommarce.dto.user.UserAuthDTO;
import com.ecommarce.dto.user.UserDTO;
import com.ecommarce.entity.DeliveryInformation;

public interface UserServicesInterface {
	
	public void addUser(AddUserDTO addUser);
		
	public String generateToken(UserAuthDTO userAuthDTO);
	
	public UserDTO getUser();
	
	public UserDTO updateUser(UpdateUserDTO updateUserDTO) throws IllegalArgumentException, IllegalAccessException;
	
	public UserDTO updateDeliveryInfo(DeliveryInformation deliveryInformation) throws IllegalArgumentException, IllegalAccessException;  
	
	public void addToCart(HashMap<String, Integer> cart);    
	
	public void deleteDeliveryInfo(String deliveryInfoId);

}
