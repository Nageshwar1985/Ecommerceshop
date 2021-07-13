package com.retail.product.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.retail.product.model.Vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	@Column(name = "name")
	private String name;
	private String desc;

	private Integer quantity;


	private String category;
	
	private Double vendorNo;

	private String status;
	
	private Double price;
	
	@JsonIgnore
	private CartDTO cartDTO;
	
	@JsonIgnore
	private VendorDTO vendorDTO;

}
