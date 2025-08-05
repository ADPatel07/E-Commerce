package com.ecommarce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommarce.entity.ProductCategory;

@Repository
public interface ProductsCategoryRepository extends JpaRepository<ProductCategory, String> {
	
	ProductCategory findByProductCategoryName(String productCategoryName);

}
