package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.OrderProduct;

public interface OrderProductRepository  extends JpaRepository<OrderProduct,Long> {
	

}
