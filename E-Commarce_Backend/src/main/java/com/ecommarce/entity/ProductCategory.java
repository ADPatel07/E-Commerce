package com.ecommarce.entity;

import jakarta.persistence.Column;
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
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String productCategoryId;
	
	@Column(unique = true)
	private String productCategoryName;
	
}
