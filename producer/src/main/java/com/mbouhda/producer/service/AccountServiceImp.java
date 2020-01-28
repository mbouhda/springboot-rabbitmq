package com.mbouhda.producer.service;

import com.mbouhda.producer.dao.AccountRepository;
import com.mbouhda.producer.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    final static Logger logger = LoggerFactory.getLogger(AccountServiceImp.class);

    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void delete(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> getAccountsByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return accountRepository.findAll(pageable);
    }
}
