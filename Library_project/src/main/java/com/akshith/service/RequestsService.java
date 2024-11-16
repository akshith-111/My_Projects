package com.akshith.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshith.entity.BorrowRequests;
import com.akshith.repositry.RequestsRepo;

@Service
public class RequestsService {

	@Autowired
	RequestsRepo repo;
	
	public BorrowRequests getRequests(Integer id) throws Exception {
		Optional<BorrowRequests> opt= repo.findById(id);
		if(opt.isPresent())
		return opt.get();
		throw new Exception();
	}
	public void saveRequests(BorrowRequests borrowRequests) {
		repo.save(borrowRequests);
		
	}
}
