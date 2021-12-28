package com.collabera.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.collabera.orderservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query(value = "SELECT * FROM PRODUCT WHERE ID = :productid", nativeQuery = true)
	List<Product> findByProductId(@Param("productid") Integer productid);
	
}
