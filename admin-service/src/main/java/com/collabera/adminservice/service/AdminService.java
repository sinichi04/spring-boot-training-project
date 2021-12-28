package com.collabera.adminservice.service;

import java.util.List;

import com.collabera.adminservice.dto.AccountCreationDto;
import com.collabera.adminservice.dto.AccountDto;
import com.collabera.adminservice.dto.CustomerOrder;
import com.collabera.adminservice.model.Customer;

public interface AdminService {
	
	public List<Customer> displayAccount();
	
	public String createAccount(AccountCreationDto accountDto);

	public Customer updateCustomer(Integer id, AccountDto customerDto);
	
	public String deleteCustomerById(Integer id);
	
	public List<Customer> getNotActiveCustomerDetails();
	
	public String approveAccount(Integer id);

	public List<Customer> search(String searchText);
	
	public List<CustomerOrder> getCustomerOrder();
}

