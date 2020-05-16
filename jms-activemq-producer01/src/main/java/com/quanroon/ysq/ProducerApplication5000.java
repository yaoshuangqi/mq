package com.quanroon.ysq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 创建最简单的queue和topic
 */
public class ProducerApplication5000 {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.136.129:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建session会话； 第一个参数为事务，第二个参数为签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地（队列还是主题）
        Destination queue = session.createQueue("queue01");

        MessageProducer producer = session.createProducer(queue);
        //消息头，五大特点。也可以在send方法中指定
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//是否持久化，队列默认持久,topic默认不持久
        //发送消息
        for (int i = 0; i < 3; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("第" + i + "消息");
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
