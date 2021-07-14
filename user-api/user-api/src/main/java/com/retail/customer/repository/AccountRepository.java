package com.retail.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.customer.model.CustomerAccount;

public interface AccountRepository  extends JpaRepository<CustomerAccount,Long> {
	
//	@Override
//	@Query(value = "SELECT * FROM account WHERE 
//	debito_negativacao.status= :status", nativeQuery = true)
	public List<CustomerAccount> findByCustomerId(Long id);
	


}
