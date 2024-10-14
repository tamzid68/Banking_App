package com.system.banking.controller;

import com.system.banking.dto.AccountDto;
import com.system.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    // Constructor injection is preferred for better testability and clarity

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        AccountDto createdAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    //get Account Rest Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAcccount(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccuntyId(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deporsit REST API
    @PutMapping("/{id}/desposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String ,Double>request) {
        AccountDto accountDto = accountService.desposit(id, request.get("amount"));

        return ResponseEntity.ok(accountDto);
    }
}
