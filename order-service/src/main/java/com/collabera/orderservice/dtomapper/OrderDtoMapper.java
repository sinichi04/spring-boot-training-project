package com.collabera.orderservice.dtomapper;

import org.springframework.stereotype.Component;

import com.collabera.orderservice.dto.OrderDto;
import com.collabera.orderservice.model.Orders;

@Component
public class OrderDtoMapper {

	public Orders getOrder(OrderDto orderDto) {
		Orders orders = new Orders();
		
		orders.setCustomerid(orderDto.getCustomerid());
		orders.setProductid(orderDto.getProductid());
		orders.setQuantity(orderDto.getQuantity());
		return orders;
	}
	
}
