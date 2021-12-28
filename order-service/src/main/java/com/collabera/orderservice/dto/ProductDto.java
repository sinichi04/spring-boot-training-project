package com.collabera.orderservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {

	@NotBlank
	private String name;
	
	@NotNull
	private Integer price;
	
	@NotNull
	private Integer quantity;
}
