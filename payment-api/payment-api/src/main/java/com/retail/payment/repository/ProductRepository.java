package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.Product;

public interface ProductRepository  extends JpaRepository<Product,Long> {
	

}
