package com.retail.customer.service;


import java.util.List;

import com.retail.customer.model.Customer;
import com.retail.customer.model.Login;

public interface CustomerRegistrationService {
	
	public Customer getCustomer(String customerId);
	public List<Customer> getCustomers();
	public Login addCustomer(Customer customer);
	public Customer updateCustomer(String customerId,Customer customer);
	public void deleteCustomer(String customerId);
	
	public Login getLogin(Long loginId) ;

}
