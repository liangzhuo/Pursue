package com.liang.springmvc.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.liang.springmvc.model.DataBindTestModel;
import com.liang.springmvc.model.PhoneNumberModelEditor;
import com.liang.springmvc.model.PhoneNumberModel;

public class DataBinderTestController extends AbstractCommandController {

	public DataBinderTestController() {
		setCommandClass(DataBindTestModel.class);
		setCommandName("dataBinder");
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		System.out.println(command);
		return new ModelAndView("bindAndValidate/success").addObject("dataBinder",command);
	}
	
	/**
	 * 该方法绑定是Controller独享的
	 */
	/*
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		//注册自定义的属性编辑器
		//1.日期
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		//表示如果命令对象有Date类型的属性，将使用属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
		//自定义的电话号码编辑器
		binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());
	}
	*/

}
