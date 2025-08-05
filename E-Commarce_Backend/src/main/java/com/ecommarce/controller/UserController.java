package com.ecommarce.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommarce.dto.user.AddUserDTO;
import com.ecommarce.dto.user.UpdateUserDTO;
import com.ecommarce.dto.user.UserAuthDTO;
import com.ecommarce.dto.user.UserDTO;
import com.ecommarce.entity.DeliveryInformation;
import com.ecommarce.services.user.UserServicesInterface;

@Controller
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserServicesInterface userServicesInterface;
	
	@PostMapping("attempt/login")
	protected ResponseEntity<?> addUser(@RequestBody AddUserDTO addUserDTO){
		userServicesInterface.addUser(addUserDTO);
		return ResponseEntity.ok("Verification Code Generated Successfully");
	}
	
	@PostMapping("get/token")
	protected ResponseEntity<?> generateToken(@RequestBody UserAuthDTO userAuthDTO){
		return ResponseEntity.ok(userServicesInterface.generateToken(userAuthDTO));
	}
	
//	@GetMapping("get")
//	protected ResponseEntity<?> getUser(){
//		return ResponseEntity.ok(userServicesInterface.getUser());
//	}
	
	@PostMapping("update")
	protected ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) throws IllegalArgumentException, IllegalAccessException{
		return ResponseEntity.ok(userServicesInterface.updateUser(updateUserDTO));
	}
	
	@PostMapping("deliveryInfo/update")
	protected ResponseEntity<?> updateDeliveryInfo(@RequestBody DeliveryInformation deliveryInformation) throws IllegalArgumentException, IllegalAccessException{
		return ResponseEntity.ok(userServicesInterface.updateDeliveryInfo(deliveryInformation));
	}
	
	@DeleteMapping("deliveryInfo/delete/{deliveryInformationId}")
	protected ResponseEntity<?> deleteDeliveryInfo(@PathVariable String deliveryInformationId) throws IllegalArgumentException, IllegalAccessException{
		userServicesInterface.deleteDeliveryInfo(deliveryInformationId);
		return ResponseEntity.ok("Deleted Sussfully");
	}
	
	@PostMapping("addToCart")
	protected ResponseEntity<?> addToCart(@RequestBody HashMap<String, Integer> cart){
		userServicesInterface.addToCart(cart);
		return ResponseEntity.ok("Added to Cart");
	}
}

