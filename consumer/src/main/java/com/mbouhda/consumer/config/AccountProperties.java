package com.mbouhda.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account")
@Getter
@Setter
public class AccountProperties {

    public Queue queue;

    @Getter
    @Setter
    public static class Queue {
        public String name;
    }
}
