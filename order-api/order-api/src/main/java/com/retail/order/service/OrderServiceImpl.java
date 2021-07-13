package com.retail.order.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.order.client.ProductClient;
import com.retail.order.dto.CartDTO;
import com.retail.order.dto.OrderDTO;
import com.retail.order.dto.ProductDTO;
import com.retail.order.exception.ResourceNotFoundException;
import com.retail.order.mapper.CartMapper;
import com.retail.order.mapper.OrderMapper;
import com.retail.order.model.Cart;
import com.retail.order.model.Order;
import com.retail.order.model.OrderProduct;
import com.retail.order.model.Product;
import com.retail.order.repository.CartRepository;
import com.retail.order.repository.OrderProductRepository;
import com.retail.order.repository.OrderRepository;
import com.retail.order.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	private ProductRepository productRepository;
	
	private CartRepository cartRepository;
	
	private OrderProductRepository orderProductRepository;
	
	private OrderRepository orderRepository;
	
	private ProductClient productClient;
	
	
	
	@Autowired	
	private OrderMapper orderMapper;
	
	@Autowired	
	private CartMapper cartMapper;

	@Autowired
	OrderServiceImpl(ProductRepository productRepository,CartRepository cartRepository,ProductClient productClient,OrderProductRepository userOrderProductRepository, OrderRepository orderRepository) {
		this.productRepository = productRepository;
		this.cartRepository= cartRepository;
		this.productClient =productClient;
		this.orderProductRepository = userOrderProductRepository;
		this.orderRepository= orderRepository;
	}

	public List<OrderDTO> getOrders() {
		List<OrderDTO> orderDTOs = new ArrayList<>();
		List<Order> orders = orderRepository.findAll();
		logger.info("get all Order details response ", orders);
		
		for(Order order :orders) {
			if(null != order) {
			logger.info("get  Product details response ", order);
			CartDTO cartDTO =productClient.getCart(order.getCartId());
			List<ProductDTO> products = cartDTO.getProducts();
			OrderDTO orderDTO = orderMapper.toDto(order);
			orderDTO.setProducts(products);
			orderDTOs.add(orderDTO);}
		}
		
		return orderDTOs;
	}

	public OrderDTO getOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  Product details response ", order);
		CartDTO cartDTO =productClient.getCart(order.getCartId());
		List<ProductDTO> products = cartDTO.getProducts();
		OrderDTO orderDTO = orderMapper.toDto(order);
		orderDTO.setProducts(products);
		return orderDTO;
	}
	

	public Product updateProduct(Long productId, Product product) {
		Product existingProduct = productRepository.findById(Long.valueOf(productId)).orElse(null);
		logger.info("existing product details response ", existingProduct);
		Product updatedProduct = null;
		if (null != existingProduct) {
			product.setId(existingProduct.getId());
			logger.info("update product details  ", product);
			updatedProduct = productRepository.save(product);
			logger.info("updated product details response ", updatedProduct);
		} else
			throw new ResourceNotFoundException("002.001", "Requested resource Not found");
		return updatedProduct != null ? updatedProduct : null;
	}

	public void deleteProduct(Long productId) {
		productRepository.deleteById(Long.valueOf(productId));
	}
	
	public CartDTO selectProducts(List<Long> productList) {
		Cart existingCart = null;
		List<Product> updatedProducts = new ArrayList<>();
		List<Product> existingProducts = productList.stream()
				.map(prod -> productRepository.findById(prod).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
		
		logger.info("existing products  response {}", existingProducts);
	
		if (null != existingProducts) {
			 existingCart= existingProducts.stream()
					.filter(prod -> null != prod.getCart()).findFirst().map(p->p.getCart()).orElse(null);
			if(existingCart == null) {
				existingCart = new Cart("open",  existingProducts);
				existingCart =cartRepository.save(existingCart);
			}
			final Cart latestCart = existingCart;
			logger.info("saving product  : {}", latestCart);
			logger.info("existing products  response {}", existingProducts);
			 updatedProducts  =existingProducts.stream().map(prod -> {
				prod.setCart(latestCart);
				logger.info("saving product  : {}", prod);
			return productRepository.save(prod);
			}).filter(Objects::nonNull).collect(Collectors.toList());
			
			
		}
		
		Cart cart  =updatedProducts.stream().map(p -> p.getCart()).findFirst().orElse(null) ;
		
		CartDTO cartDTO = cartMapper.toDto(cart);
//			
		return cartDTO;
	}
	
	public Cart selectProducts1(List<Long> productList) {
		Cart existingCart = null;
		List<Product> updatedProducts = new ArrayList<>();
		List<Product> existingProducts = productList.stream()
				.map(prod -> productRepository.findById(prod).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
		
		logger.info("existing products  response {}", existingProducts);
	
		if (null != existingProducts) {
			 existingCart= existingProducts.stream()
					.filter(prod -> null != prod.getCart()).findFirst().map(p->p.getCart()).orElse(null);
			if(existingCart == null) {
				existingCart = new Cart("open",  existingProducts);
				existingCart =cartRepository.save(existingCart);
			}
			final Cart latestCart = existingCart;
			logger.info("saving product  : {}", latestCart);
			logger.info("existing products  response {}", existingProducts);
			 updatedProducts  =existingProducts.stream().map(prod -> {
				prod.setCart(latestCart);
				logger.info("saving product  : {}", prod);
			return productRepository.save(prod);
			}).filter(Objects::nonNull).collect(Collectors.toList());
			
			
		}
		
	
		return updatedProducts.stream().map(p -> p.getCart()).findFirst().orElse(null);
	}
	
	public OrderDTO  placeOrder( Long cartId, Long userId) {
		List<OrderProduct> ordereProducts = new ArrayList<>();
		CartDTO cartDTO =productClient.getCart(cartId);
		
		logger.info("existing cartDTO  : {}", cartDTO);
		List<ProductDTO> products = cartDTO.getProducts();
		logger.info(" products  : {}", products);
		
		Order order = new Order( cartId, "open", userId,Timestamp.from(Instant.now()),Timestamp.from(Instant.now()), cartDTO.getTotalAmount());
		
		for(ProductDTO productDTO : products) {
			OrderProduct orderProduct = new  OrderProduct(productDTO.getId(), Long.valueOf(1) );			
			orderProduct.setOrder(order);
			ordereProducts.add(orderProductRepository.save(orderProduct));
			
		}
		
		logger.info(" ordereProducts details  : {}", ordereProducts);
		
		Order savedOrder = ordereProducts.get(0).getOrder();
		OrderDTO savedOrderDTO = orderMapper.toDto(savedOrder);
		
		savedOrderDTO.setProducts(products);
		
		logger.info("saved savedOrderDTO  : {}", savedOrderDTO);
		
		return savedOrderDTO;
	}
	
	

}
