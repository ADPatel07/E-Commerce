package com.ecommarce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {

	INTRANSIT("INTRANSIT") , OUTFORDELIVERY("OUTFORDELIVERY"), DELIVERED("DELIVERED");
	
	private String status;
}

