package com.example.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

//	http://localhost:8080/sayHello/Priya
	@GetMapping("/greet")
	public String greet() {
		return "Have a great day!!!";
	}
	
//	http://localhost:8080/sayHello/Priya
	@GetMapping("/sayHello/{username}")
	public String greet(@PathVariable("username") String name) {
		return "Welcome !!!"+name;
	}
	
//	http://localhost:8080/sayHello/Priya/city/Bangalore
	@GetMapping("/sayHello/{username}/city/{city}")
	
//	@GetMapping("/print/{username}/{city}")
	public String greet(@PathVariable("username") String name,@PathVariable("city")String mcity) {
		return "Welcome "+name +" from "+mcity;
	}
	
//	http://localhost:8080/show?name=Sri&salary=1000
	@GetMapping("/show")
	public String show(@RequestParam("name") String name,@RequestParam("salary")int salary) {
		return "Welcome "+name +" . Your salary is  "+salary;
	}
	
//	http://localhost:8080/showFruits
	@GetMapping("/showFruits")
	public List<String> showFruits() {
		return Arrays.asList("apple","kiwi");
	}
	
}
