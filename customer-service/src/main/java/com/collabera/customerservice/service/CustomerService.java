package com.collabera.customerservice.service;

import com.collabera.customerservice.dto.AccountDto;
import com.collabera.customerservice.model.Customer;

public interface CustomerService {

	public Customer getCustomerDetails(Integer id);
	
	public Customer updateCustomerDetails(Integer id, AccountDto accountDto );
	
	public String changePassword(Integer id, AccountDto accountDto);
	
	public String deleteAccount(Integer id);
}