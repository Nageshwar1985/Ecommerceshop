package com.retail.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.customer.model.Customer;
import com.retail.customer.model.Login;

public interface LoginRepository  extends JpaRepository<Login,Long> {

}
