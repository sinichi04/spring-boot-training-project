package com.collabera.customerservice.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	
	@OneToOne
	private Customer customer;
	
}
