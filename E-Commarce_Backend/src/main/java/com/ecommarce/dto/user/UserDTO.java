package com.ecommarce.dto.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.ecommarce.dto.DeliveryInfoDTO;
import com.ecommarce.dto.OrderInfoResponseDTO;
import com.ecommarce.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String userName;
		
	private String email;
	
	private String mobile;
	
	private UserRole userRole;
	
	private Set<DeliveryInfoDTO> deliveryInformation = new HashSet<>();

	private HashMap<String, Integer> cart;
	
	private String cartItem;
	
	private Set<OrderInfoResponseDTO> orders = new HashSet<>(); 
	
	private String ordersString;
	
	private String extra;
	
}
