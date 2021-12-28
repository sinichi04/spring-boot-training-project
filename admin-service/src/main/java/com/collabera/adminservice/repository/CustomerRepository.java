package com.collabera.adminservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.collabera.adminservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	List<Customer> findByStatus(String status);
	
	Customer findByCustomerid(Integer id);
	
	@Query(value = "SELECT * FROM CUSTOMER INNER JOIN USER ON USER.ID = CUSTOMER.CUSTOMERID WHERE USER.USERNAME = :username AND "
			+ "PASSWORD = :password", nativeQuery = true)
	Customer getCustomerByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	
	@Query(value = "SELECT * FROM CUSTOMER WHERE FIRSTNAME LIKE %:str%", nativeQuery = true)
	List<Customer> searchCustomer(@Param("str") String str);
}