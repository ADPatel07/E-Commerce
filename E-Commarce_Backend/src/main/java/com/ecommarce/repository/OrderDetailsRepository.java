package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.OrderDetail;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, String> {

}
