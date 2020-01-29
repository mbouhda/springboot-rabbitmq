package com.mbouhda.producer.config.rabbit;

import com.mbouhda.producer.config.property.AccountProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class AccountProducerConfiguration {

    @Autowired
    private AccountProperties accountProperties;

    @Bean
    public TopicExchange getAccountTopicExchange() {
        return new TopicExchange(accountProperties.getExchange().getName());
    }

    @Bean
    public Queue getAccountQueue() {
        return new Queue(accountProperties.getQueue().getName());
    }

    @Bean
    public Binding bindAccountQueueForExchange() {
        return BindingBuilder.bind(getAccountQueue()).to(getAccountTopicExchange()).with(accountProperties.getRouting().getKey());
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerMessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory methodFactory = new DefaultMessageHandlerMethodFactory();
        methodFactory.setMessageConverter(consumerMessageConverter());
        return methodFactory;
    }

    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }



}
