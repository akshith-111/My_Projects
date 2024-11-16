package com.akshith.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.akshith.entity.Books;
import com.akshith.service.AdminService;
import com.akshith.service.BookService;



@Controller
public class AdminController {
	@Autowired
	BookService bookService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/showBooks")
	public String showBooks(Model model) {
		List<Books> books=adminService.getAllBooks();
		model.addAttribute("books",books);
		return "showbooks";
	}
	
	
	@GetMapping("/deleteRecord/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteRecord(@PathVariable("id") Integer id) {
		adminService.deleteBook(id);
		return "redirect:/showBooks";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addBooks")
	public String addBook(@Validated Books book,BindingResult result) {
		if(result.hasErrors())return"addbooks";
		adminService.addBook(book);
		return "redirect:/add";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute(new Books());
		return "addbooks";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/status")
	public String checkStaus(Model model) {
		
		model.addAttribute("requests",bookService.checkStatus());
		return "requests";
	}
	
	
	
	

}
