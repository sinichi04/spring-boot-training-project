package com.collabera.adminservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AccountCreationDto {

	@NotBlank
	private String firstname;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String location;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}[1-9]{3}", message = "Must Follow the format A-Z(3)1-9(3)")
	private String username;
	
	@NotBlank
	private String password;
}
