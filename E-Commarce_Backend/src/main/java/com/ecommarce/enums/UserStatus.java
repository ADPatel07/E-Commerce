package com.ecommarce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
	
	VERIFIED("VERIFIED"), PANDING("PANDING");
	
	private String status;

}
