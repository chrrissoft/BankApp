package org.kodigo.java10.bankapp;


import org.junit.Test;
import org.kodigo.java10.bankapp.TransactionTicked.FailTicked;
import org.kodigo.java10.bankapp.TransactionTicked.FailTicked.Reason;
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
        TransactionPetition petition = getGoodPetition();
        double expectedDestination = repo.get(petition.destination.number).money + petition.amount;
        double expectedOrigen = repo.get(petition.origen.number).money - petition.amount;

        transaction.makeTransaction(petition);
        double givenDestination = repo.get(petition.destination.number).money;
        double givenOrigen = repo.get(petition.origen.number).money;

        assert givenDestination == expectedDestination;
        assert givenOrigen == expectedOrigen;
    }

    @Test
    public void theTransactionIsFailedWhenTheOrigenNotHaveMoney() {
        TransactionPetition petition = getPetitionWithNotMoney();
        double expectedDestination = repo.get(petition.destination.number).money;
        double expectedOrigen = repo.get(petition.origen.number).money;

        transaction.makeTransaction(petition);
        double givenDestination = repo.get(petition.destination.number).money;
        double givenOrigen = repo.get(petition.origen.number).money;

        assert givenDestination == expectedDestination;
        assert givenOrigen == expectedOrigen;
    }

    @Test
    public void theTransactionIsFailedWhenTheOrigenNotFound() {
        TransactionPetition petition = getPetitionWithOrigenAccountNotFound();
        double expectedDestination = repo.get(petition.destination.number).money;

        transaction.makeTransaction(petition);
        double givenDestination = repo.get(petition.destination.number).money;

        assert givenDestination == expectedDestination;
    }

    @Test
    public void theTransactionIsFailedWhenTheDestinationNotFound() {
        TransactionPetition petition = getPetitionWithDestinationAccountNotFound();
        double expectedOrigen = repo.get(petition.origen.number).money;

        transaction.makeTransaction(petition);
        double givenOrigen = repo.get(petition.origen.number).money;

        assert givenOrigen == expectedOrigen;
    }

    @Test
    public void whenNotMoneyReturnFailedTickedWithNotMoneyReason() {
        TransactionPetition petition = getPetitionWithNotMoney();
        TransactionTicked ticked = transaction.makeTransaction(petition);
        assert ticked instanceof FailTicked;
        assert ((FailTicked) ticked).reason == Reason.AMOUNT_OVER_MONEY;
    }

    @Test
    public void whenNotOrigenAccountFoundReturnFailedTickedWithNotAccountFoundReason() {
        TransactionPetition petition = getPetitionWithOrigenAccountNotFound();
        TransactionTicked ticked = transaction.makeTransaction(petition);
        assert ticked instanceof FailTicked;
        assert ((FailTicked) ticked).reason == Reason.ACCOUNT_NOT_FOUND;
    }


    private TransactionPetition getPetitionWithNotMoney() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        Account origenAccount = repo.get(1);
        Account destinationAccount = repo.get(2);

        OrigenAccount origenTransaction =
                new OrigenAccount(origenAccount.money, origenAccount.number, SAVED);
        DestinationAccount destination =
                new DestinationAccount(destinationAccount.number, SAVED);

        return new TransactionPetition(
                amount, conceptTransfer, origenTransaction, destination);
    }

    private TransactionPetition getPetitionWithOrigenAccountNotFound() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        Account destinationAccount = repo.get(2);

        OrigenAccount origenTransaction =
                new OrigenAccount(10000, 10000, SAVED);
        DestinationAccount destination =
                new DestinationAccount(destinationAccount.number, SAVED);

        return new TransactionPetition(
                amount, conceptTransfer, origenTransaction, destination);
    }

    private TransactionPetition getPetitionWithDestinationAccountNotFound() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        Account origenAccount = repo.get(1);

        OrigenAccount origenTransaction =
                new OrigenAccount(origenAccount.money, origenAccount.number, SAVED);
        DestinationAccount destination =
                new DestinationAccount(1000, SAVED);

        return new TransactionPetition(
                amount, conceptTransfer, origenTransaction, destination);
    }

    private TransactionPetition getGoodPetition() {
        double amount = 10.0;
        String conceptTransfer = "I don't know";
        Account origenAccount = repo.get(2);
        Account destinationAccount = repo.get(1);

        OrigenAccount origenTransaction =
                new OrigenAccount(origenAccount.money, origenAccount.number, SAVED);
        DestinationAccount destination =
                new DestinationAccount(destinationAccount.number, SAVED);

        return new TransactionPetition(
                amount, conceptTransfer, origenTransaction, destination);
    }

}