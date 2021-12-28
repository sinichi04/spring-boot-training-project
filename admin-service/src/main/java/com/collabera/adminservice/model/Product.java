package com.collabera.adminservice.model;

import lombok.Data;

@Data
public class Product {

	private Integer id;
	private String productname;
	private Integer price;
	private Integer quantity;
}
