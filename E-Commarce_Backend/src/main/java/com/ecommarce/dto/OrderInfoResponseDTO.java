package com.ecommarce.dto;

import java.util.Date;
import java.util.HashMap;

import com.ecommarce.enums.DeliveryStatus;
import com.ecommarce.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResponseDTO {

	private String orderId;
	
	private HashMap<String, Integer> products = new HashMap<>();
	
	private DeliveryInfoDTO deliveryInfo = new DeliveryInfoDTO();

	private Date createdDate;
	
	private PaymentStatus paymentStatus;
	
	private DeliveryStatus deliveryStatus;
	
}
