package org.kodigo.java10.bankapp;


import org.junit.Test;
import org.kodigo.java10.bankapp.balance.BalanceIManager;
import org.kodigo.java10.bankapp.balance.BalanceIManagerImpl;
import org.kodigo.java10.bankapp.crud.CrudAccountUseCaseImpl;

import static org.kodigo.java10.bankapp.TransactionAccount.TypeAccount.SAVED;

public class TransactionImplTest {

    private final AccountRepository repo = new AccountRepositoryImpl();
    private final DateResolver dateResolver = new DateResolverImpl();
    private final CrudAccountUseCaseImpl finder = new CrudAccountUseCaseImpl(repo);
    private final BalanceIManager manager = new BalanceIManagerImpl(finder, finder);
    private final TransactionTickedBuilder tickedBuilder = new TransactionTickedBuilderImpl();

    TransactionImpl transaction = new TransactionImpl(dateResolver, manager, finder, tickedBuilder);

    @Test
    public void theTransactionIsDoneWhenAllGoingGood() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        OrigenAccount origen = new OrigenAccount(100.0, 2, SAVED);
        DestinationAccount destination = new DestinationAccount(1, SAVED);
        TransactionPetition petition = new TransactionPetition(
                amount, conceptTransfer, origen, destination
        );

        double expectedDestination = repo.get(destination.number).money + petition.amount;
        double expectedOrigen = repo.get(origen.number).money - petition.amount;
        transaction.makeTransaction(petition);
        double givenDestination = repo.get(destination.number).money;
        double givenOrigen = repo.get(origen.number).money;
        assert givenDestination == expectedDestination;
        assert givenOrigen == expectedOrigen;
    }

    @Test
    public void theTransactionIsFailedWhenTheOrigenNotHaveMoney() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        OrigenAccount origen = new OrigenAccount(100.0, 6, SAVED);
        DestinationAccount destination = new DestinationAccount(1, SAVED);
        TransactionPetition petition = new TransactionPetition(
                amount, conceptTransfer, origen, destination
        );

        double expectedDestination = repo.get(destination.number).money;
        double expectedOrigen = repo.get(origen.number).money;
        transaction.makeTransaction(petition);
        double givenDestination = repo.get(destination.number).money;
        double givenOrigen = repo.get(origen.number).money;
        assert givenDestination == expectedDestination;
        assert givenOrigen == expectedOrigen;
    }

}