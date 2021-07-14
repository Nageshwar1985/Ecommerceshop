package com.retail.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.product.dto.CartDTO;
import com.retail.product.model.Product;
import com.retail.product.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	@Autowired
	ProductController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Gets Product Details for a given product Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Product not found") })
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
		logger.info("get product details for id : {} ", productId);
		return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		logger.info("get products details for id : {} ");
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@ApiOperation(value = "create and add new products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {

		logger.info("add product details : {} ", product);
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
	}

	@ApiOperation(value = "update existing product with latest details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody Product product) {
		logger.info("update product details : {} ", product);
		return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
	}

	@ApiOperation(value = "Deletes existing product ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Products not found") })
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		logger.info("delete product details for id : {} ", id);
		productService.deleteProduct(id);
		return new ResponseEntity<>("Requested Product has been deleted", HttpStatus.OK);
	}

	@ApiOperation(value = "Adds the selected product to cart and gives cart id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Product not found") })
	@PostMapping("/products/select")
	public ResponseEntity<CartDTO> selectProduct(@RequestBody List<Long> ids) {
		logger.info("select product details : {} ", ids);
		return new ResponseEntity<>(productService.selectProducts(ids), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all the products for a given  cart id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "cart not found") })
	@GetMapping("/products/cart/{cartId}")
	public ResponseEntity<CartDTO> getCart(@PathVariable("cartId") Long cartId) {
		logger.info("get cart details for id : {} ");
		return new ResponseEntity<>(productService.getCart(cartId), HttpStatus.OK);
	}

}
