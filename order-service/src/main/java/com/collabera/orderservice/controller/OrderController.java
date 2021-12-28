package com.collabera.orderservice.controller;

import java.util.List;
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

import com.collabera.orderservice.dto.OrderDto;
import com.collabera.orderservice.dto.ProductDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.exception.CommonException;
import com.collabera.orderservice.model.Orders;
import com.collabera.orderservice.service.OrderService;
import com.collabera.orderservice.service.ProductService;
import com.collabera.orderservice.serviceimpl.ValidationService;
import com.collabera.orderservice.util.CommonUtil;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private CommonUtil util;
	
	@GetMapping("/getCustomerOrder")
	public ResponseEntity<Object> getCustomerOrder(@RequestParam("id") Integer customerId){
		if(!util.isNotEmpty(customerId))
			throw new CommonException("Parameter Id Must Not Be Empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(orderService.getCustomerOrders(customerId)),HttpStatus.OK);
	}
	
	@GetMapping("/returnOrderList")
	public List<Orders> returnOrderList(@RequestParam("id") Integer customerId){
		if(!util.isNotEmpty(customerId))
			throw new CommonException("Parameter Id Must Not Be Empty");
		
		return orderService.getCustomerOrders(customerId);
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<Object> getProducts(){
		return new ResponseEntity<Object>(ResponseEntity.ok().body(productService.getProducts()),HttpStatus.OK);
	}
	
	@PostMapping("/addOrder")
	public ResponseEntity<?> addOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult){
		
		Map<String, String> errorMap = validationService.validate(bindingResult);
		if(errorMap.isEmpty())
			return new ResponseEntity<Object>(ResponseEntity.ok().body(orderService.addOrder(orderDto)), HttpStatus.OK);
		else
			return new ResponseEntity<Object>(ResponseEntity.badRequest().body(errorMap), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){
		
		Map<String, String> errorMap = validationService.validate(bindingResult);
		if(errorMap.isEmpty())
			return new ResponseEntity<Object>(ResponseEntity.ok().body(productService.addProduct(productDto)), HttpStatus.OK);
		else
			return new ResponseEntity<Object>(ResponseEntity.badRequest().body(errorMap), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateOrder")
	public ResponseEntity<?> updateOrder(@RequestParam("id") Integer orderId, @RequestBody UpdateOrderProductDto updateOrderProductDto){
		if(!util.isNotEmpty(updateOrderProductDto.getOrderquantity()))
			throw new CommonException("Order Id Must Not Be Empty");
			
		return new ResponseEntity<Object>(ResponseEntity.ok().body(orderService.updateOrder(orderId, updateOrderProductDto)),HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestParam("id") Integer productId, @RequestBody UpdateOrderProductDto updateOrderProductDto){
		if(!util.isNotEmpty(productId))
			throw new CommonException("Parameter Id Must Not Be Empty");
		
		if(updateOrderProductDto == null)
			throw new CommonException("Should Have Atleast One Product Detail");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(productService.updateProduct(productId, updateOrderProductDto)),HttpStatus.OK);
	} 
	
	@DeleteMapping("/deleteOrder")
	public ResponseEntity<?> deleteOrder(@RequestParam("id") Integer orderId){
		if(!util.isNotEmpty(orderId))
			throw new CommonException("Parameter Id Must Not Be Empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(orderService.deleteOrder(orderId)),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct")
	public ResponseEntity<?> deleteProduct(@RequestParam("id") Integer productId){
		if(!util.isNotEmpty(productId))
			throw new CommonException("Parameter Id Must Not Be Empty");
		
		return new ResponseEntity<Object>(ResponseEntity.ok().body(productService.deleteProduct(productId)),HttpStatus.OK);
	}
	
}
