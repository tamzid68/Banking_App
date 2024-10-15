package com.system.banking.service.impl;

import com.system.banking.dto.AccountDto;
import com.system.banking.entity.Account;
import com.system.banking.mapper.AccountMapper;
import com.system.banking.repository.AccountRepository;
import com.system.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccuntyId(Long id) {
        Account account = accountRepository.
                findById(id).orElseThrow(
                ()-> new RuntimeException("Account dose not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto desposit(Long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(
                ()-> new RuntimeException("Account dose not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.
                findById(id).orElseThrow(
                ()-> new RuntimeException("Account dose not exists"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insuffient amount");
        }

        double total  = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.
                findById(id).orElseThrow(
                        ()-> new RuntimeException("Account not found with id: " + id));
        accountRepository.deleteById(id);
    }
}
