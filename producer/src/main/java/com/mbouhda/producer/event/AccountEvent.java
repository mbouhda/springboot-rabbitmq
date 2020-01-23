package com.mbouhda.producer.event;

import com.mbouhda.producer.dto.AccountDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class AccountEvent extends ApplicationEvent {

    private String eventType;
    private AccountDTO accountDTO;

    public AccountEvent(Object source, String eventType, AccountDTO accountDTO) {
        super(source);
        this.eventType = eventType;
        this.accountDTO = accountDTO;
    }
}
