package com.collabera.orderservice.service;

import java.util.List;

import com.collabera.orderservice.dto.ProductDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.model.Product;

public interface ProductService {

	public List<Product> getProducts();
	
	public String addProduct(ProductDto productDto);
	
	public String updateProduct(Integer productId, UpdateOrderProductDto updateOrderProductDto);
	
	public String deleteProduct(Integer productId);
}
