package ysq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class ConsumerApplication4000 {

    public static void main(String[] args) throws JMSException, IOException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.136.129:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();
        //创建session会话； 第一个参数为事务，第二个参数为签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地（队列还是主题）
        Destination queue = session.createQueue("queue01");

        MessageConsumer consumer = session.createConsumer(queue);
        //接收消息， 第一种方法： receive同步阻塞
        /*while (true){
            TextMessage receive = (TextMessage) consumer.receive();//默认不超时
            if(receive != null){
                System.out.println("收到消息："+receive.getText());
            }else
                break;
        }*/
        //第二种方法： 监听
        consumer.setMessageListener((message) -> {
            if(message != null && message instanceof TextMessage){
                try {
                    System.out.println("收到消息："+((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();//避免还没监听到消息就关闭程序了
        //关闭
        consumer.close();
        session.close();
        connection.close();
    }
}
