package com.liang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/system")
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
}
