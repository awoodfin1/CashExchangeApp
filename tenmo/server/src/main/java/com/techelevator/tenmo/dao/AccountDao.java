package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal getBalance(int userId);



    Account getAnAccountByUserId(int userId);


    Account getAccountById(int id);
}
