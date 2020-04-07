package com.zyff.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zyff.domain.Person;
import com.zyff.repository.PersonRepository;

@RestController
public class DataController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping("/save")
	public Person save(String name, String address, Integer age) {
		logger.debug("save start");
		Person p = personRepository.save(new Person(null, name, age, address));
		logger.debug("save end");
		return null;
	}
	
	@RequestMapping("/q1")
	public List<Person> q1(String address) {
		logger.debug("q1 start");
		logger.debug("q1 接收参数address={}", address);
		return personRepository.findByAddress(address);
	}
	
	@RequestMapping("/q2")
	public List<Person> q2(String name, String address) {
		logger.debug("q2 start");
		logger.debug("q2 接收参数name={},address={}", name, address);
		return personRepository.findByNameAndAddress(name, address);
	}
	
	@RequestMapping("/q3")
	public List<Person> q3(String name, String address) {
		logger.debug("q3 start");
		logger.debug("q3 接收参数name={},address={}", name, address);
		return personRepository.withNameAndAddressQuery(name, address);
	}
	
	@RequestMapping("/sort")
	public List<Person> sort() {
		logger.debug("sort start");
		List<Person> personList = personRepository.findAll(new Sort(Direction.ASC, "age"));
		return personList;
	}
	
	@RequestMapping("/page")
	public Page<Person> page() {
		logger.debug("page start");
		Page<Person> personList = personRepository.findAll(new PageRequest(1, 2));
		return personList;
	}
}
