package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.OrderProductInfo;

public interface OrderProductInfoRepository extends JpaRepository<OrderProductInfo, String> {

}
