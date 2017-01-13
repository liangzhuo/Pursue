package com.liang.springmvc.web.controller.support.validator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.liang.springmvc.model.UserModel;

public class UserModelValidator implements Validator{
	private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z]\\w{4,19}");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9]{5,20}");
	private static final Set<String> FORBINDDEN_WORD_SET = new HashSet<String>();
	static{
		FORBINDDEN_WORD_SET.add("fuc k");
		FORBINDDEN_WORD_SET.add("admin");
	}
	

	public boolean supports(Class<?> clazz) {
		return UserModel.class == clazz;//表示只对UserModel类型的目标对象实施验证
	}

	public void validate(Object target, Errors errors) {
		//这个表示如果目标对象的username属性为空，则表示出错(简化我们手工判断为空)
		ValidationUtils.rejectIfEmpty(errors, "username", "username.not.empty");
		
		UserModel user = (UserModel)target;
		
		if(!USERNAME_PATTERN.matcher(user.getUsername()).matches()){
			errors.rejectValue("username", "username.not.illegal");//如果用户不合法
		}
		
		for(String forbidden : FORBINDDEN_WORD_SET){
			if(user.getUsername().contains(forbidden)){
				errors.rejectValue("username", "username.forbidden", new Object[]{forbidden}, "您的用户名包含非法关键词");
				//用户包含非法关键词
			}
		}
		
		if(!PASSWORD_PATTERN.matcher(user.getPassword()).matches()){
			errors.rejectValue("password","password.not.illegal","密码不合法");//密码不合法
		}
	}

}
