package com.liang.activemq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liang.activemq.mq.producer.queue.QueueSender;
import com.liang.activemq.mq.producer.topic.TopicSender;

@Controller
@RequestMapping("/activemq")
public class ActiveMQController {
	
	@Resource
	QueueSender queueSender;
	
	@Resource
	TopicSender topicSender;
	
	@ResponseBody
	@RequestMapping("/queue")
	public String queueSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			queueSender.send("test.queue", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
	@ResponseBody
	@RequestMapping("/topic")
	public String topicSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			topicSender.send("test.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
}
