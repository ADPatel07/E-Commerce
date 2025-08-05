package com.ecommarce.controller;

import java.util.HashMap;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommarce.dto.FillterDTO;
import com.ecommarce.dto.OrderInformationDTO;
import com.ecommarce.entity.ProductImageInfo;
import com.ecommarce.services.buyer.BuyerServicesInterface;
import com.ecommarce.services.products.ProductsServicesInterface;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("products")
@CrossOrigin("*")
public class BuyerController {
	
	@Autowired
	private ProductsServicesInterface productsServicesInterface;
	
	@Autowired
	private BuyerServicesInterface buyerServicesInterface;

	@GetMapping("all")
	protected ResponseEntity<?> getAllProducts(){
		return ResponseEntity.ok(productsServicesInterface.getAllProducts());
	}
	
	@GetMapping("get/{productId}")
	protected ResponseEntity<?> getProduct(@PathVariable String productId) throws AttributeNotFoundException{
		return ResponseEntity.ok(productsServicesInterface.getProductById(productId));
	}
	
	
	@GetMapping("image/{imageId}")
	protected ResponseEntity<?> getImage(@PathVariable String imageId) {
		ProductImageInfo productImage = productsServicesInterface.getProductimage(imageId);
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(productImage.getContentType()))
				.body(productImage.getBytes());
	}
	
	@PostMapping("/filltered")
	protected ResponseEntity<?> getFillteredProducts(@RequestBody FillterDTO fillterDTO) throws Exception{
		return ResponseEntity.ok(productsServicesInterface.getFillteredProducts(fillterDTO));
	}
	
	@PostMapping("order")
	public ResponseEntity<?> order(@RequestBody @Validated OrderInformationDTO orderInformationDTO){
		return ResponseEntity.ok(buyerServicesInterface.order(orderInformationDTO));
	}
	
	@PostMapping("payment")
	public ResponseEntity<?> getPayment(@RequestBody HashMap<String, Integer> orders) throws RazorpayException{
		return ResponseEntity.ok(buyerServicesInterface.getPaymentFromRazorPay(orders));
	}
	
	@GetMapping("order/info/{orderId}")
	protected ResponseEntity<?> getOrderInfo(@PathVariable String orderId) throws AttributeNotFoundException{
		return ResponseEntity.ok(buyerServicesInterface.getOrderInformation(orderId));
	}
	
	@GetMapping("categories")
	public ResponseEntity<?> getAllCategories(){
		return ResponseEntity.ok(buyerServicesInterface.getAllProductCetegories());
	}
	
	
}
