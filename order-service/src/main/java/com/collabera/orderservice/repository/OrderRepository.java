package com.collabera.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.collabera.orderservice.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	@Query(value = "SELECT * FROM CUSTOMER_ORDER WHERE CUSTOMERID = :customerid", nativeQuery = true)
	List<Orders> findByCustomerid(@Param("customerid") Integer customerId);
}
