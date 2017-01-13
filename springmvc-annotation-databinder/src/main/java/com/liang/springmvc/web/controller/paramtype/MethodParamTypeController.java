package com.liang.springmvc.web.controller.paramtype;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liang.springmvc.model.UserModel;

@Controller
public class MethodParamTypeController {
	/**
	 * SpringMVC框架会自动帮助我们把响应的Servlet请求/响应作为参数传过来
	 * @param servletRequest
	 * @param httpServletRequest
	 * @param servletResponse
	 * @param httpServletResponse
	 * @return
	 */
	public String requestOrResponse(
			ServletRequest servletRequest, HttpServletRequest httpServletRequest,
			ServletResponse servletResponse, HttpServletResponse httpServletResponse){
		return null;
	}
	
	/**
	 * 
	 * @param requestBodyIn 获取请求的内容区字节流，等价于request.getInputStream()
	 * @param responseBodyOut 获取响应的内容区字节流，等价于response.getOutputStream()
	 */
	public void inputOrOutBody(InputStream requestBodyIn, OutputStream responseBodyOut){
		
	}
	
	/**
	 * 
	 * @param reader 获取请求的内容区字符流，等价于request.getReader()
	 * @param writer 获取相应的内容区字符流，等价于response.getWriter()
	 */
	public void readerOrWriteBody(Reader reader, Writer writer){
		
	}
	
	public String webRequest(WebRequest webRequest,NativeWebRequest nativeWebRequest){
		System.out.println(webRequest.getParameter("test"));//得到请求参数test的值
		webRequest.setAttribute("name", "value", WebRequest.SCOPE_REQUEST);
		System.out.println(webRequest.getAttribute("name", WebRequest.SCOPE_REQUEST));
		HttpServletRequest request = 
				nativeWebRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = 
				nativeWebRequest.getNativeResponse(HttpServletResponse.class);
		return "success";
	}
	
	public String session(HttpSession session){
		System.out.println(session);
		return "success";
	}
	
	@RequestMapping(value="/commandObject", method=RequestMethod.GET)
	public String toCreateUser(HttpServletRequest request, UserModel user){
		return "customer/create";
	}
	
	@RequestMapping(value="/commandObject", method=RequestMethod.POST)
	public String createUser(HttpServletRequest request, UserModel user){
		System.out.println(user);
		return "success";
	}
	
	@RequestMapping(value="/model")
	public String createUser(Model model, Map model2, ModelMap model3){
		model.addAttribute("a","a");
		model2.put("b","b");
		model3.put("c", "c");
		System.out.println(model == model2);
		System.out.println(model2 == model3);
		return "success";
	}
	
	@RequestMapping(value="/mergeModel")
	public ModelAndView mergeModel(Model model){
		model.addAttribute("a","a");//添加模型数据
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("a","update");//在视图渲染之前更新同名数据模型
		model.addAttribute("a", "new");//修改同名数据模型
		//视图页面的a将显示为"update"而不是"new"
		return mv;
	}
	
	@RequestMapping(value="/error1")
	public String error1(UserModel user, BindingResult result){
		return "success";
	}
	
	@RequestMapping(value="/error2")
	public String error2(UserModel user, BindingResult result, Model model){
		return "success";
	}
	
	@RequestMapping(value="/error3")
	public String error3(UserModel user, Error errors){
		return "success";
	}
	
}
