package com.liang.springmvc.web.controller.paramtype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderValueTypeController {
	@RequestMapping
	public String test(
			@RequestHeader("userAgent") String userAgent,
			@RequestHeader("accepts") String[] accepts
			){
		return "success";
	}
}
