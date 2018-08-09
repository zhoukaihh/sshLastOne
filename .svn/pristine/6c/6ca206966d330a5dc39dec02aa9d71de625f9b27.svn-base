package com.qfedu.SSHDemo.controller;

import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handler (Exception e){
		String error;
		try {
			error = URLEncoder.encode(e.getMessage(), "UTF-8");
		} catch (Exception e2) {
			error="未知异常";
		}
		
		return "redirect:/login?error="+error;
	}
	
	
}
