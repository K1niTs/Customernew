package org.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue orderStatusQueue() {
        return new Queue("orderStatus.requests.queue", true);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("preorder.exchange");
    }
    @Bean
    public Binding orderStatusBinding() {
        return BindingBuilder.bind(orderStatusQueue()).to(exchange()).with("orderStatus.requests");
    }
}
