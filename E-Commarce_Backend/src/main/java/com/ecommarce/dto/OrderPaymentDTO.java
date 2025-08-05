package com.ecommarce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentDTO {

	private String orderId;
	
	private String paymentId;
	
	private Integer amount;
	
	private String currency;
	
	private String key;
	
}
