package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public Transfer getTransferByTransferId(int transferId);

    boolean transferFunds(Transfer transfer, int userSendId, int userReceiveId);

    public Transfer newTransfer(int userFrom, int userTo, BigDecimal amount);

    public List<Transfer> getListOfTransfers(int accountId);
}
