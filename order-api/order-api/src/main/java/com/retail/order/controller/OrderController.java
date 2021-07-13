package com.retail.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.order.dto.OrderDTO;
import com.retail.order.dto.OrderRequestDTO;
import com.retail.order.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);

	private OrderService orderService;

	@Autowired
	OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@ApiOperation(value = "Gets Order Details for a given order id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Order not found") })
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
		logger.info("get order details for id : {} ", orderId);
		return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets all Orders ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Orders not found") })

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDTO>> getOrders() {
		logger.info("get orders details for id : {} ");
		return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
	}


	@ApiOperation(value = "creates new order for selected prodocts in cart ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "Order/products not found") })
	@PostMapping("/orders")
	public ResponseEntity<OrderDTO> placeOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {

		logger.info("add order details : {} ", orderRequestDTO);
		return new ResponseEntity<>(orderService.placeOrder(orderRequestDTO.getCartId(), orderRequestDTO.getUserId()),
				HttpStatus.CREATED);
	}

}
