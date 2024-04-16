package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
}
