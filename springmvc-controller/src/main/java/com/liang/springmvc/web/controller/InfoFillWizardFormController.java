package com.liang.springmvc.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.liang.springmvc.model.UserModel;

public class InfoFillWizardFormController extends AbstractWizardFormController {

	public InfoFillWizardFormController() {
		setCommandClass(UserModel.class);
		setCommandName("user");
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request, int page)
			throws Exception {
		Map map = new HashMap();
		if(page == 1){//学校信息需要学校类型信息
			map.put("schoolTypeList", Arrays.asList("高中","中专","大学"));
		}
		if(page == 2){//工作信息需要工作城市信息
			map.put("cityList", Arrays.asList("广州","上海","深圳"));
		}
		return map;
	}
	
	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		//提供每一页数据的验证处理方法
	}
	
	@Override
	protected void postProcessPage(HttpServletRequest request, Object command,
			Errors errors, int page) throws Exception {
		//提供每一页完成时的后处理方法
		UserModel user = null;
		if(page == 0){
			user = (UserModel)command;
		}
		if(page == 1){
			user = (UserModel)command;
		}
		if(page == 2){
			user = (UserModel)command;
		}
	}
	
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		//成功后的处理方法
		System.out.println(command);
		return new ModelAndView("redirect:/success");
	}
	
	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		//取消后的处理方法
		System.out.println(command);
		return new ModelAndView("redirect:/cancel");
	}

}
