package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
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
    public Transfer newTransfer(Transfer transfer) {
        int userFrom = transfer.getUserFrom();
        int userTo = transfer.getUserTo();
        BigDecimal amount = transfer.getAmount();
        String sql = "INSERT INTO transfer (account_from, account_to, amount) "
                + "VALUES (?,?,?) RETURNING transfer_id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, userFrom, userTo, amount);
        return getTransferByTransferId(id);
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
