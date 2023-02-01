package org.kodigo.java10.bankapp.crud;

import org.kodigo.java10.bankapp.Account;

public interface UpdateAccountUseCase {
    Account update(Account newAccount);
}
