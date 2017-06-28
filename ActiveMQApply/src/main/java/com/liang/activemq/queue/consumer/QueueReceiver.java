package com.liang.activemq.queue.consumer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.liang.activemq.model.Person;

public class QueueReceiver {
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
	// 消费者
	private MessageConsumer consumer = null;
		
	public static void main(String[] args) {
		QueueReceiver receiver = new QueueReceiver();
		receiver.start();
	}

	public void start() {
		try {
			// 创建连接工厂
			connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
			// 从工厂中获取连接
			connection = connectionFactory.createConnection();
			// 这里不写接收消息会报错
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
			// 从session中，获取一个消息消费者
			consumer = session.createConsumer(destination);
			
			// 设置消息监听器
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					try {
						if (message instanceof TextMessage){
							String text = ((TextMessage)message).getText();
							System.out.println(text);
							// message.acknowledge();
						} else if (message instanceof ObjectMessage) {
							Person p = (Person)((ObjectMessage)message).getObject();
							System.out.println(p.getId());
						} else if (message instanceof BytesMessage) {
							BytesMessage bytesMessage = (BytesMessage)message;
							BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\test.ini")));
							byte[] buff = new byte[1024];
							int len = 0;
							while ((len = bytesMessage.readBytes(buff)) != -1) {
								out.write(buff, 0, len);
							}
							out.close();
						}
						
					} catch (JMSException | IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
//			if (connection != null) {
//				try {
//					connection.close();
//				} catch (JMSException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}	
}
