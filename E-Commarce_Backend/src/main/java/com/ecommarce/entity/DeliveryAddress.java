package com.ecommarce.entity;

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
public class DeliveryAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String addressId;
	
    private String address1;
    
    private String address2;
      
    private String state;
         
    private String district;
     
    private String city;
        
    private int pinCode;
       
	
}
