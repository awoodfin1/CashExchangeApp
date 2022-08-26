package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public Transfer getTransferByTransferId(int transferId);

    boolean transferFunds(BigDecimal amount, int userSendId, int userReceiveId);

    public Transfer newTransfer(Transfer transfer);

    public List<Transfer> getListOfTransfers(int accountId);
}
