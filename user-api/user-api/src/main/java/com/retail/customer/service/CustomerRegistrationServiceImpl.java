package com.retail.customer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.customer.controller.CustomerRegistrationController;
import com.retail.customer.exception.ResourceNotFoundException;
import com.retail.customer.model.Account;
import com.retail.customer.model.Customer;
import com.retail.customer.model.Login;
import com.retail.customer.repository.CustomerRepository;
import com.retail.customer.repository.LoginRepository;
import com.retail.customer.util.PasswordGenerator;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

	Logger logger = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);

	private CustomerRepository customerRegistrationRepository;
	private LoginRepository loginRepository;

	@Autowired
	CustomerRegistrationServiceImpl(CustomerRepository customerRegistrationRepository,
			LoginRepository loginRepository) {
		this.customerRegistrationRepository = customerRegistrationRepository;
		this.loginRepository = loginRepository;
	}

	public List<Customer> getCustomers() {
		List<Customer> customers = customerRegistrationRepository.findAll();
		logger.info("get all customer details response ", customers);
		return customers;
	}

	public Customer getCustomer(String customerId) {
		Customer customer = customerRegistrationRepository.findById(Long.valueOf(customerId))
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));
		logger.info("get  customer details response ", customer);
		return customer;
	}
	
	public Login getLogin(Long loginId) {
		Login login = loginRepository.findById(loginId)
				.orElseThrow(() -> new ResourceNotFoundException("002.001", "Requested resource Not found"));
		logger.info("get  customer details response ", login);
		return login;
	}

	public Login addCustomer(Customer customer) {
		Customer createdCustomer = null;
		Login login = new Login(PasswordGenerator.generatePassword(8), true, "user");
		if (null != login) {
			loginRepository.save(login);
			customer.setLogin(login);
			logger.info("saving customer details  ", customer);
			createdCustomer = customerRegistrationRepository.save(customer);
			logger.info("saved customer details response ", createdCustomer);
		}
		
		if(createdCustomer != null) {
		Account account = new Account("Savings", 0.0, 0.0);
		
		account.setCustomer(createdCustomer);
		}
		return createdCustomer != null ? login : null;
	}

	public Customer updateCustomer(String customerId, Customer customer) {
		Customer existingCustomer = customerRegistrationRepository.findById(Long.valueOf(customerId)).orElse(null);
		logger.info("existing customer details response ", existingCustomer);
		Customer updatedCustomer = null;
		if (null != existingCustomer) {
			customer.setId(existingCustomer.getId());
			customer.setLogin(existingCustomer.getLogin());
			logger.info("update customer details  ", customer);
			updatedCustomer = customerRegistrationRepository.save(customer);
			logger.info("updated customer details response ", updatedCustomer);
		} else
			throw new ResourceNotFoundException("002.001", "Requested resource Not found");
		return updatedCustomer != null ? updatedCustomer : null;
	}

	public void deleteCustomer(String customerId) {
		customerRegistrationRepository.deleteById(Long.valueOf(customerId));
	}

}
