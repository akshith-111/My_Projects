package com.akshith.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshith.repositry.LibraryRepo;

@Controller
public class BookController {
	@Autowired
	LibraryRepo libraryRepo;
	
	@GetMapping("/search")
	public String search(@RequestParam("book") String title,Model model) {
		
		model.addAttribute("books",libraryRepo.findByTitleStartsWith(title));
		return "showbooks";
		
	}
}
