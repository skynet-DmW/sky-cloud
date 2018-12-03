package org.sky.web.test.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "我是消息";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("trace.exchange", "topic.1", context);
    }

    public void send1() {
        String context = "我是消息1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("trace.exchange", "topic.message", context);
    }

    public void send2() {
        String context = "我是消息2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("trace.exchange", "topic.messages", context);
    }
}