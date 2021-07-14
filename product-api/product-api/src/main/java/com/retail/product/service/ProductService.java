package com.retail.product.service;

import java.util.List;

import com.retail.product.dto.CartDTO;
import com.retail.product.dto.CustomerDTO;
import com.retail.product.dto.LoginDTO;
import com.retail.product.exception.ResourceNotFoundException;
import com.retail.product.model.Cart;
import com.retail.product.model.Product;

public interface ProductService {


	public List<Product> getProducts();

	public Product getProduct(Long productId);

	public Product addProduct(Product product);

	public Product updateProduct(Long productId, Product product);

	public void deleteProduct(Long productId);
	
	public CartDTO selectProducts(List<Long> productList) ;
	
	public CartDTO getCart(Long cartId);
}
