package com.liang.mongodb;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liang.mongodb.model.Customer;
import com.liang.mongodb.model.Person;
import com.mongodb.WriteResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/applicationContext.xml")
public class MongodbTest {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void testInsert(){
		Customer customer = new Customer();
		customer.setFirstName("liang3");
		customer.setLastName("zhuo3");
		customer.setUsername("liangz3");
		customer.setAge(10);
		mongoTemplate.insert(customer);
	}
	
	/**
	 * 批量插入遇错误中断，不进行回滚
	 */
	@Test
	public void testBatchInsert(){
		Customer customer = new Customer();
		customer.setId("565800458b668d121035d4dd");
		customer.setFirstName("liang2");
		customer.setLastName("zhuo2");
		customer.setUsername("liangz");
		
		Customer customer1 = new Customer();
		customer1.setFirstName("liang2");
		customer1.setLastName("zhuo2");
		customer1.setUsername("liangz");
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		customerList.add(customer1);
		
		//一个一个插入
		mongoTemplate.insertAll(customerList);
		//mongoTemplate.insert(customerList, Customer.class);
	}
	
	/**
	 * save和insert的区别是，save对象不存在执行insert操作，主要是看_id
	 */
	@Test
	public void testSave(){
		Customer customer = new Customer();
		customer.setFirstName("liang3");
		customer.setLastName("zhuo3");
		customer.setUsername("liangz1");
		mongoTemplate.save(customer);
	}
	
	@Test
	public void testSave1(){
		Customer customer = mongoTemplate.findOne(query(where("username").is("liangz1")),Customer.class);
		customer.setUsername("liangzhuo01");
		mongoTemplate.save(customer);
	}
	
	@Test
	public void testUpdate(){
		WriteResult writeResult = mongoTemplate.updateMulti(query(where("age").is(10)), 
								 new Update().inc("age", 10), 
								 Customer.class);
		System.out.println(writeResult.getN());
	}
	
	/**
	 * 查询到就更新，否则插入
	 */
	@Test
	public void testUpsert(){
		WriteResult writeResult = mongoTemplate.upsert(query(where("username").is("liangz4")), 
							new Update().update("firstName", "liang33"), 
							Customer.class);
		System.out.println(writeResult.getN());
	}
	
	@Test
	public void testFindAndUpset(){
		mongoTemplate.dropCollection(Person.class);
		
		mongoTemplate.insert(new Person("liangz01",22));
		mongoTemplate.insert(new Person("liangz02",23));
		mongoTemplate.insert(new Person("liangz03",24));
		
		Query query = new Query(Criteria.where("name").is("liangz03"));
		Update update = new Update().inc("age",1);
		
		Person p = mongoTemplate.findAndModify(query, update, Person.class);
		System.out.println(p.getName());
		System.out.println(p.getAge());
		
		p = mongoTemplate.findOne(query, Person.class);
		System.out.println(p.getAge());
		
		p = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true),Person.class);
		System.out.println(p.getAge());
		
		p = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true).upsert(true), Person.class);
		System.out.println(p.getAge());
	}
	
	@Test
	public void testRemove(){
		Query query = new Query(Criteria.where("name").is("liangz01"));
		mongoTemplate.remove(query, Person.class);
	}
	
	@Test
	public void testQuery(){
		Customer customer = mongoTemplate.findOne(query(where("username").is("liangz")),Customer.class);
		System.out.println(customer.toString());
	}
	
}
