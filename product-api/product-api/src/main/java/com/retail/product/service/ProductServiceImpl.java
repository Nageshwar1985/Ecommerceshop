package com.retail.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.product.dto.CartDTO;
import com.retail.product.exception.ResourceNotFoundException;
import com.retail.product.mapper.CartMapper;
import com.retail.product.model.Cart;
import com.retail.product.model.Product;
import com.retail.product.repository.CartRepository;
import com.retail.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductRepository productRepository;

	private CartRepository cartRepository;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	ProductServiceImpl(ProductRepository productRepository, CartRepository cartRepository) {
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
	}

	public List<Product> getProducts() {

		List<Product> Products = productRepository.findAll();
		logger.info("get all Product details response ", Products);
		return Products;
	}

	public Product getProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  Product details response ", product);
		return product;
	}

	public CartDTO getCart(Long cartId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));

		logger.info("get  Cart details response ", cart);
		return cartMapper.toDto(cart);
	}

	public Product addProduct(Product product) {
		logger.info("saving Product details  ", product);

		return productRepository.save(product);
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
		List<Product> existingProducts = productList.stream().map(prod -> productRepository.findById(prod).orElse(null))
				.filter(Objects::nonNull).collect(Collectors.toList());

		logger.info("existing products  response {}", existingProducts);

		if (null != existingProducts) {
			existingCart = existingProducts.stream().filter(prod -> null != prod.getCart()).findFirst()
					.map(p -> p.getCart()).orElse(null);
			Double cartTotalAmount = existingProducts.stream().filter(prod -> null != prod.getPrice())
					.map(p -> p.getPrice()).collect(Collectors.summingDouble(Double::doubleValue));

			if (existingCart == null) {
				existingCart = new Cart("open", existingProducts);
				existingCart.setTotalAmount(cartTotalAmount);
			}
			Cart latestCart = existingCart;
			logger.info("saving product  : {}", latestCart);
			logger.info("existing products  response {}", existingProducts);

			for (Product prod : existingProducts) {
				prod.setCart(latestCart);
				logger.info("saving product  : {}", prod);
				updatedProducts.add(productRepository.save(prod));

			}

		}

		Cart cart = updatedProducts.stream().map(p -> p.getCart()).findFirst().orElse(null);
		logger.info("cart details  : {}", cart);
		CartDTO cartDTO = cartMapper.toDto(cart);
		logger.info("cartDTO  : {}", cartDTO);
		return cartDTO;
	}

}
