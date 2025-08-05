package com.ecommarce.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDTO {

	private String productId;
	
	private String productName;
	
	private String productDescription;
	
	private int productOriginalPrice;

	private int productSellingPrice;
	
	private String productCategoryName;
		
	private Set<String> productImages;
	
	private String productColor;
	
}
