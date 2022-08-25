package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public class TransferController implements TransferDao {


    @Override
    public Transfer getTransferByTransferId(int transferId) {
        return null;
    }

    @Override
    public Transfer newTransfer(int userFrom, int userTo, BigDecimal amount) {
        return null;
    }

    @Override
    public List<Transfer> getListOfTransfers(int accountId) {
        return null;
    }
}
