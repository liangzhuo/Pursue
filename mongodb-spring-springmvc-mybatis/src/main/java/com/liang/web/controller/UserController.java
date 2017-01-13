package com.liang.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liang.model.User;
import com.liang.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/userList")
	public String getUserList(Model model){
		User user = new User();
		List<User> userList = userService.getList(user);
		model.addAttribute("userList", userList);
		return "user/userList";
	}
	
	@RequestMapping("/initAddUser")
	public String initAddUser(){
		return "user/addOrUpdateUser";
	}
	
	@RequestMapping("/initUpdateUser")
	public String initUpdateUser(Integer id, Model model){
		User user = userService.selectById(id);
		model.addAttribute("user", user);
		return "user/addOrUpdateUser";
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, Model model){
		userService.saveUser(user);
		return "redirect:/user/userList";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id, Model model){
		userService.deleteById(id);
		return "redirect:/user/userList";
	}
}
