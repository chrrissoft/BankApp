package org.kodigo.java10.bankapp.balance;

import org.kodigo.java10.bankapp.Account;
import org.kodigo.java10.bankapp.crud.GetterAccountUseCase;
import org.kodigo.java10.bankapp.crud.UpdateAccountUseCase;

public class BalanceIManagerImpl implements BalanceIManager {

    private final UpdateAccountUseCase updater;
    private final GetterAccountUseCase getter;

    public BalanceIManagerImpl(
            UpdateAccountUseCase updater, GetterAccountUseCase getter
    ) {
        this.updater = updater;
        this.getter = getter;
    }

    @Override
    public void decrement(double amount, int accountNumber) {
        Account account = getter.get(accountNumber);
        double money = account.money;
        Account newAccount = account.copyAccountMoney(money - amount);
        updater.update(newAccount);
    }

    @Override
    public void increment(double amount, int accountNumber) {
        Account account = getter.get(accountNumber);
        double money = account.money;
        Account newAccount = account.copyAccountMoney(money + amount);
        updater.update(newAccount);
    }

    @Override
    public boolean validate(double amount, int accountNumber) {
        double money = getter.get(accountNumber).money;
        return amount <= money;
    }

}
