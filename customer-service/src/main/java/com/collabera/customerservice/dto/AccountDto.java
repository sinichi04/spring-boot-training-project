package com.collabera.customerservice.dto;

import lombok.Data;

@Data
public class AccountDto {

	private String firstname;
	private String lastname;
	private String email;
	private String location;
	private String privilege;
	private String status;
	private String password;
}
