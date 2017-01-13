package com.liang.springmvc.web.controller.support.formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.ui.Model;

import com.liang.springmvc.model.FormatterModel;
import com.liang.springmvc.model.PhoneNumberModel;
import com.liang.springmvc.web.controller.support.fomatter.PhoneNumFormatter;
import com.liang.springmvc.web.controller.support.fomatter.PhoneNumberFormatAnnotationFormatterFactory;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.LocalElement;

public class FomatterTest {
	@Test
	public void testFormatter() throws ParseException{
		CurrencyFormatter currencyFormatter = new CurrencyFormatter();
		currencyFormatter.setFractionDigits(2);//保留小数点几位
		currencyFormatter.setRoundingMode(RoundingMode.CEILING);//舍入模式(celling表示四舍五入)
		
		//1.将带货币符号的字符串"$123.125"转换为BigDecimal("123.00")
		Assert.assertEquals(new BigDecimal("123.13"), currencyFormatter.parse("$123.125", Locale.US));
		//2.将BigDecimal("123")格式化为字符串"$123.00"展示
		Assert.assertEquals("$123.00", currencyFormatter.print(new BigDecimal("123"), Locale.US));
		Assert.assertEquals("￥123.00", currencyFormatter.print(new BigDecimal("123"), Locale.CHINA));
		Assert.assertEquals("￥123.00", currencyFormatter.print(new BigDecimal("123"), Locale.JAPAN));
	}
	
	@Test
	public void testWithDefaultFormattingConversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		CurrencyFormatter currencyFormatter = new CurrencyFormatter();
		currencyFormatter.setFractionDigits(2);//保留小数点几位
		currencyFormatter.setRoundingMode(RoundingMode.CEILING);//舍入模式(celling表示四舍五入)
		
		//绑定Local信息到ThreadLocal
		LocaleContextHolder.setLocale(Locale.US);
		Assert.assertEquals("$1,234.13", conversionService.convert(new BigDecimal(1234.128), String.class));
		LocaleContextHolder.setLocale(null);
		LocaleContextHolder.setLocale(Locale.CHINA);
		
		Assert.assertEquals("￥1,234.13", conversionService.convert(new BigDecimal(1234.128), String.class));
		Assert.assertEquals(new BigDecimal("1234.13"), conversionService.convert("￥1234.13", BigDecimal.class));
	}
	
	@Test
	public void testPhoneNumberFormatter(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addFormatter(new PhoneNumFormatter());
		
		PhoneNumberModel phoneNumber = new PhoneNumberModel("010","12345678");
		Assert.assertEquals("010-12345678", conversionService.convert(phoneNumber, String.class));
		
		Assert.assertEquals("010", conversionService.convert("010-12345678", PhoneNumberModel.class).getAreaCode());
	}
	
	@Test
	public void testFormatterStyle() throws NoSuchFieldException, SecurityException{
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		/*
		FormatterModel formatterModel = new FormatterModel();
		formatterModel.setTotalCount(10000);
		formatterModel.setDiscount(0.51);
		formatterModel.setSumMoney(100000.13);
		formatterModel.setRegisterDate(new Date(2012-1900, 4, 1));
		formatterModel.setOrderDate(new Date(2012-1900, 4, 1, 10, 42, 00));
		
		
		TypeDescriptor descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("totalCount"));
		TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);
		
		Assert.assertEquals("10,000", conversionService.convert(formatterModel.getTotalCount(), descriptor, stringDescriptor));
		Assert.assertEquals(formatterModel.getTotalCount(), conversionService.convert("10,000", stringDescriptor, descriptor));
		
		
		descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("registerDate"));
		Assert.assertEquals("2012-05-01", conversionService.convert(formatterModel.getRegisterDate(), descriptor, stringDescriptor));
		Assert.assertEquals(formatterModel.getRegisterDate(), conversionService.convert("2012-05-01", stringDescriptor, descriptor));
		
		descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("orderDate"));
		Assert.assertEquals("2012-05-01 10:42:00", conversionService.convert(formatterModel.getOrderDate(), descriptor, stringDescriptor));
		Assert.assertEquals(formatterModel.getOrderDate(), conversionService.convert("2012-05-01 10:42:00", stringDescriptor, descriptor));
		*/
		
		conversionService.addFormatterForFieldAnnotation(new PhoneNumberFormatAnnotationFormatterFactory());
		
		FormatterModel model = new FormatterModel();
		TypeDescriptor descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("phoneNumber"));
		TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);
		
		PhoneNumberModel value = (PhoneNumberModel)conversionService.convert("010-1234567", stringDescriptor, descriptor);
		model.setPhoneNumber(value);
		
		Assert.assertEquals("010-1234567", conversionService.convert(model.getPhoneNumber(), descriptor, stringDescriptor));
	}
}
