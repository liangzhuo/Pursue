package com.liang.springmvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HelloWorldWithoutModelAndViewController extends AbstractController{
	
	/**
	 * 通过response写响应
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().write("Hello World!");
		//如果直接在该处理器中写响应 可以通过返回null告诉DispatcherServlet自己已经写出响应了
		return null;
	}
	
}
