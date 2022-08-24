package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal getBalance(int id);
    Account getAnAccountByUserId(int userId);
    void addBalance(BigDecimal amount, int accountId);
    boolean subtractBalance(BigDecimal amount, int accountId);
}
