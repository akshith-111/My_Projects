package com.akshith.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshith.entity.Books;
import com.akshith.entity.BorrowRequests;
import com.akshith.entity.Users;
import com.akshith.service.AdminService;
import com.akshith.service.BookService;
import com.akshith.service.RequestsService;
import com.akshith.service.UserService;

@Controller
public class userController {

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;
	
	@Autowired
	RequestsService requestsService;
	
	@Autowired
	AdminService adminService;

	
	@GetMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Users user = (Users) authentication.getPrincipal();
		List<Books> books=userService.getBooks();
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("user", (Users)user);
		m.put("books",books);
		model.addAllAttributes(m);
		return "userhome";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/requests/{uid}")
	public String handleRequests(@PathVariable("uid") Integer uid,@RequestParam("bid") Integer bid,@RequestParam("btitle") String title) {
//		Users u=userService.getUser(uid);
//		Books b=bookService.getBook(bid);
//		u.getBooks().add(b);
//		b.getUsers().add(u);
		
		BorrowRequests borrowRequests =new BorrowRequests(uid,title,bid,new Date(),"Waiting");
		
		requestsService.saveRequests(borrowRequests);
		
		
		return "redirect:/";
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/userBooks/{id}")
	public String userBooks(@PathVariable("id") Integer id,Model model) {
		Set<Books> bookList=userService.getUserBooks(id);
		System.out.println(bookList);
		model.addAttribute("books",bookList);
		return "userbooks";
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/return/{bid}")
	public String returnBook(@PathVariable Integer bid,@RequestParam Integer uid) {
		Users u=userService.getUser(uid);
		Books b=bookService.getBook(bid);
		
		u.getBooks().remove(b);
		b.getUsers().remove(u);
		
		
		b.setAvail(b.getAvail()+1);
		
		userService.addUser(u);
		adminService.addBook(b);
		
		
		return "redirect:/userBooks/"+uid;
	}
	
	
	
}
