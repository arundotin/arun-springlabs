
package com.arun.springlabs.transactionaldemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements CommandLineRunner {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        // This is just to fill-in the database with some values - for demo purpose

        accountRepository.save(new Account("Arun", 10000));
        accountRepository.save(new Account("Sai", 5000));

        System.out.println("Accounts initialized!");
    }

    public void transferWithoutServiceTransaction() throws Exception {

        Account sai = accountRepository.findById("Sai")
                .orElseThrow();

        Account arun = accountRepository.findById("Arun")
                .orElseThrow();

        sai.setBalance(sai.getBalance() - 1000);
        arun.setBalance(arun.getBalance() + 1000);

        accountRepository.save(sai);
        accountRepository.save(arun);

        throw new Exception("Something went wrong during transfer!");
    }

    @Transactional
    //@Transactional(rollbackFor = Exception.class)
    public void transferWithServiceTransaction() throws Exception {

        Account sai = accountRepository.findById("Sai")
                .orElseThrow();

        Account arun = accountRepository.findById("Arun")
                .orElseThrow();

        sai.setBalance(sai.getBalance() - 1000);
        arun.setBalance(arun.getBalance() + 1000);

        accountRepository.save(sai);
        accountRepository.save(arun);

        throw new Exception("Something went wrong during transfer!");
    }

}
