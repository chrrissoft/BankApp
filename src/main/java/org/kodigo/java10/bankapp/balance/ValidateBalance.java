package org.kodigo.java10.bankapp.balance;


/**
 * It validates than a determinate account have the amount passed or more
 */
public interface ValidateBalance {

    /**
     * @param amount is the amount to verify
     * @param accountNumber is the accountNumber of the account to verify
     * @return true if the account have the amount or have more one, else false
     */
    boolean validate(double amount, int accountNumber);

}
