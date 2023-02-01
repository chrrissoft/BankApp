package org.kodigo.java10.bankapp.crud;

import org.kodigo.java10.bankapp.Account;
import org.kodigo.java10.bankapp.AccountRepository;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CrudAccountUseCaseImpl implements CrudAccountUseCase {

    private final AccountRepository repo;

    public CrudAccountUseCaseImpl(AccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public FindAccountResult tryGet(int number) {
        ArrayList<Account> accounts = repo.getAll();
        AtomicReference<FindAccountResult> result = new AtomicReference<>();
        accounts.forEach(a -> {
            if (a.number == number) result.set(new FindAccountResult.AccountFound(a));
        });
        return result.get();
    }

    @Override
    public Account get(int number) {
        return repo.get(number);
    }

    @Override
    public ArrayList<Account> getAll() {
        return repo.getAll();
    }

    @Override
    public void delete(Account account) {
        repo.delete(account);
    }

    @Override
    public Account delete(int number) {
        return repo.delete(number);
    }

    @Override
    public Account update(Account newAccount) {
        return repo.update(newAccount);
    }

    @Override
    public Account create(Account newAccount) {
        return repo.create(newAccount);
    }
}
