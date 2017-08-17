package com.zyff.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class FirstController {
	
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/hello/{name}")
	public String index(@PathVariable String name) {
		return "Hello " + name + "!!!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FirstController.class, args);
	}
}
