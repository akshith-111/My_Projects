package com.akshith.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshith.entity.Books;
import com.akshith.repositry.LibraryRepo;

@Service
public class AdminService {

	@Autowired 
	private LibraryRepo libraryRepo;
	
	
	public void deleteBook(int id) {
		libraryRepo.deleteById(id);
	}
	
	public void addBook(Books book) {
		libraryRepo.save(book);
		}

	public List<Books> getAllBooks() {
	return	libraryRepo.findAll();
		
	}

	public void addOne(Integer id) {
	
		
	}
	
	
}
