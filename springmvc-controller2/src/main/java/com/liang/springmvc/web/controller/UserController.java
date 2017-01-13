package com.liang.springmvc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.liang.springmvc.model.UserModel;
import com.liang.springmvc.service.IUserService;

public class UserController extends MultiActionController {
	//用户服务类
	private IUserService userService;
	//逻辑视图名，通过依赖注入方式注入，可配置
	private String createView;
	private String updateView;
	private String deleteView;
	private String listView;
	private String redirectToListView;
	
	public String create(HttpServletRequest request, HttpServletResponse response, UserModel user){
		if("GET".equals(request.getMethod())){
			//如果是get请求，转向新增页面
			return getCreateView();
		}
		userService.create(user);
		//直接重定向到列表页面
		return getRedirectToListView();
	}
	
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response, UserModel user){
		if("GET".equals(request.getMethod())){
			//如果是GET请求，则转向更新页面
			ModelAndView mv = new ModelAndView();
			mv.addObject("command",userService.get(user.getUsername()));
			mv.setViewName(getUpdateView());
			return mv;
		}
		userService.update(user);
		return new ModelAndView(getRedirectToListView());
	}
	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, UserModel user){
		if("GET".equals(request.getMethod())){
			//转向查看删除页面
			ModelAndView mv = new ModelAndView();
			mv.addObject("command",userService.get(user.getUsername()));
			mv.setViewName(getDeleteView());
			return mv;
		}
		userService.delete(user);
		return new ModelAndView(getRedirectToListView());
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, UserModel user){
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList",userService.list());
		mv.setViewName(getListView());
		return mv;
	}
	
	
	//如果使用委托方式，命令对象只能是command
	@Override
	protected String getCommandName(Object command) {
		//命令对象的名字，默认是command
		return "command";
	}
	
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String getCreateView() {
		return createView;
	}
	public void setCreateView(String createView) {
		this.createView = createView;
	}
	public String getUpdateView() {
		return updateView;
	}
	public void setUpdateView(String updateView) {
		this.updateView = updateView;
	}
	public String getDeleteView() {
		return deleteView;
	}
	public void setDeleteView(String deleteView) {
		this.deleteView = deleteView;
	}
	public String getListView() {
		return listView;
	}
	public void setListView(String listView) {
		this.listView = listView;
	}
	public String getRedirectToListView() {
		return redirectToListView;
	}
	public void setRedirectToListView(String redirectToListView) {
		this.redirectToListView = redirectToListView;
	}
	
}
