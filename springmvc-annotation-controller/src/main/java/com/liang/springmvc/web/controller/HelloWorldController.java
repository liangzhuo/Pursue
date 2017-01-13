package com.liang.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 使用@Controller或@RequestMapping把一个POJO类变身为处理器
 * @RequestMapping(value="/hello") 请求到处理器功能处理方法的映射
 * 类级别@RequestMapping 处理器通用请求前缀
 * 方法级别@RequestMapping 对类级别映射的窄化
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/user")
public class HelloWorldController {
	@RequestMapping(value="/hello")
	public ModelAndView helloWorld(){
		//1.收集参数
		//2.绑定参数到命令对象
		//3.调用业务对象
		//4.选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.addObject("message","hello world");
		mv.setViewName("hello");
		return mv;
	}
}
