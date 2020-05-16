package com.quanroon.ysq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Consumer consumer = (SpringMQ_Consumer)context.getBean("springMQ_Consumer");

        String result = (String)consumer.jmsTemplate.receiveAndConvert();

        System.out.println(result);
    }
}
