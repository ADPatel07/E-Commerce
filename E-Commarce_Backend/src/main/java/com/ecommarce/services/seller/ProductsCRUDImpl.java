package com.ecommarce.services.seller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommarce.entity.ProductCategory;
import com.ecommarce.entity.ProductImageInfo;
import com.ecommarce.entity.ProductInfo;
import com.ecommarce.repository.ProductImageInfoRepository;
import com.ecommarce.repository.ProductInfoRepository;
import com.ecommarce.repository.ProductsCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductsCRUDImpl implements ProductsCRUDInterface {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Autowired
	private ProductImageInfoRepository productImageInfoRepository;
	
	@Autowired
	private ProductsCategoryRepository productsCategoryRepository;
	
	@Override
	public ProductInfo addProduct(String productInformation, MultipartFile[] productImages) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(productInformation);
		ProductInfo productInfo = mapper.readValue(productInformation, ProductInfo.class);
		
		
		productInfo.setProductCategory(productsCategoryRepository.findByProductCategoryName(productInfo.getProductCategory().getProductCategoryName()));
		
		for (MultipartFile img : productImages) {
			ProductImageInfo productImageInfo = new ProductImageInfo();
			
			BeanUtils.copyProperties(img, productImageInfo);
			
			productImageInfo = productImageInfoRepository.save(productImageInfo);
			productInfo.getProductImages().add(productImageInfo.getId());
		}
				
		return productInfoRepository.save(productInfo);
		
	}

	@Override
	public ProductInfo updateProduct(ProductInfo productInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductInfo deleteProduct(String productId) {

		ProductInfo productInfo = productInfoRepository.findById(productId).get();
		productInfo.getProductImages().forEach(id -> {
			productImageInfoRepository.deleteById(id);
		});
		
		productInfoRepository.deleteById(productId);
		
		return productInfo;
	}

	@Override
	public ProductInfo getProductById(String productId) {
		return productInfoRepository.findById(productId).orElse(null);
	}

	@Override
	public List<ProductInfo> getAllProduct() {
		return productInfoRepository.findAll();
	}

	@Override
	public void addProductCetagory(ProductCategory productCategory) {
		productsCategoryRepository.save(productCategory);
	}

}
