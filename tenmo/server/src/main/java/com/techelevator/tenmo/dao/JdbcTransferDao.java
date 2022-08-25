package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.List;

public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer getTransferByTransferId(int transferId) {
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        Transfer transfer = null;
        if (result.next()) {
            transfer = mapToRowTransfer(result);
        }
        return transfer;
    }

    @Override
    public boolean transferFunds(Transfer transfer, int userSendId, int userReceiveId) {
        BigDecimal transferAmount = new BigDecimal(String.valueOf(transfer.getAmount()));
        String sql = "SELECT user_id FROM tenmo_user WHERE username = ?";
        Integer senderId = jdbcTemplate.queryForObject(sql, Integer.class);
        if (!senderId.equals(senderId)) {
            String fromSql = "UPDATE account SET balance = balance - ? WHERE user_id = ?";
            jdbcTemplate.update(fromSql, transferAmount, userSendId);
            String toSql = "UPDATE account SET balance = balance + ? WHERE user_id = ?";
            jdbcTemplate.update(toSql, transferAmount, userReceiveId);
        }
        return false;
    }


    @Override
    public Transfer newTransfer(Transfer transfer) {
        
        String sql = "INSERT INTO transfer (account_from, account_to, amount) VALUES (?, ?, ?) RETURNING transfer_id";

        return null;
    }

    @Override
    public List<Transfer> getListOfTransfers(int accountId) {
        return null;
    }

    private Transfer mapToRowTransfer(SqlRowSet result) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setUserFrom(result.getInt("account_from"));
        transfer.setUserTo(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));
        return transfer;
    }
}
