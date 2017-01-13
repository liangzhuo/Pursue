package com.liang.springmvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.liang.springmvc.model.UserModel;

public class MyAbstractCommandController extends AbstractCommandController {
	public MyAbstractCommandController() {
		//设置命令对象实现类
		setCommandClass(UserModel.class);
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserModel user = (UserModel)command;
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",user);
		mv.setViewName("abstractCommand");
		return mv;
	}

}
