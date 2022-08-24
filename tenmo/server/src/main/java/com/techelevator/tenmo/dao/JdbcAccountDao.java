package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(){}
    public JdbcAccountDao(JdbcTemplate getJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance FROM account where user_id = ?";
        SqlRowSet results = null;
        BigDecimal balance = null;
        try {
            results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                balance = results.getBigDecimal("balance");
            }
        } catch (DataAccessException e) {
            System.out.println("Error accessing data.");
        }
        return balance;
    }

    @Override
    public Account getAnAccountByUserId(int userId) {return null;}

    @Override
    public Account getAccountById(int id) {
        return null;
    }


    private Account mapToRowAccount(SqlRowSet result) {
        Account account = new Account();
        double balanceDbl = result.getDouble("balance");
        account.setBalance(new BigDecimal(balanceDbl));
        account.setAccountId(result.getInt("accountId"));
        account.setUserId(result.getInt("user_id"));
        return account;
    }
}
