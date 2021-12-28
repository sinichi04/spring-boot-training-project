package com.collabera.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.collabera.adminservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{	
	
	@Query(value = "SELECT * FROM USER ORDER BY ID DESC LIMIT 1",nativeQuery = true)
	User getLatestUser();
	
	User findByUsernameAndPassword(String username, String password);
}
