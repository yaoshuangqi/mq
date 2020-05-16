package com.quanroon.ysq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 对于持久化订阅，必须先启动订阅者，在启动生产者
 */
public class ConsumerTopicApplication4001 {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.136.129:61616");

        System.out.println("z13订阅....");
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("z13");

        //创建session会话； 第一个参数为事务：如果设置成true,则需要手动提交；，第二个参数为签收
        //事务偏于生产者，签收偏于消费者
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地（队列还是主题）queue01
        Topic topic = session.createTopic("topic01");
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remarks....");

        //发布订阅
        connection.start();

        Message message = durableSubscriber.receive();
        while (null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("*****收到的持久化topic:"+textMessage.getText());
            message = durableSubscriber.receive(3000);//超时后，不再接收
        }

        //关闭
        session.close();
        connection.close();
    }
}
