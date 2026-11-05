package com.arun.springlabs.transactionaldemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transfer1")
    public String transferWithoutTransactional() throws Exception {
        accountService.transferWithoutServiceTransaction();
        return "Transfer-1 withoutTransactional completed!";
    }

    @PostMapping("/transfer2")
    public String transferWithTransactional() throws Exception {
        accountService.transferWithServiceTransaction();
        return "Transfer-2 with Transactional completed!";
    }


}