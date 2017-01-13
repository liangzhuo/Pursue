package com.liang.springmvc.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.liang.springmvc.model.DataBindTestModel;
import com.liang.springmvc.model.PhoneNumberModel;
import com.liang.springmvc.web.controller.support.editor.PhoneNumberModelEditor;

@Controller
public class DataBinderTestController {
	@RequestMapping(value = "/databind")
	public String test(DataBindTestModel command){
		System.out.println(command);
		return "bind/success";
	}
	
	/**
	 * 控制器独享
	 */
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//注册自定义的属性编辑器
		//1.日期
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		//表示如果命令对象有Date类型的属性，将使用属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
		//自定义的电话号码编辑器
		binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberModelEditor());
	}
	*/
}
