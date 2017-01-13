package com.liang.springmvc.web.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liang.springmvc.model.FormatterModel;
import com.liang.springmvc.model.PhoneNumberModel;
import com.liang.springmvc.web.controller.support.fomatter.PhoneNumber;

@Controller
public class DataFormatterController {
	@RequestMapping(value="/format1")
	public String test1(@ModelAttribute("model") FormatterModel formatterModel){
		return "format/success";
	}
	
	@RequestMapping(value="/format2")
	public String test2(
			@PhoneNumber @RequestParam("phoneNumber") PhoneNumberModel phoneNumber,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("date") Date date
			){
		System.out.println(phoneNumber);
		System.out.println(date);
		return "format/success";
	}
}
