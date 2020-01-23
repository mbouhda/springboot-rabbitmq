package com.mbouhda.producer.listeners;

import com.mbouhda.producer.config.AccountProperties;
import com.mbouhda.producer.event.AccountEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountEventListener {

    final static Logger log = LoggerFactory.getLogger(AccountEventListener.class);

    private RabbitTemplate rabbitTemplate;
    private AccountProperties accountProperties;
    private AccountSender accountSender;
    private Exchange exchange;

    @EventListener
    public void onApplicationEvent(AccountEvent accountEvent) {
        log.info("Received event {}", accountEvent.getEventType());
        log.info("Received account {}", accountEvent.getAccountDTO().toString());

        accountSender.sendMessage(rabbitTemplate, accountEvent.getAccountDTO(), accountProperties.getExchange().getName(), accountProperties.getRouting().getKey());
    }


}
