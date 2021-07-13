package com.retail.product.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.product.dto.CustomerDTO;
import com.retail.product.dto.LoginDTO;

@FeignClient(name="customer-api")
public interface CustomerClient {
	
	@GetMapping("/customers/{customerId}")
	public CustomerDTO getCustomer(@PathVariable String  customerId);
	
	@GetMapping("/customers")
	public List<CustomerDTO> getCustomers();
	
	@PostMapping("/customers/")
	public LoginDTO addCustomer(@Valid @RequestBody CustomerDTO customer);

}
