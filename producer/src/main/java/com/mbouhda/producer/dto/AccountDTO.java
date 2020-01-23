package com.mbouhda.producer.dto;

import com.mbouhda.producer.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AccountDTO {

    private long id;

    private String accountNumber;

    private String accountName;

    private User user;
}
