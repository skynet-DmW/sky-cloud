package org.sky.web.config.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * mq配置
 */
@SpringBootConfiguration
@Slf4j
public class RabbitConfig {


    /**
     * 队列1
     *
     * @return
     */
    @Bean(name = "message")
    public Queue queueMessage() {
        // 开启持久化
        return new Queue("topic.message", true);
    }


    /**
     * 队列2
     *
     * @return
     */
    @Bean(name = "messages")
    public Queue queueMessages() {
        // 开启持久化
        return new Queue("topic.messages", true);
    }


    /**
     * 交换机
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        /**
         *  第一个参数为交换机名。
         *  第二个参数为durable,是否持久化。
         *  第二个参数为autoDelete,当交换机没有绑定队列时会自动删除交换机。
         */
        return new TopicExchange("trace.exchange", true, false);
    }


    /**
     * 将队列1绑定带交换机上
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }


    /**
     * 将队列2绑定到交换机上
     *
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");// *表示一个词,#表示零个或多个词
    }

}
