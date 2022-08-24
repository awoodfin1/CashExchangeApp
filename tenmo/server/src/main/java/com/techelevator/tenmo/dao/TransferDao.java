package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    public List<Transfer> getAllApprovedTransfers(int accountId);
    public Transfer getTransferById(int transferId);
    public Transfer newTransfer(int accountFrom, int accountTo, BigDecimal amount);
}
