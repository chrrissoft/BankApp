package org.kodigo.java10.bankapp;


abstract class TransactionAccount {
    int number;
    TypeAccount type;

    TransactionAccount(int number, TypeAccount type) {
        this.number = number;
        this.type = type;
    }

    enum TypeAccount {
        SAVED
    }
}

class DestinationAccount extends TransactionAccount {
    DestinationAccount(int number, TypeAccount type) {
        super(number, type);
    }
}

class OrigenAccount extends TransactionAccount {
    int money;

    OrigenAccount(int money, int number, TypeAccount type) {
        super(number, type);
        this.money = money;
    }

}
