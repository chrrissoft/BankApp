package org.kodigo.java10.bankapp;

/**
 * This interface is responsible for decreasing money in an account.
 * Its implementation has validations to determine that the account
 * exists and other things that may happen.
 */
interface BalanceDecreaser {

    /**
     * decrease the account balance.
     * @param amount is the money amount to increase
     * @param accountNumber is the number account to increase
     */
    void decrement(int amount, int accountNumber);
}