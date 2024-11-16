package com.akshith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshith.entity.Books;
import com.akshith.entity.BorrowRequests;
import com.akshith.entity.Users;
import com.akshith.repositry.RequestsRepo;
import com.akshith.service.AdminService;
import com.akshith.service.BookService;
import com.akshith.service.RequestsService;
import com.akshith.service.UserService;

@Controller
public class RequestsController {

	@Autowired
	RequestsService requestsService;

	@Autowired
	RequestsRepo repo;

	@Autowired
	UserService service;

	@Autowired
	BookService bookService;

	@Autowired
	AdminService adminService;

	@GetMapping("/handleRequests/{id}")
	public String acceptRequests(@PathVariable("id") Integer id, @RequestParam("status") String status, Model model)
			throws Exception {
		BorrowRequests br = requestsService.getRequests(id);
		if (status.toUpperCase().equals("ACCEPT")) {
			
			Users u = service.getUser(br.getUserId());
			Books b = bookService.getBook(br.getBookId());
			
			b.setAvail(b.getAvail()-1);
			
			u.getBooks().add(b);
			b.getUsers().add(u);
			br.setStatus(status);

			requestsService.saveRequests(br);
			service.addUser(u);
			adminService.addBook(b);
		}
		br.setStatus(status);
		requestsService.saveRequests(br);
		return "redirect:/status";
	}
	
	
}
