package com.ecommarce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommarce.entity.ProductCategory;
import com.ecommarce.entity.ProductInfo;
import com.ecommarce.services.seller.ProductsCRUDInterface;

@RestController
@RequestMapping("seller/product")
@CrossOrigin("*")
//@Secured("ROLE_SELLER")
public class SellerController {
	
	@Autowired
	private ProductsCRUDInterface productsCRUDInterface;

	@PostMapping("add")
	public ResponseEntity<ProductInfo> addProduct(@RequestParam String productInfo, @RequestParam MultipartFile[] productImages) throws IOException {
			return ResponseEntity.ok(productsCRUDInterface.addProduct(productInfo, productImages));
	}
	
	@PostMapping("category/add")
	public ResponseEntity<?> addProductCetagory(@RequestBody ProductCategory productCategory) throws IOException {
			productsCRUDInterface.addProductCetagory(productCategory);
			return ResponseEntity.ok("Added Successfully");
	}
	
	@DeleteMapping("delete/{productId}")
	public ResponseEntity<ProductInfo> deleteProduct(@PathVariable String productId){
		return ResponseEntity.ok(productsCRUDInterface.deleteProduct(productId));
	}
	
	@GetMapping("get/{productId}")
	public ResponseEntity<ProductInfo> getProductById(@PathVariable String productId){
		return ResponseEntity.ok(productsCRUDInterface.getProductById(productId));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<ProductInfo>> getAllProducts(){
		return ResponseEntity.ok(productsCRUDInterface.getAllProduct());
 	}
}
