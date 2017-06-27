package com.liang.activemq.mq.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * Topic消息监听器
 * @author liangz
 *
 */
@Component
public class TopicReceiver1 implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver1 接收到消息:" + ((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
