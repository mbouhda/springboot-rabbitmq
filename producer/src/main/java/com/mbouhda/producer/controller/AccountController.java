package com.mbouhda.producer.controller;

import com.mbouhda.producer.dto.AccountDTO;
import com.mbouhda.producer.event.AccountEvent;
import com.mbouhda.producer.model.Account;
import com.mbouhda.producer.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AccountController extends AbstractController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    @ResponseBody Page<Account> getAccountsByPage(
            @RequestParam(value = "pageNumber", required = true, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) {
        Page<Account> pagedAccounts = accountService.getAccountsByPage(pageNumber, pageSize);
        return pagedAccounts;
    }

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        Account account1 = accountService.save(account);

        AccountDTO accountDTO = new AccountDTO(account1.getId(), account1.getAccountNumber(), account1.getAccountName(), account1.getUser());

        AccountEvent accountEvent = new AccountEvent(this, "AccountCreated", accountDTO);
        eventPublisher.publishEvent(accountEvent);
        return ResponseEntity.ok().body("New account created with id: {}" +accountDTO.getId());
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {
        checkResourceFound(account);
        accountService.update(account);
        return ResponseEntity.ok().body("Account was successfully updated.");
    }
}
