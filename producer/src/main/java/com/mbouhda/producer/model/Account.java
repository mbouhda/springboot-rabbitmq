package com.mbouhda.producer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountNumber;

    private String accountName;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;
}
