package com.ecommarce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ecommarce.dto.user.UserDTO;
import com.ecommarce.services.user.UserServicesInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@CrossOrigin("*")
public class GraphQLControler {
	
	@Autowired
	UserServicesInterface userServicesInterface;

	@QueryMapping
	public UserDTO getUser() throws JsonProcessingException{
		
		UserDTO userDTO = userServicesInterface.getUser();
		ObjectMapper objectMapper = new ObjectMapper();
		userDTO.setCartItem(objectMapper.writeValueAsString(userDTO.getCart()));
		userDTO.setOrdersString(objectMapper.writeValueAsString(userDTO.getOrders()));
		userDTO.setOrders(null);
		userDTO.setCart(null);
		
		return  userDTO;
	}
	
	
}
