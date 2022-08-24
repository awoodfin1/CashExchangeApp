package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao{
    @Override
    public BigDecimal getBalance(int id) {
        return null;
    }

    @Override
    public Account getAnAccountByUserId(int userId) {
        return null;
    }

    @Override
    public void addBalance(BigDecimal amount, int accountId) {

    }

    @Override
    public boolean subtractBalance(BigDecimal amount, int accountId) {
        return false;
    }
}
