package com.collabera.orderservice.dto;

import lombok.Data;

@Data
public class UpdateOrderProductDto {

	private Integer orderquantity;
	private String name;
	private Integer price;
	private Integer quantity;
}
