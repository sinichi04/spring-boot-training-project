package com.collabera.orderservice.service;

import java.util.List;

import com.collabera.orderservice.dto.OrderDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.model.Orders;

public interface OrderService {

	public List<Orders> getCustomerOrders(Integer customerId);
	
	public String addOrder(OrderDto ordedrDto);
	
	public String updateOrder(Integer orderId, UpdateOrderProductDto updateOrderProductDto);
	
	public String deleteOrder(Integer orderId);
}
