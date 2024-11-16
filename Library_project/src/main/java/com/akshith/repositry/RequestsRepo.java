package com.akshith.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshith.entity.BorrowRequests;

public interface RequestsRepo extends JpaRepository<BorrowRequests,Integer> {

	
}
