package org.kodigo.java10.bankapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface AccountRepository {

    ArrayList<Account> getAll();

    Account get(int number);

    Account delete(int number);

    void delete(Account account);

    Account create(Account newAccount);

    Account update(Account copy);
}

class AccountRepositoryImpl implements AccountRepository {

    private final ArrayList<Account> accounts = new ArrayList<>(List.of(
            new Account(1, 0),
            new Account(2, 2000.0),
            new Account(3, 1000.0),
            new Account(4, 100.50),
            new Account(5, 500.20),
            new Account(6, 1000.0)
    ));

    public AccountRepositoryImpl() {
    }

    @Override
    public ArrayList<Account> getAll() {
        return accounts;
    }

    @Override
    public Account get(int number) {
        AtomicReference<Account> account = new AtomicReference<>();
        accounts.forEach(a -> {
            if (a.number == number) {
                account.set(a);
            }
        });
        return account.get();
    }

    @Override
    public Account delete(int number) {
        return removeByNumber(number);
    }

    @Override
    public void delete(Account account) {
        accounts.remove(account);
    }

    @Override
    public Account create(Account newAccount) {
        accounts.add(newAccount);
        return newAccount;
    }

    @Override
    public Account update(Account copy) {
        Account removed = removeByNumber(copy.number);
        accounts.add(copy);
        return removed;
    }

    private Account removeByNumber(int number) {
        AtomicReference<Account> removed = new AtomicReference<>();
        accounts.forEach(a -> {
            if (a.number == number) {
                removed.set(a);
            }
        });
        accounts.remove(removed.get());
        return removed.get();
    }
}
