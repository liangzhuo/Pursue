package com.liang.springmvc.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liang.springmvc.model.UserModel;

@Controller
public class HelloWorldController {
	@RequestMapping(value="/validator/hello")
	public String submitForm(
			@Valid @ModelAttribute("user") UserModel user,
			Errors errors
			){
		if(errors.hasErrors()){
			return "validate/error";
		}
		return "redirect:/success";
	}
	
	@RequestMapping("/success")
	public String success(){
		return "validate/success";
	}
}
