package com.retail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.order.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
