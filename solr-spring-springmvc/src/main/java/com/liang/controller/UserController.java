package com.liang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liang.model.User;
import com.liang.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/{id}")
	public String showUser(@PathVariable("id") String id, Model model) {
		System.out.println(id);
		return "userList";
	}
	
	@RequestMapping("/list")
	public String showAllUser(Model model) {
		Pageable pageable = new PageRequest(0, 10);
		Page<User> users = userRepository.findAll(pageable);
		model.addAttribute("users", users);
		return "userList";
	}
}
