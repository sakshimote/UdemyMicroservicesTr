package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class CourseController {
	
	@GetMapping("/get")
	public String getHello(){
		return "hello world";
	}

}
