package com.liang.springmvc.web.controller.support.fomatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import com.liang.springmvc.model.PhoneNumberModel;

public class PhoneNumFormatter implements Formatter<PhoneNumberModel>{
	private Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
	
	public String print(PhoneNumberModel phoneNum, Locale locale) {
		if(phoneNum == null){
			return "";
		}
		return new StringBuilder().append(phoneNum.getAreaCode()).append("-")
				.append(phoneNum.getPhoneNumber()).toString();
	}

	public PhoneNumberModel parse(String source, Locale locale)
			throws ParseException {
		if(!StringUtils.hasLength(source)){
			return null;
		}
		Matcher matcher = pattern.matcher(source);
		if(matcher.matches()){
			PhoneNumberModel numberModel = new PhoneNumberModel();
			numberModel.setAreaCode(matcher.group(1));
			numberModel.setPhoneNumber(matcher.group(2));
			return numberModel;
		}else{
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678],但是格式是%s", source));
		}
	}

}
