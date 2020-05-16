package com.quanroon.ysq.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class MyJmsMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message != null && message instanceof TextMessage){
            try {
                System.out.println("收到消息："+((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
