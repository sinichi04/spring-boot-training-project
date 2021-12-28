package com.collabera.orderservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.orderservice.dto.OrderDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.dtomapper.OrderDtoMapper;
import com.collabera.orderservice.dtomapper.UpdateOrderProductDtoMapper;
import com.collabera.orderservice.exception.CommonException;
import com.collabera.orderservice.exception.OrderNotFoundException;
import com.collabera.orderservice.model.Orders;
import com.collabera.orderservice.model.Product;
import com.collabera.orderservice.repository.OrderRepository;
import com.collabera.orderservice.repository.ProductRepository;
import com.collabera.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderDtoMapper orderDtoMapper;
	
	@Autowired
	private UpdateOrderProductDtoMapper updateOrderProductDtoMapper;
		
	@Override
	public List<Orders> getCustomerOrders(Integer customerId) {
		
		List<Orders> customerOrders = new ArrayList<>(), orderList = orderRepository.findByCustomerid(customerId);
		
		if(orderList == null)
			throw new OrderNotFoundException("Order Data Not Found");
		
		for(Orders orders : orderList) {
			List<Product> productList = productRepository.findByProductId(orders.getProductid());
			
			if(productList != null)
				orders.setProducts(productList);
				
			customerOrders.add(orders);
		}
		
		return customerOrders;
	}

	@Override
	public String addOrder(OrderDto orderDto) {
		try {
			orderRepository.save(orderDtoMapper.getOrder(orderDto));
		}catch (Exception e) {
			throw new CommonException("Error In Adding Order");
		}
		
		return "Order Successfully Added";
	}

	@Override
	public String updateOrder(Integer orderId, UpdateOrderProductDto updateOrderProductDto) {
		try {
			Orders order = orderRepository.findById(orderId).get();
			
			orderRepository.save(updateOrderProductDtoMapper.updateOrderQuantity(updateOrderProductDto, order));
		}catch (Exception e) {
			throw new OrderNotFoundException("Order Data Not Found");
		}
		return null;
	}

	@Override
	public String deleteOrder(Integer orderId) {
		Orders orders = orderRepository.findById(orderId).get();
		
		if(orders == null)
			throw new CommonException("Order Data Not Found");
		
		orderRepository.delete(orders);
		return "Order Successfully Deleted";
	}

}
