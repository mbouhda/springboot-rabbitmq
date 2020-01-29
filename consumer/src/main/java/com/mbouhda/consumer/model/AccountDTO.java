package com.mbouhda.consumer.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDTO {

    private int id;

    private String accountNumber;

    private String accountName;

    private String userId;
}
