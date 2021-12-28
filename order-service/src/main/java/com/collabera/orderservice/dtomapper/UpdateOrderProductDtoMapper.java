package com.collabera.orderservice.dtomapper;

import org.springframework.stereotype.Component;

import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.model.Orders;

@Component
public class UpdateOrderProductDtoMapper {

	public Orders updateOrderQuantity(UpdateOrderProductDto updateOrderProductDto, Orders order) {
		
		order.setQuantity(updateOrderProductDto.getOrderquantity());
		return order;
	}
}
