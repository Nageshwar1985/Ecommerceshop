package com.retail.shop.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.shop.dto.CartDTO;
import com.retail.shop.dto.ProductDTO;

@FeignClient(name="product-api")
public interface ProductClient {
	
	@GetMapping("/products/{productId}")
	public ProductDTO getProduct(@PathVariable Long  productId);
	
	@GetMapping("/products")
	public List<ProductDTO> getProducts();
	
	@PostMapping("/products/select")
	public CartDTO selectProduct(@Valid @RequestBody List<Long> ids);

}
