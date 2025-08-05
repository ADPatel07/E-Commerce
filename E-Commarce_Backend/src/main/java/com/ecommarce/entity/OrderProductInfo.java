package com.ecommarce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderProductInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String orderProductInfoId;
	
	private String productId;
	
	private int productQuantity;
	
	private int productPrice;
	
}
