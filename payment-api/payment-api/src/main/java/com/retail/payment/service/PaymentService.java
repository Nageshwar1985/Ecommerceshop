package com.retail.payment.service;

import java.util.List;

import com.retail.payment.dto.CartDTO;
import com.retail.payment.model.CustomerAccount;
import com.retail.payment.model.Product;
import com.retail.payment.model.Transfer;
import com.retail.payment.model.VendorAccount;

public interface PaymentService {


//	public List<Product> getProducts();

//	public Product getProduct(Long productId);

//	public Product addProduct(Product product);

//	public Product updateProduct(Long productId, Product product);
//
//	public void deleteProduct(Long productId);
	
//	public CartDTO selectProducts(List<Long> productList) ;
	
//	public CartDTO getCart(Long cartId);
	
	public Transfer  placePayment( Long orderId,Long userId) ;
	
	public List<Transfer> getPaymentTransfers() ;

	public Transfer getPaymentTransfer(Long transferId);
	
	public CustomerAccount createCustomerAccount(CustomerAccount customerAccount);
	
	public VendorAccount createVendorAccount(VendorAccount vendorAccount);
}
