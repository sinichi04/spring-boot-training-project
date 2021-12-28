package com.collabera.orderservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderDto {

	@NotBlank
	private Integer customerid;
	
	@NotBlank
	private Integer productid;
	
	@NotBlank
	private Integer quantity;
}
