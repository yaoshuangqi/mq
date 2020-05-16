package com.quanroon.ysq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建一个持久化的topic
 */
public class ProducerTopicApplication5001 {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.136.129:61616");

        Connection connection = connectionFactory.createConnection();

        //创建session会话； 第一个参数为事务，第二个参数为签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地（队列还是主题）
        Topic topic = session.createTopic("topic01");

        MessageProducer producer = session.createProducer(topic);
        //消息头，五大特点。也可以在send方法中指定
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        //发送消息
        for (int i = 0; i < 3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("持久化topic消息：第" + i + "消息");
            //可以设置消息头,除了使用消息头外，可以增加消息属性
            //.....textMessage

            producer.send(textMessage);
        }
        //关闭
        producer.close();
        session.close();
        connection.close();

        System.out.println("****消息发布到MQ成功");
    }
}
