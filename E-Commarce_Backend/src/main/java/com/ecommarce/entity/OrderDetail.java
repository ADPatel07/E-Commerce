package com.ecommarce.entity;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.ecommarce.enums.DeliveryStatus;
import com.ecommarce.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String orderId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderProductInfo> OrderProductInfos;
	
	@JdbcTypeCode(SqlTypes.JSON)
	private DeliveryInformation deliveryInformation;

	private Date createdDate;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentDetail paymentDetail;
	
}
