package com.techelevator.dao;

import com.techelevator.tenmo.model.Transfer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class JdbcTransferDaoTest extends BaseDaoTests {
    private static final Transfer TRANSFER_1 = new Transfer(1, 1, 2, "500.00", "Approved");
    private static final Transfer TRANSFER_2 = new Transfer(2, 2, 3, "50.00", "Approved");
    private static final Transfer TRANSFER_3 = new Transfer(3, 2, 1, "100.00", "Approved");
    private static final Transfer TRANSFER_4 = new Transfer(3004, 2004, 2003, new BigDecimal("500.00" ));
    private static final Transfer TRANSFER_5 = new Transfer(3005, 2005, 2004,new BigDecimal("500.00" ));

    @Test
    public void getTransferByTransferId() {
    }

    @Test
    public void newTransfer() {
    }

    @Test
    public void getListOfTransfers() {
    }
}