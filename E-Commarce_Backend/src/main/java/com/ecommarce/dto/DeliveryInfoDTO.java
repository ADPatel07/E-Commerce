package com.ecommarce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfoDTO {

	private String deliveryId;
	
	private String firstName;
    
    private String lastName;
      
    private String mobileNumber1;
 
    private String mobileNumber2;
 
    private String emailAddress;
  
    private String address1;
    
    private String address2;
      
    private String state;
         
    private String district;
     
    private String city;
        
    private int pinCode;
	
}
