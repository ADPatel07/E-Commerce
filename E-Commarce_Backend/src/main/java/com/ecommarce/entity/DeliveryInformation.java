package com.ecommarce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DeliveryInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String deliveryId;
	
	private String firstName;
     
    private String lastName;
      
    private String mobileNumber1;
 
    private String mobileNumber2;
 
    private String emailAddress;
  
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DeliveryAddress address = new DeliveryAddress();

}
