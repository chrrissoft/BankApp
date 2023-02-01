package org.kodigo.java10.bankapp;

public class Account {
    public final int number;
    public final double money;

    public Account(int number, double money) {
        this.number = number;
        this.money = money;
    }

    public Account copyAccountNumber(int n) {
        return new Account(n, this.money);
    }

    public Account copyAccountMoney(double m) {
        return new Account(this.number, m);
    }
}
