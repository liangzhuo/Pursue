package com.liang.springmvc.web.controller.paramtype;

import javax.servlet.http.Cookie;

import org.springframework.web.bind.annotation.CookieValue;

public class CookieValueTypeController {
	public String test(@CookieValue(value="JSESSIONID", defaultValue="") String sessionId){
		return "success";
	}
	
	public String test(@CookieValue(value="JSESSIONID",defaultValue="") Cookie sessionId){
		return "success";
	}
}	
