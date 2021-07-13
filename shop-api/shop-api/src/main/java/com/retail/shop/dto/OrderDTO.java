package com.retail.shop.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Long id;	

	private String status;
	
	
//	@JsonIgnore
//	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL)
//	private List<Product> products = new ArrayList<>();
//	
//	@JsonIgnore
//	@ManyToOne(mappedBy ="order",cascade = CascadeType.ALL)
//	private User user ;
	
//	@JsonIgnore
//	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	private List<ProductDTO> products = new ArrayList<>();
	
	private Long cartId;    
	
	private Long userId;  
	
	private Double totalAmount;
	
	private Timestamp createdOn;
	
//	public OrderDTO(String status,Long cartId,List<UserOrderProduct> userOrderProducts) {
//	this.status=status;
//	this.cartId =cartId;
//	this.userOrderProducts=userOrderProducts;
//	}

}
