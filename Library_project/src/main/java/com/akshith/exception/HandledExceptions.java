package com.akshith.exception;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//public class HandledExceptions {
//
//	@ExceptionHandler(value=AuthorizationDeniedException.class)
//	public String AuthorizationHandler(AuthorizationDeniedException ex,Model model) {
//		model.addAttribute("error",ex.getMessage());
//		return "error";
//	}
//	
//	@ExceptionHandler(value=Exception.class)
//	public String eHandler(Exception ex,Model model) {
//		model.addAttribute("error",ex.getMessage());
//		return "index";
//	}
//	
//}
