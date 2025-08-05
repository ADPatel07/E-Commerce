package com.ecommarce.dto;

import java.util.HashMap;

import com.ecommarce.entity.DeliveryAddress;
import com.ecommarce.entity.PaymentDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInformationDTO {
	
	private HashMap<String, Integer> products;
	
	private String firstName;
    
    private String lastName;
      
    private String mobileNumber1;
 
    private String mobileNumber2;
 
    private String emailAddress;
  
    private DeliveryAddress address;
    
    private PaymentDetail paymentDetail;
	
}
