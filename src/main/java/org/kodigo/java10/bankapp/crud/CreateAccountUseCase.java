package org.kodigo.java10.bankapp.crud;

import org.kodigo.java10.bankapp.Account;

public interface CreateAccountUseCase {
    Account create(Account newAccount);
}
