package com.ecommarce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllProductsDTO {

	private String productId;
	
	private String productName;
		
	private int productOriginalPrice;

	private int productSellingPrice;
	
	private String productImages;
	
	private String productCategoryName;
	
	private String productColor;
	
}
