package com.akshith.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.akshith.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	List<Users> findByUsernameAndPassword(String username, String password);

	UserDetails findByUsername(String username);
	

}
