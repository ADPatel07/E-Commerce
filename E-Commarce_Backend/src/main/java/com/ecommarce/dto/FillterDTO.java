package com.ecommarce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillterDTO {

	private String productName;
	
	private int minPrice;
	
	private int maxPrice;
	
	private Boolean isAscending;
	
	private String productCategory;
	
	
}
