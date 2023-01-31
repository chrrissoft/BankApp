package org.kodigo.java10.bankapp;



interface TransactionTicked {

    class SuccessTicked implements TransactionTicked {
        Date date;
        int amount;
        String conceptTransfer;
        TransactionAccount origen;
        TransactionAccount destination;
    }

    class FailTicked implements TransactionTicked {
        Reason reason;
        enum Reason {
            NUMBER_NOT_FOUND, AMOUNT_OVER_MONEY
        }

    }

}
