package com.retail.order.service;

import java.util.List;

import com.retail.order.dto.CartDTO;
import com.retail.order.dto.CustomerDTO;
import com.retail.order.dto.LoginDTO;
import com.retail.order.dto.OrderDTO;
import com.retail.order.exception.ResourceNotFoundException;
import com.retail.order.model.Cart;
import com.retail.order.model.Order;
import com.retail.order.model.Product;

public interface OrderService {


//	public List<Product> getProducts();

//	public Product getProduct(Long productId);

//	public Product addProduct(Product product);

	public Product updateProduct(Long productId, Product product);

	public void deleteProduct(Long productId);
	
	public CartDTO selectProducts(List<Long> productList) ;
	
//	public CartDTO getCart(Long cartId);
	
	public OrderDTO  placeOrder( Long cartId,Long userId) ;
	
	public List<OrderDTO> getOrders() ;

	public OrderDTO getOrder(Long orderId);
}
