package com.retail.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.retail.shop.client.CustomerClient;
import com.retail.shop.client.OrderClient;
import com.retail.shop.client.PaymentClient;
import com.retail.shop.client.ProductClient;
import com.retail.shop.dto.CartDTO;
import com.retail.shop.dto.CustomerDTO;
import com.retail.shop.dto.LoginDTO;
import com.retail.shop.dto.OrderDTO;
import com.retail.shop.dto.OrderRequestDTO;
import com.retail.shop.dto.PaymentRequestDTO;
import com.retail.shop.dto.ProductDTO;
import com.retail.shop.dto.PurchaseResponseDTO;
import com.retail.shop.dto.TransferDTO;

@Service
public class ShopServiceImpl implements ShopService {

	Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	private CustomerClient customerClient;

	private ProductClient productClient;

	private OrderClient orderClient;

	private PaymentClient paymentClient;

	@Autowired
	ShopServiceImpl(CustomerClient customerClient, ProductClient productClient, OrderClient orderClient,
			PaymentClient paymentClient) {
		this.customerClient = customerClient;
		this.productClient = productClient;
		this.orderClient = orderClient;
		this.paymentClient = paymentClient;
	}

	public List<CustomerDTO> getCustomers() {
		List<CustomerDTO> customers = customerClient.getCustomers();
		logger.info("get all customer details response ", customers);
		return customers;
	}

	public CustomerDTO getCustomer(String customerId) {
		CustomerDTO customer = customerClient.getCustomer(customerId);
		logger.info("get  customer details response ", customer);
		return customer;
	}

	public LoginDTO addCustomer(CustomerDTO customer) {
		logger.info("saving customer details  ", customer);

		return customerClient.addCustomer(customer);
	}

	public List<ProductDTO> getProducts() {
		List<ProductDTO> products = new ArrayList<>();
		products = productClient.getProducts();
		return products;
	}

	public ProductDTO getProduct(Long productId) {

		ProductDTO product = null;
		product = productClient.getProduct(productId);
		return product;

	}

	public PurchaseResponseDTO buyProduct(LoginDTO loginDTO, List<Long> ids) {
		CartDTO cartDTO = null;
		OrderDTO orderDTO = null;
		TransferDTO transferDTO = null;
		LoginDTO existingLoginDTO = customerClient.getLogin(loginDTO.getId());
		if (existingLoginDTO.equals(loginDTO)) {
			cartDTO = productClient.selectProduct(ids);
			logger.info("get  cartDTO details response {}", cartDTO);
			if (cartDTO != null)
				orderDTO = orderClient.placeOrder(new OrderRequestDTO(cartDTO.getCartId(), loginDTO.getId()));
			logger.info("Order  Details {} ", orderDTO);
			if (orderDTO != null)
				transferDTO = paymentClient.placePayment(new PaymentRequestDTO(orderDTO.getId(), loginDTO.getId()));

		} else {
			logger.info("invalide user {} ", loginDTO.getId());
		}

		logger.info("transfer Details {} ", transferDTO);
		String message = "";
		if ("Success".equalsIgnoreCase(transferDTO.getStatus())) {
			message = "Purchase for Selected products has been successfully";
		} else {

			message = "Purchase for Selected products has been failed, please try again";
		}

		PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO(transferDTO.getId(), transferDTO.getAmount(),
				transferDTO.getStatus(), message);
		purchaseResponseDTO.setOrderedProducts(orderDTO.getProducts());
		logger.info("purchaseResponseDTO Details {} ", purchaseResponseDTO);
		return purchaseResponseDTO;
	}

	public List<OrderDTO> getOrders(Long customerId) {
		List<OrderDTO> orders = orderClient.getOrders();
		logger.info("  order details  ", orders);
		List<OrderDTO> customerOrders = null;
		if (!CollectionUtils.isEmpty(orders))
			customerOrders = orders.stream().filter(o -> o.getUserId().equals(customerId)).collect(Collectors.toList());

		logger.info("get  order details response ", customerOrders);
		return customerOrders;
	}

}
