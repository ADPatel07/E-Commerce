package com.ecommarce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

	SUCCESS("SUCCESS"), PANDING("PANDING"), CANCELED("CANCELED");
	
	private String status;
	
}