package org.kodigo.java10.bankapp;


class AccountValidatorUseCase implements
        AccountExistUseCase, BalanceValidatorUseCase {

    @Override
    public Boolean validate(int accountNumber) {
        return null;
    }

    @Override
    public Boolean validate(int amount, int accountNumber) {
        return null;
    }

}
