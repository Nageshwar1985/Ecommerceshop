package com.retail.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.payment.model.Payment;
import com.retail.payment.model.Transfer;

public interface TransferRepository  extends JpaRepository<Transfer,Long> {
	

}
