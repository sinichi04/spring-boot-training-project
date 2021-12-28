package com.collabera.adminservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.collabera.adminservice.dto.AccountCreationDto;
import com.collabera.adminservice.dto.AccountDto;
import com.collabera.adminservice.dto.CustomerOrder;
import com.collabera.adminservice.dtomapper.AccountCreationDtoMapper;
import com.collabera.adminservice.dtomapper.AccountDtoMapper;
import com.collabera.adminservice.exception.AccountCreationException;
import com.collabera.adminservice.exception.CommonException;
import com.collabera.adminservice.model.Customer;
import com.collabera.adminservice.model.User;
import com.collabera.adminservice.repository.CustomerRepository;
import com.collabera.adminservice.repository.UserRepository;
import com.collabera.adminservice.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountCreationDtoMapper accountCreationDtoMapper;
	
	@Autowired
	private AccountDtoMapper accountDtoMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Customer> displayAccount() {
		System.out.println("Hello there");
		List<Customer> customerList = customerRepository.findAll();
		
		if(customerList.isEmpty())
			throw new CommonException("Customer Data Not Found");
		
		return customerList;
	}
	
	@Override
	public String createAccount(AccountCreationDto accountCreationDto) {
		try {
			userRepository.save(accountCreationDtoMapper.getUser(accountCreationDto));
			
			User user = userRepository.getLatestUser();
			customerRepository.save(accountCreationDtoMapper.getCustomer(accountCreationDto, user, "admin"));
			
		}catch (Exception e) {
			throw new AccountCreationException(e.getLocalizedMessage());
		}
		
		return "Account Successfully Created";
	}
	
	@Override
	public Customer updateCustomer(Integer id, AccountDto accountDto) {
		try {
			Customer customer = customerRepository.findByCustomerid(id);
			
			if(customer == null)
				throw new CommonException("Customer Data Not Found");
				
			customerRepository.save(accountDtoMapper.getCustomer(accountDto, customer));
		}catch(Exception e) {
			throw new CommonException("Customer Data Not Found");
		}
		
		return customerRepository.findByCustomerid(id);
	}
	
	@Override
	public String deleteCustomerById(Integer id) {
		try {
			User user = userRepository.findById(id).get();
			Customer customer = customerRepository.findByCustomerid(id);
			
			if(user == null || customer == null)
				throw new CommonException("No customer or user data found with id "+id);
			
			userRepository.delete(user);
			customerRepository.delete(customer);
		}catch(Exception e) {
			throw new CommonException("No customer or user data found with id "+id);
		}
		
		return "Account Deleted Successfully";
	}

	@Override
	public List<Customer> getNotActiveCustomerDetails(){
		List<Customer> customerList = customerRepository.findByStatus("N");
		
		if(customerList.isEmpty())
			throw new CommonException("Customer Data Not Found");
		
		return customerList;
	}
	
	@Override
	public String approveAccount(Integer id) {
		
		Customer customer = customerRepository.findByCustomerid(id);
		
		if(customer == null)
			throw new CommonException("Customer Data Not Found");
		
		customer.setStatus("A");
		customerRepository.save(customer);
		
		return "Account Successfully Approved";
	}
	
	@Override
	public List<Customer> search(String searchText){
		
		List<Customer> customerList = customerRepository.searchCustomer(searchText);
		
		if(customerList.isEmpty())
			throw new CommonException("Customer Data Not Found");
		
		return customerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getCustomerOrder() {
		List<CustomerOrder> customerOrderList = new ArrayList<>();
		List<Customer> customerList = customerRepository.findAll();
		
		if(customerList == null)
			throw new CommonException("Customer Data Not Found");
		
		for(Customer customer : customerList) {
			CustomerOrder customerOrder = new CustomerOrder();
			
			customerOrder.setCustomerid(customer.getCustomerid());
			customerOrder.setFirstname(customer.getFirstname());
			customerOrder.setLastname(customer.getLastname());
			customerOrder.setEmail(customer.getEmail());
			customerOrder.setLocation(customer.getLocation());
			customerOrder.setPrivilege((customer.getPrivilege().equals("A")) ? "Admin" : "Customer");
			customerOrder.setStatus((customer.getStatus().equals("A")) ? "Active" : "Not-Active");
			
			List orderList = restTemplate.getForObject("http://localhost:8084/order/returnOrderList?id="+customer.getCustomerid(), List.class);
			customerOrder.setOrders(orderList);
			
			customerOrderList.add(customerOrder);
		}
		
		return customerOrderList;
	}
	

}
