package com.liang.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liang.mybatis.model.Article;
import com.liang.mybatis.service.IUserService;

@Controller
@RequestMapping("/article")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/list")
	public ModelAndView listAll(){
		List<Article> articles = userService.getUserArticles(3);
		ModelAndView view = new ModelAndView();
		view.setViewName("list");
		view.addObject("articles",articles);
		return view;
	}
}
