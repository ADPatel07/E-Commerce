package com.ecommarce.services.seller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommarce.entity.ProductCategory;
import com.ecommarce.entity.ProductInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ProductsCRUDInterface {

	public ProductInfo addProduct(String productInfo, MultipartFile[] productImages) throws JsonMappingException, JsonProcessingException, IOException;
	
	public ProductInfo updateProduct(ProductInfo productInfo);

	public ProductInfo deleteProduct(String ProductId);
	
	public ProductInfo getProductById(String productId);
	
	public List<ProductInfo> getAllProduct();
	
	public void addProductCetagory(ProductCategory productCategory);
}
