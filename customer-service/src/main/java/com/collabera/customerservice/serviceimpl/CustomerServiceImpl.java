package com.collabera.customerservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.customerservice.dto.AccountDto;
import com.collabera.customerservice.dtomapper.AccountDtoMapper;
import com.collabera.customerservice.exception.CommonException;
import com.collabera.customerservice.model.Customer;
import com.collabera.customerservice.model.User;
import com.collabera.customerservice.repository.CustomerRepository;
import com.collabera.customerservice.repository.UserRepository;
import com.collabera.customerservice.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountDtoMapper accountDtoMapper;
	
	@Override
	public Customer getCustomerDetails(Integer id) {

		Customer customer = customerRepository.findByCustomerid(id);
		
		if(customer == null)
			throw new CommonException("Customer Data Not Found");
		
		return customer;
	}

	@Override
	public Customer updateCustomerDetails(Integer id, AccountDto accountDto ){
		Customer customer = customerRepository.findByCustomerid(id);
		
		if(customer == null)
			throw new CommonException("Customer Data Not Found");
		
		customerRepository.save(accountDtoMapper.getCustomer(accountDto, customer));
		
		return customerRepository.findByCustomerid(id);
	}

	@Override
	public String changePassword(Integer id, AccountDto accountDto) {
		try {
			User user = userRepository.findById(id).get();
			
			if(user == null)
				throw new CommonException("User Data Not Found");
			
			userRepository.save(accountDtoMapper.changeUserPassword(accountDto, user));
		}catch(Exception e) {
			throw new CommonException("User Data Not Found");
		}
		
		return "Successfully Changed the Password";
	}

	@Override
	public String deleteAccount(Integer id) {
		try {
			User user = userRepository.findById(id).get();
			Customer customer = customerRepository.findByCustomerid(id);
			
			if(user == null || customer == null)
				throw new CommonException("User or Customer Data Not Found");
			
			userRepository.delete(user);
			customerRepository.delete(customer);
		}catch(Exception e) {
			throw new CommonException("User Data Not Found");
		}
		return "Deleted Account Successfully";
	}

}
