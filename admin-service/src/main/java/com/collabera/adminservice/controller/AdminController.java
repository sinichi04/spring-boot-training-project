package com.collabera.adminservice.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.adminservice.dto.AccountCreationDto;
import com.collabera.adminservice.dto.AccountDto;
import com.collabera.adminservice.exception.CommonException;
import com.collabera.adminservice.service.AdminService;
import com.collabera.adminservice.service.ValidationService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ValidationService validationService;
	
	@GetMapping("/getCustomerDetails")
	public ResponseEntity<Object> getCustomerDetails(){
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.displayAccount()), HttpStatus.OK);
	}
	
	@PostMapping("/createCustomerAccount")
	public ResponseEntity<?> createCustomerAccount(@Valid @RequestBody AccountCreationDto accountDto, BindingResult bindingResult){
		
		Map<String, String> errorMap = validationService.validate(bindingResult);
		if(errorMap.isEmpty())
			return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.createAccount(accountDto)), HttpStatus.OK);
		else
			return new ResponseEntity<Object>(ResponseEntity.badRequest().body(errorMap), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateCustomerAccount") 
	public ResponseEntity<Object> updateCustomerAccount(@RequestParam("id") Integer id, @RequestBody AccountDto customerDto){
		System.out.println("this is id: "+id);
		if(id == null)
			throw new CommonException("Parameter id must not be empty");
		
		if(customerDto == null)
			throw new CommonException("Must have at least one(1) customer detail");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.updateCustomer(id, customerDto)), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomerById")
	public ResponseEntity<Object> deleteCustomerById(@RequestParam("id") Integer id){
		
		if(id == null)
			throw new CommonException("Parameter id must not be empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.deleteCustomerById(id)), HttpStatus.OK);
	}

	@GetMapping("/getNotActiveCustomerDetails")
	public ResponseEntity<Object> getNotActiveCustomerDetails(){
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.getNotActiveCustomerDetails()), HttpStatus.OK);
	}

	@PutMapping("/approveAccount")
	public ResponseEntity<Object> approveAccount(@RequestParam("id") Integer id){
		
		if(id == null)
			throw new CommonException("Parameter id must not be empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.approveAccount(id)), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Object> searchAccount(@RequestParam("name") String name){
		if(name.isEmpty())
			throw new CommonException("Parameter name must not be empty!");
			
		return new ResponseEntity<Object>(adminService.search(name), HttpStatus.OK);
	}
	
	@GetMapping("/getCustomersOrder")
	public ResponseEntity<Object> getCustomersOrder(){
		return new ResponseEntity<Object>(ResponseEntity.ok().body(adminService.getCustomerOrder()), HttpStatus.OK);
	}
}
