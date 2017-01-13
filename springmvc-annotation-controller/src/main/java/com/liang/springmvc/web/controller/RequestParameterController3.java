package com.liang.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/parameter3")
public class RequestParameterController3 {
	@RequestMapping(params={"test1","test2=create"},method=RequestMethod.GET)//类级别的@RequestMapping窄化
	public String showForm(){
		System.out.println("##########GET");
		return "customer/create";
	}
	
	@RequestMapping(params={"test1","test2=create"},method=RequestMethod.POST)
	public String submit(){
		System.out.println("##########POST");
		return "redirect:/success";
	}
}
