package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommarce.entity.ProductImageInfo;

public interface ProductImageInfoRepository extends JpaRepository<ProductImageInfo, String> {

}
