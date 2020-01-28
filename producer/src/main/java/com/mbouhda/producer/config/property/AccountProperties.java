package com.mbouhda.producer.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account")
@Getter
@Setter
public class AccountProperties {

    public Exchange exchange;
    public Queue queue;
    public Routing routing;

    @Getter
    @Setter
    public static class Exchange {
        public String name;
    }

    @Getter
    @Setter
    public static class Queue {
        public String name;
    }

    @Getter
    @Setter
    public static class Routing {
        public String key;
    }
}
