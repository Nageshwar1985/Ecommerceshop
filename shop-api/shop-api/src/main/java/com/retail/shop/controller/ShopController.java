package com.retail.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.retail.shop.dto.BuyProductRequestDTO;
import com.retail.shop.dto.CustomerDTO;
import com.retail.shop.dto.LoginDTO;
import com.retail.shop.dto.OrderDTO;
import com.retail.shop.dto.ProductDTO;
import com.retail.shop.dto.PurchaseResponseDTO;
import com.retail.shop.service.ShopService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ShopController {

	Logger logger = LoggerFactory.getLogger(ShopController.class);

	private ShopService shopService;

	@Autowired
	ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@ApiOperation(value = "Gets Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@GetMapping("/shop/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
		logger.info("get customer details for id : {} ", customerId);
		return new ResponseEntity<>(shopService.getCustomer(customerId), HttpStatus.OK);
	}

	@ApiOperation(value = "Adds new Customer Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@PostMapping("/shop/customers")
	public ResponseEntity<LoginDTO> addCustomer(@Valid @RequestBody CustomerDTO customer) {

		logger.info("add customer details : {} ", customer);
		return new ResponseEntity<>(shopService.addCustomer(customer), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Gets all available Products ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@GetMapping("/shop/products")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		return new ResponseEntity<>(shopService.getProducts(), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets  Product for a given Id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Product not found") })
	@GetMapping("/shop/products/{productId}")
	public ResponseEntity<ProductDTO> getProducts(@PathVariable Long productId) {
		logger.info("get product details for id : {} ", productId);
		return new ResponseEntity<>(shopService.getProduct(productId), HttpStatus.OK);
	}

	@ApiOperation(value = " validates Customer login and buy the selected product and gets conformation including payment  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@PostMapping("/shop/products")
	public ResponseEntity<PurchaseResponseDTO> buyProduct(
			@Valid @RequestBody BuyProductRequestDTO buyProductRequestDTO) {
		logger.info("get buyProductRequestDTO details for id : {} ", buyProductRequestDTO);
		return new ResponseEntity<>(
				shopService.buyProduct(buyProductRequestDTO.getLoginDTO(), buyProductRequestDTO.getProductIds()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all available orders for a selected customer  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Order not found") })

	@GetMapping("/shop/{customerId}/orders")
	public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long customerId) {
		logger.info("get customer details for id : {} ", customerId);
		return new ResponseEntity<>(shopService.getOrders(customerId), HttpStatus.OK);
	}

	// Get orders

}
