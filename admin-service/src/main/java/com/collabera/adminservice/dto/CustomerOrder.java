package com.collabera.adminservice.dto;

import java.util.List;

import com.collabera.adminservice.model.Orders;

import lombok.Data;

@Data
public class CustomerOrder {

	private Integer customerid;
	private String firstname;
	private String lastname;
	private String email;
	private String location;
	private String privilege;
	private String status;
	private List<Orders> orders;
}
