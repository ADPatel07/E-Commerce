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
public class PaymentDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String paymentId;
	
	private String razorpay_order_id;
	
	private String razorpay_payment_id;

	private String razorpay_signature;

	private int amount;

}

