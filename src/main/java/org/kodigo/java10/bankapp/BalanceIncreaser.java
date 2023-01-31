package org.kodigo.java10.bankapp;


/**
 * This interface is responsible for increasing money in an account.
 * Its implementation has validations to determine that the account
 * exists and other things that may happen.
 */
interface BalanceIncreaser {

    /**
     * increment the account balance.
     * @param amount is the money amount to increase
     * @param accountNumber is the number account to increase
     */
    void increment(int amount, int accountNumber);
}
