package com.mbouhda.producer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountSender {

    final static Logger logger = LoggerFactory.getLogger(AccountSender.class);

    public void sendMessage(RabbitTemplate rabbitTemplate, Object data, String exchange, String routingKey) {
        logger.info("Sending message..");

        rabbitTemplate.convertAndSend(exchange, routingKey, data);

        logger.info("Message sent.");
    }
}
