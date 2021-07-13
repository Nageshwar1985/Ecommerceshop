package com.retail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.order.model.Cart;
import com.retail.order.model.Order;

public interface OrderRepository  extends JpaRepository<Order,Long> {
	

}
