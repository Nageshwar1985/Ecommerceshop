package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment,Long> {
	

}
