package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public BigDecimal getBalance(int userId) {
//        String sql = "SELECT balance FROM account where user_id = ?";
//        SqlRowSet results = null;
//        BigDecimal balance = null;
//        try {
//            results = jdbcTemplate.queryForRowSet(sql, userId);
//            if (results.next()) {
//                balance = results.getBigDecimal("balance");
//            }
//        } catch (DataAccessException e) {
//            System.out.println("Error accessing data.");
//        }
//        return balance;
//    }

//    @Override
//    public Account getAnAccountByUserPrincipal(int userId) {
//        Account account = null;
//        String sql = "SELECT account_id, balance FROM account JOIN tenmo_user ON ";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//        if(results.next()) {
//            account = mapToRowAccount(results);
//        // getUserAccountByUserPrincipal(principal.getName)
//        // string sql account id balance join user table where username = ? (principal.getname)
//        // Account.getBalance
//        return Account.getBalance;
//        }
//
//    }

    @Override
    public Account getAnAccountByUserId(int userId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE user_id = ?";

        try{
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            account = mapToRowAccount(results);
            }
        }catch (DataAccessException e) {
            System.out.println("Error accessing data.");
        }
        return account;
    }

    @Override
    public Account getAccountById(int accountId) {
        Account account = null;
        String sql = "SELECT * FROM account WHERE account_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            while(results.next()) {
                account = mapToRowAccount(results);
            }
        }catch (DataAccessException e) {
            System.out.println("Error accessing data.");
        }
        return account;
    }

    private Account mapToRowAccount(SqlRowSet result) {
        Account account = new Account();
        account.setBalance(result.getBigDecimal("balance"));
        account.setAccountId(result.getInt("account_id"));
        account.setUserId(result.getInt("user_id"));
        return account;
    }
}
