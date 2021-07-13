package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.Cart;

public interface CartRepository  extends JpaRepository<Cart,Long> {
	

}
