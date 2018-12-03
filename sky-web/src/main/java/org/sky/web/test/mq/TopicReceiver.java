package org.sky.web.test.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {


    @RabbitListener(queues = "topic.message")
    public void process(String message) {
        System.out.println("Topic Receiver1  : " + message);
    }

}