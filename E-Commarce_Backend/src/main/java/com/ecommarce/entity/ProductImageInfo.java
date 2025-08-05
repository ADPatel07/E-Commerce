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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductImageInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String originalFilename;

	private String contentType;

	private long size;

    @Column(length=1000000)
	private byte[] bytes;

}
