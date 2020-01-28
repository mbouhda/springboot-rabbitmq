package com.mbouhda.producer.service;

import com.mbouhda.producer.model.Account;
import org.springframework.data.domain.Page;

public interface AccountService {

    Account save(Account account);
    void update(Account account);
    void delete(int id);
    Page<Account> getAccountsByPage(Integer pageNumber, Integer pageSize);

}
