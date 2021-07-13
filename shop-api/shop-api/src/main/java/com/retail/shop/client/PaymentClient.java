package com.retail.shop.client;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.shop.dto.PaymentRequestDTO;
import com.retail.shop.dto.TransferDTO;

@FeignClient(name="payment-api")
public interface PaymentClient {
	
	@PostMapping("/payments")
	public TransferDTO placePayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) ;
}
