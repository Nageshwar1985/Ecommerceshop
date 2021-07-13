package com.retail.shop.service;

import java.util.List;

import com.retail.shop.dto.CustomerDTO;
import com.retail.shop.dto.LoginDTO;
import com.retail.shop.dto.OrderDTO;
import com.retail.shop.dto.ProductDTO;
import com.retail.shop.dto.PurchaseResponseDTO;

public interface ShopService {

	public List<CustomerDTO> getCustomers();

	public CustomerDTO getCustomer(String customerId);

	public LoginDTO addCustomer(CustomerDTO customer);
	
	public List<ProductDTO> getProducts() ;
	
	public ProductDTO getProduct( Long productId);
	
	public PurchaseResponseDTO buyProduct(LoginDTO loginDTO,List<Long> ids) ;
	
	public List<OrderDTO> getOrders(Long customerId) ;
}
