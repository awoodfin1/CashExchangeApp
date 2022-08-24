package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public class JdbcTransferDao implements TransferDao{
    @Override
    public List<Transfer> getAllApprovedTransfers(int accountId) {
        return null;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        return null;
    }

    @Override
    public Transfer newTransfer(int accountFrom, int accountTo, BigDecimal amount) {
        return null;
    }
}
