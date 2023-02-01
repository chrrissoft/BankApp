package org.kodigo.java10.bankapp;

import org.kodigo.java10.bankapp.TransactionTicked.FailTicked;
import org.kodigo.java10.bankapp.TransactionTicked.SuccessTicked;

import static org.kodigo.java10.bankapp.TransactionTicked.FailTicked.Reason.ACCOUNT_NOT_FOUND;
import static org.kodigo.java10.bankapp.TransactionTicked.FailTicked.Reason.AMOUNT_OVER_MONEY;

interface TransactionTickedBuilder {

    SuccessTicked buildSuccess(
            Date date, double amount,
            String conceptTransfer,
            TransactionAccount origen,
            TransactionAccount destination
    );

    FailTicked buildWithAccountNotFound();
    FailTicked buildWithOverMoney();

}

class TransactionTickedBuilderImpl implements TransactionTickedBuilder {


    @Override
    public SuccessTicked buildSuccess(
            Date date, double amount,
            String conceptTransfer,
            TransactionAccount origen,
            TransactionAccount destination
    ) {
        return new SuccessTicked(date, amount, conceptTransfer, origen, destination);
    }

    @Override
    public FailTicked buildWithAccountNotFound() {
        return new FailTicked(ACCOUNT_NOT_FOUND);
    }

    @Override
    public FailTicked buildWithOverMoney() {
        return new FailTicked(AMOUNT_OVER_MONEY);
    }

}
