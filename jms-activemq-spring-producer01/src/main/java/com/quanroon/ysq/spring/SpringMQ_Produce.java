package com.quanroon.ysq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Produce produce = (SpringMQ_Produce)context.getBean("springMQ_Produce");

        produce.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("*****spring和ActiveMQ整合配置。。。");
            return textMessage;
        });

        System.out.println("....消息发送成功！");
    }
}
