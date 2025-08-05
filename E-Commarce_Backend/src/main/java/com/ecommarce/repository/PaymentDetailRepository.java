package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, String> {

}
