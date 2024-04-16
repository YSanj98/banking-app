package com.example.bankingapp.mapper;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccount(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountName(),
                account.getBalance()
        );
        return accountDto;
    }
}
