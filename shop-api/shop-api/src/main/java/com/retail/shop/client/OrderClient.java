package com.retail.shop.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.shop.dto.OrderDTO;
import com.retail.shop.dto.OrderRequestDTO;
import com.retail.shop.dto.ProductDTO;

@FeignClient(name="order-api")
public interface OrderClient {
	
	@GetMapping("/orders/{orderId}")
	public OrderDTO getOrder(@PathVariable Long orderId);
	
	@GetMapping("/orders")
	public List<OrderDTO> getOrders();
	
	@PostMapping("/orders")
	public OrderDTO placeOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) ;

}
