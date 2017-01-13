package com.liang.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liang.springmvc.model.UserModel;
import com.liang.springmvc.web.controller.support.validator.UserModelValidator;

@Controller
public class RegisterSimpleFormController {
	private UserModelValidator validator = new UserModelValidator();
	
	@ModelAttribute("user")		//暴露表单引用对象为模型数据
	public UserModel getUser(){
		return new UserModel();
	}
	
	@RequestMapping(value="/validator", method=RequestMethod.GET)
	public String showRegisterForm(){
		return "validate/registerAndValidator";
	}
	
	@RequestMapping(value="/validator", method=RequestMethod.POST)
	public String submitForm(
			@ModelAttribute("user") UserModel user,
			Errors errors
			){
		validator.validate(user, errors);
		if(errors.hasErrors()){
			return showRegisterForm();
		}
		return "redirect:/success";
	}
	
	@RequestMapping("/success")
	public String success(){
		return "validate/success";
	}
}
