package com.akshith.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginContoller {

	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
}
