package com.liang.springmvc.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.liang.springmvc.model.UserModel;

public class CanCancelRegisterSimpleFormController extends CancellableFormController {
	public CanCancelRegisterSimpleFormController(){
		setCommandClass(UserModel.class);//设置命令对象实现类
		setCommandName("user");//设置命令对象的名字
	}
	
	//form object表单对象，提供展示表单时的表单数据(使用commandName放入请求)
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		UserModel user = new UserModel();
		user.setUsername("请输入用户名");
		return user;
	}
	
	//提供展示表单时需要的一些其他数据
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		map.put("cityList", Arrays.asList("山东","北京","上海"));
		return map;
	}
	
	@Override
	protected void doSubmitAction(Object command) throws Exception {
		UserModel user = (UserModel)command;
		System.out.println(user);
	}
	
	@Override
	protected ModelAndView onCancel(Object command) throws Exception {
		UserModel user = (UserModel)command;
		System.out.println(user);
		//父类的onCancel方法默认返回cancelView属性指定的逻辑视图名
		return super.onCancel(command);
	}
	
	
}
