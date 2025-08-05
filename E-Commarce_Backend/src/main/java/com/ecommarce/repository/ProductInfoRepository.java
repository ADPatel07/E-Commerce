package com.ecommarce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommarce.entity.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
 
	@Query(nativeQuery = true, value = "SELECT * FROM product_info p "
			+ "inner join product_category c on p.product_category = c.product_category_id"
			+ " where p.product_selling_price between :minPrice and :maxPrice"
			+ " And p.product_name like %:name%"
			+ " and c.product_category_name like %:productCategory"
			+ " order by p.product_selling_price asc")
	List<ProductInfo> fillterASC(int minPrice, int maxPrice, String name, String productCategory);
	
	@Query(nativeQuery = true, value = "SELECT * FROM product_info p "
			+ "inner join product_category c on p.product_category = c.product_category_id"
			+ " where p.product_selling_price between :minPrice and :maxPrice"
			+ " And p.product_name like %:name%"
			+ " and c.product_category_name like %:productCategory"
			+ " order by p.product_selling_price desc")
	List<ProductInfo> fillterDESC(int minPrice, int maxPrice, String name, String productCategory);
	

	@Query(nativeQuery = true, value = "SELECT * FROM product_info p "
			+ "inner join product_category c on p.product_category = c.product_category_id"
			+ " where p.product_selling_price between :minPrice and :maxPrice"
			+ " And p.product_name like %:name%"
			+ " and c.product_category_name like %:productCategory")
	List<ProductInfo> fillter(int minPrice, int maxPrice, String name, String productCategory);
	
}
