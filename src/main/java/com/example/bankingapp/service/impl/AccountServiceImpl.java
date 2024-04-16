package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.service.AccountService;
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
        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exists"));
        return AccountMapper.mapToAccount(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exists"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account)-> AccountMapper.mapToAccount(account))
                .collect(Collectors.toList());
    }
}
