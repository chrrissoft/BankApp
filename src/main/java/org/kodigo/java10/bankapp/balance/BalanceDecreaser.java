package org.kodigo.java10.bankapp.balance;

/**
 * This interface is responsible for decreasing money in an account.
 */
interface BalanceDecreaser {

    /**
     * decrease the account balance.
     * @param amount is the money amount to increase
     * @param accountNumber is the number account to increase
     */
    void decrement(double amount, int accountNumber);

}