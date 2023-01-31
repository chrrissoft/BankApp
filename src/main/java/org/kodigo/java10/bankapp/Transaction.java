package org.kodigo.java10.bankapp;

public interface Transaction {

    TransactionTicked makeTransaction(TransactionPetition petition);

}

class TransactionImpl implements Transaction {

    DateResolver dateResolver;
    BalanceDecreaser balanceDecreaser;
    BalanceIncreaser moneyIncrement;

    public TransactionImpl(
            DateResolver dateResolver,
            BalanceDecreaser balanceDecreaser,
            BalanceIncreaser moneyIncrement
    ) {
        this.dateResolver = dateResolver;
        this.balanceDecreaser = balanceDecreaser;
        this.moneyIncrement = moneyIncrement;
    }

    @Override
    public TransactionTicked makeTransaction(TransactionPetition petition) {
        return null;
    }

    /**
     * validates if the amount is major than account money
     *
     * @param amount to transfer.
     * @param money  of the origen account.
     * @return TODO
     */
    private Boolean validateMoney(int amount, int money) {
        return amount <= money;
    }

    /**
     * get the current date
     *
     * @return Date
     */
    private Date getDateResolver() {
        return dateResolver.resolve();
    }

    /**
     * this function decrement the money of the origen account
     * and increment the money of the destination account
     * @param amount to transfer
     * @param origen account to decrement money
     * @param destination account to increment money
     * */
    private void makeTransition(
            int amount, OrigenAccount origen,
            DestinationAccount destination
    ) {
        balanceDecreaser.decrement(amount, origen.number);
        moneyIncrement.increment(amount, destination.number);
    }

}
