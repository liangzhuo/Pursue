package com.zyff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String hello() {
		LOG.debug("访问hello!");
		return "hello world!";
	}
	
	@RequestMapping("/hello/{name}")
	public String helloName(@PathVariable String name) {
		LOG.debug("访问helloName, Name={}", name);
		return "hello " + name;
	}
	
}
