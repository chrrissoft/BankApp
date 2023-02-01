package org.kodigo.java10.bankapp;


interface TransactionTicked {

    class SuccessTicked implements TransactionTicked {
        Date date;
        double amount;
        String conceptTransfer;
        TransactionAccount origen;
        TransactionAccount destination;

        public SuccessTicked(
                Date date, double amount,
                String conceptTransfer,
                TransactionAccount origen,
                TransactionAccount destination
        ) {
            this.date = date;
            this.amount = amount;
            this.conceptTransfer = conceptTransfer;
            this.origen = origen;
            this.destination = destination;
        }
    }

    class FailTicked implements TransactionTicked {
        Reason reason;

        public FailTicked(Reason reason) {
            this.reason = reason;
        }

        public enum Reason {
            ACCOUNT_NOT_FOUND, AMOUNT_OVER_MONEY
        }

    }

}
