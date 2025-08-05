package com.ecommarce.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String productId;
	
	private String productName;
	
	private String productDescription;
	
	private int productOriginalPrice;

	private int productSellingPrice;
	
	private int productQuantity;
	
	private String productColor;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	@JoinColumn(name = "productCategory")
	private ProductCategory productCategory;
	
	private Date createdDate = new Date();
	
	private Date lastModifiedDate;
	
	private Set<String> productImages = new HashSet<String>();

}
