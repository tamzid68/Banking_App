package com.system.banking.service;

import com.system.banking.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccuntyId(Long id);

    AccountDto desposit(Long id, double amount);
}
