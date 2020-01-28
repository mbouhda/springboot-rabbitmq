package com.mbouhda.producer.controller;

import com.mbouhda.producer.dto.AccountDTO;
import com.mbouhda.producer.event.AccountEvent;
import com.mbouhda.producer.model.Account;
import com.mbouhda.producer.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AccountController extends AbstractController {

    private AccountService accountService;
    private ModelMapper modelMapper;

    @Autowired
    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/account")
    @ResponseBody Page<Account> getAccountsByPage(
            @RequestParam(value = "pageNumber", required = true, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) {
        Page<Account> pagedAccounts = accountService.getAccountsByPage(pageNumber, pageSize);
        return pagedAccounts;
    }

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        Account accountEntity = modelMapper.map(accountDTO, Account.class);
        Account account1 = accountService.save(accountEntity);

        AccountEvent accountEvent = new AccountEvent(this, "AccountCreated", accountDTO);
        eventPublisher.publishEvent(accountEvent);
        return ResponseEntity.ok().body("New account created with id: " +account1.getId());
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {
        checkResourceFound(account);
        accountService.update(account);
        return ResponseEntity.ok().body("Account was successfully updated.");
    }
}
