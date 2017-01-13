package com.liang.springmvc.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.liang.springmvc.model.DataBindTestModel;

public class SuccessControllelr extends AbstractCommandController {

	public SuccessControllelr() {
		setCommandClass(DataBindTestModel.class);
		setCommandName("command");
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return new ModelAndView("bindAndValidate/success");
	}

}
