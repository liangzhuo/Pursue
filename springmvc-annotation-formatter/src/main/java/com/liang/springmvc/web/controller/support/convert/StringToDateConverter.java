package com.liang.springmvc.web.controller.support.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


public class StringToDateConverter implements Converter<String, Date> {
	private String dateFormatStr;
	
	public StringToDateConverter() {
	}
	
	public StringToDateConverter(String dateFormatStr) {
		super();
		this.dateFormatStr = dateFormatStr;
	}

	public Date convert(String source) {
		if(!StringUtils.hasLength(source)){
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		try {
			Date date = dateFormat.parse(source);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[yyyy-MM-dd HH:mm:ss],但是格式是%s", source));
		}
	}

}
