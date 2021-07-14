package com.retail.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.product.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
