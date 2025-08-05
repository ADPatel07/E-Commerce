package com.ecommarce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

	SELLER("SELLER"), BUYER("BUYER");
	
	private String role;
	
}
