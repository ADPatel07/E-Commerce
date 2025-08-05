package com.ecommarce.services.products;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommarce.dto.AllProductsDTO;
import com.ecommarce.dto.FillterDTO;
import com.ecommarce.dto.ProductInfoDTO;
import com.ecommarce.entity.ProductImageInfo;
import com.ecommarce.entity.ProductInfo;
import com.ecommarce.repository.ProductImageInfoRepository;
import com.ecommarce.repository.ProductInfoRepository;

@Service
public class ProductsServicesImpl implements ProductsServicesInterface {

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Autowired
	private ProductImageInfoRepository productImageInfoRepository;
	
	@Override
	public Set<AllProductsDTO> getAllProducts() {
		
		Set<AllProductsDTO> productList = new HashSet<>();

		productInfoRepository.findAll().forEach((entity) -> {
			AllProductsDTO informationDTO = new AllProductsDTO();
			BeanUtils.copyProperties(entity, informationDTO);
			BeanUtils.copyProperties(entity.getProductCategory(), informationDTO);
			informationDTO.setProductImages(entity.getProductImages().iterator().next());
			productList.add(informationDTO);
		});

		return productList;
		
	}

	@Override
	public ProductInfoDTO getProductById(String productId) {
		
		ProductInfoDTO productInformationDTO = new ProductInfoDTO();

		ProductInfo productInformation = productInfoRepository.findById(productId).orElse(null);

		BeanUtils.copyProperties(productInformation, productInformationDTO);
		BeanUtils.copyProperties(productInformation.getProductCategory(), productInformationDTO);

		return productInformationDTO;
	}

	@Override
	public ProductImageInfo getProductimage(String imageId) {
		return productImageInfoRepository.findById(imageId).get();
	}

	@Override
	public List<AllProductsDTO> getFillteredProducts(FillterDTO fillterDTO) {
		
		List<AllProductsDTO> allProductsDTOs = new ArrayList<>();
		
		if(fillterDTO.getMaxPrice() == 0) 
			fillterDTO.setMaxPrice(100000000);
		
		if(fillterDTO.getIsAscending() == null) {
			productInfoRepository.fillter(fillterDTO.getMinPrice(),fillterDTO.getMaxPrice(),fillterDTO.getProductName(), fillterDTO.getProductCategory()).forEach(product -> {
				AllProductsDTO dto = new AllProductsDTO();
				BeanUtils.copyProperties(product, dto);
				dto.setProductImages(product.getProductImages().iterator().next());
				allProductsDTOs.add(dto);
			});
		}
		
		else if(fillterDTO.getIsAscending()) {			
			productInfoRepository.fillterASC(fillterDTO.getMinPrice(),fillterDTO.getMaxPrice(),fillterDTO.getProductName(), fillterDTO.getProductCategory()).forEach(product -> {
				AllProductsDTO dto = new AllProductsDTO();
				BeanUtils.copyProperties(product, dto);
				dto.setProductImages(product.getProductImages().iterator().next());
				allProductsDTOs.add(dto);
			});
		}
		else {
			productInfoRepository.fillterDESC(fillterDTO.getMinPrice(),fillterDTO.getMaxPrice(),fillterDTO.getProductName(), fillterDTO.getProductCategory()).forEach(product -> {
				AllProductsDTO dto = new AllProductsDTO();
				BeanUtils.copyProperties(product, dto);
				dto.setProductImages(product.getProductImages().iterator().next());
				allProductsDTOs.add(dto);
			});
		}
	
		return allProductsDTOs;
		
	}

	
	
}
