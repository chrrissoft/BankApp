package org.kodigo.java10.bankapp.balance;


/**
 * This interface is responsible for increasing money in an account.
 */
interface BalanceIncreaser {

    /**
     * increment the account balance.
     * @param amount is the money amount to increase
     * @param accountNumber is the number account to increase
     */
    void increment(double amount, int accountNumber);

}
