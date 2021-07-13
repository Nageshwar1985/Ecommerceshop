package com.retail.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.retail.payment.dto.OrderDTO;


@FeignClient(name="order-api")
public interface OrderClient {
	
	@GetMapping("/orders/{orderId}")
	public OrderDTO getOrder(@PathVariable Long  orderId);

}
