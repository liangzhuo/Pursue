package com.liang.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/parameter2")
public class RequestParameterController2 {
	/**
	 * 表示请求中有"submitFlag=create"请求参数且请求方法为 "GET"即可匹配
	 * @return
	 */
	//@RequestMapping(params="submitFlag!=create",method=RequestMethod.GET)
	@RequestMapping(params="submitFlag=create",method=RequestMethod.GET)//类级别的@RequestMapping窄化
	public String showForm(){
		System.out.println("##########GET");
		return "customer/create";
	}
	
	@RequestMapping(params="submitFlag=create",method=RequestMethod.POST)
	public String submit(){
		System.out.println("##########POST");
		return "redirect:/success";
	}
}
