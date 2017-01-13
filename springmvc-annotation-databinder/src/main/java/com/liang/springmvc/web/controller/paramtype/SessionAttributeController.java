package com.liang.springmvc.web.controller.paramtype;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.liang.springmvc.model.UserModel;

//1.在控制器类头上添加@SessionAttributes注解
@SessionAttributes(value = {"user"})
public class SessionAttributeController {
	
	//2.@ModelAttribute注解的方法进行表单引用对象的创建
	@ModelAttribute("user")
	public UserModel initUser(){
		UserModel user = new UserModel();
		return user;
	}
	
	//3.@RequestMapping注解方法的@ModelAttribute注解的参数进行命令对象的绑定
	@RequestMapping("/session1")
	public String session1(@ModelAttribute("user") UserModel user){
		return "success";
	}
	
	//4.通过SessionStatus的setComplete方法清除@SessionAttributes指定的会话数据
	@RequestMapping("/session2")
	public String session2(@ModelAttribute("user") UserModel user, SessionStatus status){
		if(true){
			status.setComplete();
		}
		return "success";
	}
}
