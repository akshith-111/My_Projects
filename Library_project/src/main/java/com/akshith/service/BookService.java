package com.akshith.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshith.entity.Books;
import com.akshith.entity.BorrowRequests;
import com.akshith.repositry.LibraryRepo;
import com.akshith.repositry.RequestsRepo;

@Service
public class BookService {
	@Autowired
	RequestsRepo repo;
	
	@Autowired
	LibraryRepo libraryRepo;
	
	public List<BorrowRequests> checkStatus(){
		List<BorrowRequests> list=repo.findAll();
		list=list.stream().filter(o->o.getStatus().toUpperCase().equals("WAITING"))
		.collect(Collectors.toList());
		return list;
	}
	
	public Books getBook(Integer id) {
		Optional<Books> opt=libraryRepo.findById(id);
		return opt.get();
	}
	
	
}
