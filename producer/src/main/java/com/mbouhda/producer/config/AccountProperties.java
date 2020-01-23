package com.mbouhda.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account")
@Getter
@Setter
public class AccountProperties {

    private Exchange exchange;
    private Queue queue;
    private Routing routing;

    @Getter
    @Setter
    public static class Exchange {
        private String name;
    }

    @Getter
    @Setter
    public static class Queue {
        private String name;
    }

    @Getter
    @Setter
    public static class Routing {
        private String key;
    }
}
