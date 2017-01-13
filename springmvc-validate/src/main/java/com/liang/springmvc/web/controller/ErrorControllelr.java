package com.liang.springmvc.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.liang.springmvc.model.DataBindTestModel;

public class ErrorControllelr extends AbstractCommandController {

	public ErrorControllelr() {
		setCommandClass(DataBindTestModel.class);
		setCommandName("command");
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		//注册错误码
		
		//表示用户名不能为空
		errors.reject("username.not.empty");
		//带有默认错误消息
		errors.reject("username.not.empty1","用户名不能为空");
		//带有参数和默认错误消息
		errors.reject("username.length.error", new Object[]{5,10}, "用户名长度不正确");
		
		//得到错误相关的模型数据
		Map model = errors.getModel();
		return new ModelAndView("bindAndValidate/error",model);
	}

}
