package org.kodigo.java10.bankapp.crud;

import org.kodigo.java10.bankapp.Account;

import java.util.ArrayList;

public interface GetterAccountUseCase {

    FindAccountResult tryGet(int number);

    Account get(int number);

    ArrayList<Account> getAll();

    abstract class FindAccountResult {
        public static class AccountFound extends FindAccountResult {
            Account account;

            public AccountFound(Account account) {
                this.account = account;
            }
        }
        public static class AccountNotFound extends FindAccountResult { }
    }

}

