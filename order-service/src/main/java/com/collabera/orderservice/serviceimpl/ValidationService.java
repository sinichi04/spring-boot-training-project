package com.collabera.orderservice.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationService {

	public Map<String, String> validate(BindingResult bindingResult){
		Map<String, String> map = new HashMap<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return map;
	}
}
