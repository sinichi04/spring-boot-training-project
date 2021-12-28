package com.collabera.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<Object> handler(CommonException commonException){
		return new ResponseEntity<Object>(ResponseEntity.badRequest().body(commonException.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
