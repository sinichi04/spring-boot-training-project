package com.collabera.adminservice.dtomapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collabera.adminservice.dto.AccountDto;
import com.collabera.adminservice.model.Customer;
import com.collabera.adminservice.model.User;
import com.collabera.adminservice.util.CommonUtil;

@Component
public class AccountDtoMapper {

	@Autowired
	private CommonUtil commonUtil;
	
	public Customer getCustomer(AccountDto AccountDtoMapper, Customer customer) {
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getFirstname()))
			customer.setFirstname(AccountDtoMapper.getFirstname());
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getLastname()))
			customer.setLastname(AccountDtoMapper.getLastname());
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getEmail()))
			customer.setEmail(AccountDtoMapper.getEmail());
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getLocation()))
			customer.setLocation(AccountDtoMapper.getLocation());
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getPrivilege()))
			customer.setPrivilege(AccountDtoMapper.getPrivilege());
		
		if(commonUtil.isNotEmpty(AccountDtoMapper.getStatus()))
			customer.setStatus(AccountDtoMapper.getStatus());
			
		return customer;
	}
	
	public User changeUserPassword(AccountDto accountDto, User user) {
		
		if(commonUtil.isNotEmpty(accountDto.getPassword()))
			user.setPassword(accountDto.getPassword());
		
		return user;
	}
}
