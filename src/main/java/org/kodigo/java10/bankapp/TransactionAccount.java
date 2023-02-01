package org.kodigo.java10.bankapp;


/**
 * super class to [DestinationAccount] and [OrigenAccount]
 */
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

/**
 * Class to pass to the [TransactionPetition]. this allows the [Transaction]
 * object to have to know the real accounts with all their properties.
 **/
class DestinationAccount extends TransactionAccount {
    DestinationAccount(int number, TypeAccount type) {
        super(number, type);
    }
}

/**
 * Class to pass to the [TransactionPetition]. this allows the [Transaction]
 * object to have to know the real accounts with all their properties.
 * This class has an additional property with respect to the [Transaction]
 * which is money. Since "Mom" does not have to know how much money [DestinationAccount] has
 **/
class OrigenAccount extends TransactionAccount {
    double money;

    OrigenAccount(double money, int number, TypeAccount type) {
        super(number, type);
        this.money = money;
    }

}
