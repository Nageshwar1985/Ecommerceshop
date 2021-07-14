package com.retail.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.product.model.Product;

public interface ProductRepository  extends JpaRepository<Product,Long> {
	

}
