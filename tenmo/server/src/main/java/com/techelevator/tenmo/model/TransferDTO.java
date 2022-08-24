package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

    private int accountToId;
    private BigDecimal amount;


    public long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(int accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
