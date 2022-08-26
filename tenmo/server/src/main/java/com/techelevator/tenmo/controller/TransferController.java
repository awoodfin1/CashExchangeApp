package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    @Autowired
    UserDao userDao;
    @Autowired
    TransferDao transferDao;


    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer transferFunds(@RequestBody Transfer transfer, Principal principal) {
        transfer.setUserFrom(userDao.findIdByUsername(principal.getName()));
        Transfer postedTransfer = transferDao.newTransfer(transfer);
        int userFromId = userDao.findIdByUsername(principal.getName());
        int userToId = userDao.findIdByUsername(String.valueOf(postedTransfer.getTransferId()));
        BigDecimal amount = postedTransfer.getAmount();
        transferDao.transferFunds(amount, userFromId, userToId);
        return postedTransfer;
    }


}
