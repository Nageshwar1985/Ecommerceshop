package com.retail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.order.model.OrderProduct;

public interface OrderProductRepository  extends JpaRepository<OrderProduct,Long> {
	

}
