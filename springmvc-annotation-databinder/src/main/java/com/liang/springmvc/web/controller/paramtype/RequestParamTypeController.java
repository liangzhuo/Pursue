package com.liang.springmvc.web.controller.paramtype;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestParamTypeController {
	/**
	 * @RequestParame 这里最好写出参数名称
	 * @param username
	 * @return
	 */
	public String requestParam1(@RequestParam String username){
		return "success";
	}
	
	public String requestParam2(@RequestParam("username") String username){
		return "success";
	}
	
	/**
	 * 如果请求中没有名字为username的参数时，默认为null
	 * @param username
	 * @return
	 */
	public String requestParam4(@RequestParam(value="username",required=false) String username){
		return "success";
	}
	
	public String requestParam5(@RequestParam(value="useranme",required=true,defaultValue="liangz") String username){
		return "success";
	}
	
	public String requestParam6(@RequestParam(value="role") String roleList){
		return "success";
	}
	
	
	public String requestParam7(@RequestParam(value="role") String[] roleList){
		return "success";
	}
	
	public String requestParam8(@RequestParam(value="list") List<String> list){
		return "success";
	}
}
