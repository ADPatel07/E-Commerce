package com.ecommarce.services.products;

import java.util.List;
import java.util.Set;

import com.ecommarce.dto.AllProductsDTO;
import com.ecommarce.dto.FillterDTO;
import com.ecommarce.dto.ProductInfoDTO;
import com.ecommarce.entity.ProductImageInfo;

public interface ProductsServicesInterface {

	public Set<AllProductsDTO> getAllProducts();
	
	public ProductInfoDTO getProductById(String productId); 
	
	public ProductImageInfo getProductimage(String imageId);
	
	public List<AllProductsDTO> getFillteredProducts(FillterDTO fillterDTO);
	
}
