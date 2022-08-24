package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/account")
public class ApplicationController {
    private AccountDao accountDao;
    private UserDao userDao;

    public ApplicationController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping("/balance")
    public BigDecimal getBalance(Principal principal) {
        int userId = getCurrentUserId(principal);
        BigDecimal balance = accountDao.getAnAccountByUserId(userId).getBalance();
        // getUserAccountByUserPrincipal(principal.getName)
         // string sql account id balance join user table where username = ? (principal.getname)
        // Account.getBalance
        return balance;
    }

    public int getCurrentUserId (Principal principal) {
        return userDao.findByUsername(principal.getName()).getId();
    }


}
