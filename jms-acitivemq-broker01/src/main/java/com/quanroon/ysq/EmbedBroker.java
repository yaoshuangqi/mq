package com.quanroon.ysq;

import org.apache.activemq.broker.BrokerService;

/**
 * 相当于ActiveMQ服务器实例，嵌入到Java代码中
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();

        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
