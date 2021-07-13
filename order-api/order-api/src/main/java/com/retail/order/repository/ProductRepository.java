package com.retail.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.order.model.Product;

public interface ProductRepository  extends JpaRepository<Product,Long> {
	

}
