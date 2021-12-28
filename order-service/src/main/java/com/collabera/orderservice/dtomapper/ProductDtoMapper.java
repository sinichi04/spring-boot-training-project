package com.collabera.orderservice.dtomapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.collabera.orderservice.dto.ProductDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.model.Product;
import com.collabera.orderservice.util.CommonUtil;

@Component
public class ProductDtoMapper {

	@Autowired
	private CommonUtil util;
	
	public Product getProduct(ProductDto productDto){
		Product product = new Product();
		
		product.setProductname(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());
		return product;
	}
	
	public Product updateProduct(UpdateOrderProductDto updateOrderProductDto, Product product) {
		
		if(util.isNotEmpty(updateOrderProductDto.getName()))
			product.setProductname(updateOrderProductDto.getName());
		
		if(util.isNotEmpty(updateOrderProductDto.getPrice()))
			product.setPrice(updateOrderProductDto.getPrice());
		
		if(util.isNotEmpty(updateOrderProductDto.getQuantity()))
			product.setQuantity(updateOrderProductDto.getQuantity());
		
		return product;
	}
}
