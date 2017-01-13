package com.liang.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/parameter1")
public class RequestParameterController {
	/**
	 * 匹配请求如：http://×××/parameter1?create
	 * @return
	 */
	//@RequestMapping(params="!create",method=RequestMethod.GET)//请求参数不包含create参数名
	@RequestMapping(params="create",method=RequestMethod.GET)//类级别的@RequestMapping窄化
	public String showForm(){
		System.out.println("##########GET");
		return "customer/create";
	}
	
	@RequestMapping(params="create",method=RequestMethod.POST)
	public String submit(){
		System.out.println("##########POST");
		return "redirect:/success";
	}
}
