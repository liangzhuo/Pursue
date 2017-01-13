package com.liang.springmvc.web.controller.paramtype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liang.springmvc.model.UserModel;

public class ModelAttributeTypeController {
	@RequestMapping(value="/model1")
	public String test1(@ModelAttribute("user") UserModel user){
		return "success";
	}
	
	@RequestMapping(value="/model2/{username}")
	public String test2(@ModelAttribute("user") UserModel user){
		return "success";
	}
	
	@ModelAttribute("cityList")
	public List<String> cityList(){
		return Arrays.asList("北京","上海");
	}
	
	@ModelAttribute("user")
	public UserModel getUser(@RequestParam(value="username") String username){
		UserModel user = new UserModel();
		user.setUsername("liangz");
		return user;
	}
	
	public @ModelAttribute("user2") UserModel test3(@ModelAttribute("user2") UserModel user){
		return user;
	}
	
	public String test4(@ModelAttribute UserModel user,Model model){
		return "success";
	}
	
	public String test5(UserModel user,Model model){
		return "success";
	}
	
	public @ModelAttribute List<String> test6(){
		return new ArrayList<String>();
	}
	
	public @ModelAttribute List<UserModel> test7(){
		return new ArrayList<UserModel>();
	}
}
