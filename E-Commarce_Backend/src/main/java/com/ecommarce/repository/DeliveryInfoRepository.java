package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.DeliveryInformation;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInformation, String> {
	
	DeliveryInformation deleteByDeliveryId(String id);

}
