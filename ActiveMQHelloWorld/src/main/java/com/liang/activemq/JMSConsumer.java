package com.liang.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	/**
	 * 默认连接用户名
	 */
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
	/**
	 * 默认连接密码
	 */
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	/**
	 * 默认连接地址
	 */
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		// 连接工厂
		ConnectionFactory connectionFactory;
		// 连接
		Connection connection = null;
		// 会话 接收或者发送消息的线程
		Session session = null;
		// 消息的目的地
		Destination destination = null;
		// 消息消费者
		MessageConsumer messageConsumer;
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
		
		try {
			// 创建连接
			connection = connectionFactory.createConnection();
			// 启动连接
			connection.start();
			// 创建session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个名称为HelloWorld的消息队列
			destination = session.createQueue("HelloWorld");
			// 创建消息消费者
			messageConsumer = session.createConsumer(destination);
			
			// 接收消息
			while (true) {
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if (textMessage != null) {
					System.out.println("收到的消息:" + textMessage.getText());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
