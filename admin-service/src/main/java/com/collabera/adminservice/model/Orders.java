package com.collabera.adminservice.model;

import java.util.List;

import lombok.Data;

@Data
public class Orders {

	private Integer id;
	private Integer customerid;
	private Integer productid;
	private Integer quantity;
	private List<Product> productList;
}
