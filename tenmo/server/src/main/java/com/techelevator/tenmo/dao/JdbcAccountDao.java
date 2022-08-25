package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
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
    public boolean transferFunds(Transfer transfer, int userSendId, int userReceiveId) {
        Account account = new Account();
        BigDecimal transferAmount = new BigDecimal(String.valueOf(transfer.getAmount()));
        String sql = "SELECT user_id FROM tenmo_user WHERE username = ?";
        Integer senderId = jdbcTemplate.queryForObject(sql, Integer.class);

        assert senderId != null;
        if (!senderId.equals(senderId)) {
            account = getAnAccountByUserId(userSendId);
            //BigDecimal currentBalance = new BigDecimal(String.valueOf(account.getBalance()));
            //BigDecimal updatedSendBalance = new BigDecimal(String.valueOf(currentBalance.subtract(transferAmount)));
            String fromSql = "UPDATE account SET balance - ? WHERE account_id = ?";
            jdbcTemplate.update(fromSql, transferAmount, userSendId);

            account = getAnAccountByUserId(userReceiveId);
            //BigDecimal receiversCurrentBalance = account.getBalance();

            String toSql = "UPDATE account SET balance + ? WHERE account_id = ?";
            jdbcTemplate.update(toSql, transferAmount, userReceiveId);




//            try (SqlRowSet fromSql = jdbcTemplate.queryForRowSet(fromSql, transferAmount, account.getAccountId());
//                 SqlRowSet toSql = jdbcTemplate.queryForRowSet(toSql, transferAmount, account.getAccountId())) {
//
//            } catch (DataAccessException e) {
//                return false;
//            }
        }
        return false;
    }

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
