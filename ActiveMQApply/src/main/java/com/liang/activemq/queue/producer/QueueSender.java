package com.liang.activemq.queue.producer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.liang.activemq.model.Person;

public class QueueSender {
	// 连接用户名
	private String username = "";
	// 连接密码
	private String password = "";
	// 连接地址
	private String brokerURL = "tcp://127.0.0.1:61616";
	// connection的工厂
	private ConnectionFactory connectionFactory = null;
	// 连接对象
	private Connection connection = null;
	// 会话
	private Session session = null;
	// 目的地，其实就是连接到哪种消息模式，如果是点对点，那它的实现就是Queue，如果是订阅模式，那它的实现就是Topic
	private Destination destination = null;
	// 消息生产者
	private MessageProducer producer = null;
	
	public static void main(String[] args) {
		QueueSender queueSender = new QueueSender();
		queueSender.start();
	}

	@SuppressWarnings("resource")
	public void start() {
		try {
			// 创建连接工厂
			connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
			// 从工厂中获取连接
			connection = connectionFactory.createConnection();
			// 不清楚是否必须写
			connection.start();
			// 创建一个Session
			// 第一个参数：是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
			// 第一个参数为false时，第二个参数的值可为Session.AUTO_ACKNOWLEDGE, Session.CLIENT_ACKNOWLEDGE, Session.DUPS_OK_ACKNOWLEDGE其中一个
			// Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收端消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功
			// Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
			// Session.DUPS_OK_ACKNOWLEDGE 允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复。
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 消息到达的目的地
			destination = session.createQueue("text-msg");
			// 从session中，获取一个消息生产者
			producer = session.createProducer(destination);
			// 设置生产者的模式，有两种可选
			// DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会保存
			// DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			for (int i=0; i<10; i++) {
				// 创建消息，当然，消息的类型很多，如字节、文字、对象等可以通过session.create...方法创建
				/**
				 * 纯字符串的数据
				 * session.createTextMessage();
				 * 序列化对象
				 * session.createObjectMessage();
				 * 流，可以用来传递文件
				 * session.createStreamMessage();
				 * 用来传递字节
				 * session.createBytesMessage();
				 * 创建出来就是一map，可当作map用
				 * session.createMapMessage();
				 * javax.jms.Message所有message的接口
				 * session.createMessage();
				 */
				TextMessage message = session.createTextMessage("你好,ActiveMQ!" + " " + i);
				// 发送消息
				producer.send(message);
			}
			
			// 发送对象
			ObjectMessage objectMessage = session.createObjectMessage();
			for (int j=0; j<10; j++) {
				Person p = new Person("" + j, "名字");
				objectMessage.setObject(p);
				producer.send(objectMessage);
			}
			
			// 发送字节消息
			BytesMessage bytesMessage = session.createBytesMessage();
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File("D:\\Config.ini")));
			byte[] buff = new byte[1024];
			int len = 0;
			while((len = is.read(buff)) != -1) {
				bytesMessage.writeBytes(buff, 0, len);
			}
			producer.send(bytesMessage);
			
			
			System.out.println("--------------------发送消息成功!");
			
		} catch (JMSException | IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
