package com.collabera.orderservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collabera.orderservice.dto.ProductDto;
import com.collabera.orderservice.dto.UpdateOrderProductDto;
import com.collabera.orderservice.dtomapper.ProductDtoMapper;
import com.collabera.orderservice.exception.CommonException;
import com.collabera.orderservice.exception.OrderNotFoundException;
import com.collabera.orderservice.model.Product;
import com.collabera.orderservice.repository.ProductRepository;
import com.collabera.orderservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDtoMapper productDtoMapper;
	
	@Override
	public List<Product> getProducts() {
		List<Product> productList = productRepository.findAll();
		
		if(productList == null)
			throw new OrderNotFoundException("Product Data Not Found");
		
		return productList;
	}

	@Override
	public String addProduct(ProductDto productDto) {
		try {
			productRepository.save(productDtoMapper.getProduct(productDto));
		}catch (Exception e) {
			throw new CommonException("Error In Adding Product");
		}
		return "Product Successfully Addded";
	}

	@Override
	public String updateProduct(Integer productId, UpdateOrderProductDto updateOrderProductDto) {
		try {
			Product product = productRepository.findById(productId).get();
			
			productRepository.save(productDtoMapper.updateProduct(updateOrderProductDto, product));
		}catch (Exception e) {
			throw new OrderNotFoundException("Product Data Not Found");
		}
		return "Product Updated Successfully";
	}

	@Override
	public String deleteProduct(Integer productId) {
		try {
			Product product = productRepository.findById(productId).get();
			
			productRepository.delete(product);
		}catch (Exception e) {
			throw new OrderNotFoundException("Product Data Not Found");
		}
		return "Product Successfully Deleted";
	}

}
