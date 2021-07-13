package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.Cart;
import com.retail.payment.model.Order;

public interface OrderRepository  extends JpaRepository<Order,Long> {
	

}
