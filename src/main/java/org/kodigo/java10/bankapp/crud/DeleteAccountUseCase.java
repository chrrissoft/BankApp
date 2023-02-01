package org.kodigo.java10.bankapp.crud;

import org.kodigo.java10.bankapp.Account;

public interface DeleteAccountUseCase {
    void delete(Account account);
    Account delete(int account);
}
