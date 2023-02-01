package org.kodigo.java10.bankapp;

/**
 * It represents a transaction petition
 **/
class TransactionPetition {
    double amount;
    String conceptTransfer;
    OrigenAccount origen;
    DestinationAccount destination;

    public TransactionPetition(
            double amount, String conceptTransfer,
            OrigenAccount origen, DestinationAccount destination
    ) {
        this.amount = amount;
        this.conceptTransfer = conceptTransfer;
        this.origen = origen;
        this.destination = destination;
    }
}
