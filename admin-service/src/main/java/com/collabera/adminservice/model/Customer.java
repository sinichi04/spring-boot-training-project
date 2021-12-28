package com.collabera.adminservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer customerid;
	private String firstname;
	private String lastname;
	private String email;
	private String location;
	private String privilege;
	private String status;
	
//	@OneToOne
	@Transient
	private User user;
}