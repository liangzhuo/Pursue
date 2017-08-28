package com.zyff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyff.dao.PersonDao;
import com.zyff.domain.Person;

@RestController
public class PersonRedisController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonDao personDao;
	
	@RequestMapping("setPerson")
	public void setPerson(String id, String name, Integer age) {
		logger.debug("访问setPerson:id={},name={},age={}", id, name, age);
		Person person = new Person(id, name, age);
		personDao.save(person);
	}
	
	@RequestMapping("/getPerson")
	public Person getPerson(String id) {
		return personDao.get(id);
	}
	
	

}
