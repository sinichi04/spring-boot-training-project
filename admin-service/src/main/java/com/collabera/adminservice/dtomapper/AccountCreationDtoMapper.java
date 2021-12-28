package com.collabera.adminservice.dtomapper;

import org.springframework.stereotype.Component;

import com.collabera.adminservice.dto.AccountCreationDto;
import com.collabera.adminservice.model.Customer;
import com.collabera.adminservice.model.User;

@Component
public class AccountCreationDtoMapper {

	public User getUser(AccountCreationDto accountDto) {
		User user = new User();
		user.setUsername(accountDto.getUsername());
		user.setPassword(accountDto.getPassword());
		
		return user;
	}
	
	public Customer getCustomer(AccountCreationDto accountDto, User user, String pos) {
		String status = (pos.equals("admin")) ? "A" : "N";
		Customer customer = new Customer();
		
		customer.setCustomerid(user.getId());
		customer.setFirstname(accountDto.getFirstname());
		customer.setLastname(accountDto.getLastname());
		customer.setEmail(accountDto.getEmail());
		customer.setLocation(accountDto.getLocation());
		customer.setPrivilege("C");
		customer.setStatus(status);
		
		return customer;
	}
}
