package com.retail.shop.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyProductRequestDTO {

	private LoginDTO loginDTO;

	private List<Long> productIds;

}
