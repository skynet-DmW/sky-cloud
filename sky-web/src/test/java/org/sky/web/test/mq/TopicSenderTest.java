package org.sky.web.test.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicSenderTest {


    @Autowired
    TopicSender topicSender;


    @Test
    public void send() {
        this.topicSender.send();
    }

    @Test
    public void send1() {
        this.topicSender.send1();
    }

    @Test
    public void send2() {
        this.topicSender.send2();
    }
}