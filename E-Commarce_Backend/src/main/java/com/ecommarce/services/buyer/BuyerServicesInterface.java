package com.ecommarce.services.buyer;

import java.util.HashMap;
import java.util.List;

import com.ecommarce.dto.OrderInfoResponseDTO;
import com.ecommarce.dto.OrderInformationDTO;
import com.ecommarce.dto.OrderPaymentDTO;
import com.ecommarce.entity.OrderDetail;
import com.razorpay.RazorpayException;

public interface BuyerServicesInterface {
	
	public OrderDetail order(OrderInformationDTO orderInformationDTO);
	
	public OrderPaymentDTO getPaymentFromRazorPay(HashMap<String,Integer> orderedProducts) throws RazorpayException; 
	
	public OrderInfoResponseDTO getOrderInformation(String orderId);

	public List<String> getAllProductCetegories();

}
