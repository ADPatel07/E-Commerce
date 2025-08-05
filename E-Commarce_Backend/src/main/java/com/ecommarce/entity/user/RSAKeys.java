package com.ecommarce.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RSAKeys {
	
	@Id
	private int id;
	
	@Column(length = 100000)
	private byte[] publicKey;
	
	@Column(length = 100000)
	private byte[] privateKey;

}
