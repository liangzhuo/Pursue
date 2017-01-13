package com.liang.springmvc.web.controller.support.convert;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import com.liang.springmvc.model.PhoneNumberModel;

public class ConverterTest {

	@Test
	public void testPhoneNumber() {
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new StringToPhoneNumberConverter());
		
		String phoneNumberStr = "010-11112222";
		PhoneNumberModel numberModel = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);
		
		Assert.assertEquals("010", numberModel.getAreaCode());
	}
	
	@Test
	public void testDate(){
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new StringToDateConverter());
		
		String dateStr = "2015-01-01 12:00:00";
		Date date = conversionService.convert(dateStr, Date.class);
		
		System.out.println(date);
	}
	
	@Test
	public void testOther(){
		DefaultConversionService conversionService = new DefaultConversionService();
		
		Assert.assertEquals(Boolean.valueOf(true), conversionService.convert("1", Boolean.class));
		
		Assert.assertEquals(4, conversionService.convert("1,2,3,4", List.class).size());
	}

}
