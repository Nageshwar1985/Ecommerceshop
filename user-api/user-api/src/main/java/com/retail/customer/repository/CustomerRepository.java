package com.retail.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.customer.model.Customer;
import com.retail.customer.model.Login;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
	
	public Customer findByLogin(Login login);

}
