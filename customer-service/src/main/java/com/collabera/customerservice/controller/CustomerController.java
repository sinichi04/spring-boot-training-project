package com.collabera.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.customerservice.dto.AccountDto;
import com.collabera.customerservice.exception.CommonException;
import com.collabera.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getCustomerDetails")
	public ResponseEntity<Object> getCustomerDetails(@RequestParam("id") Integer id){
		System.out.println("id");
		return new ResponseEntity<Object>(ResponseEntity.ok().body(customerService.getCustomerDetails(id)), HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomerDetails")
	public ResponseEntity<?> updateCustomerDetails(@RequestParam("id") Integer id, @RequestBody AccountDto accountDto) {
		if(id == null)
			throw new CommonException("Parameter id must not be empty");
		
		if(accountDto == null)
			throw new CommonException("Must have at least one(1) customer detail");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(customerService.updateCustomerDetails(id, accountDto)), HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestParam("id") Integer id, @RequestBody AccountDto accountDto) {
		if(id == null)
			throw new CommonException("Parameter id must not be empty");
		
		if(accountDto.getPassword() == null)
			throw new CommonException("Password must not be empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(customerService.changePassword(id, accountDto)), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomerAccount")
	public ResponseEntity<?> deleteCustomerAccount(@RequestParam("id") Integer id) {
		if(id.toString() == null || id.toString().isEmpty())
			throw new CommonException("Parameter id must not be empty");
		
		System.out.println("id == >"+id);
		return new ResponseEntity<Object>(ResponseEntity.ok().body(customerService.deleteAccount(id)), HttpStatus.OK);
	}
}
