package org.sky.web.test.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver2 {


    @RabbitListener(queues = "topic.messages")
    public void process(String message) {
        System.out.println("Topic Receiver2  : " + message);
    }

}