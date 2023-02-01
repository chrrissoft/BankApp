package org.kodigo.java10.bankapp;

import org.kodigo.java10.bankapp.balance.BalanceIManager;
import org.kodigo.java10.bankapp.crud.GetterAccountUseCase;
import org.kodigo.java10.bankapp.crud.GetterAccountUseCase.FindAccountResult;
import org.kodigo.java10.bankapp.crud.GetterAccountUseCase.FindAccountResult.AccountFound;

public interface Transaction {

    TransactionTicked makeTransaction(TransactionPetition petition);

}

class TransactionImpl implements Transaction {

    private final DateResolver dateResolver;
    private final BalanceIManager manager;
    private final GetterAccountUseCase finder;
    private final TransactionTickedBuilder tickedBuilder;


    public TransactionImpl(
            DateResolver dateResolver, BalanceIManager manager,
            GetterAccountUseCase finder, TransactionTickedBuilder tickedBuilder
    ) {
        this.dateResolver = dateResolver;
        this.manager = manager;
        this.finder = finder;
        this.tickedBuilder = tickedBuilder;
    }

    @Override
    public TransactionTicked makeTransaction(TransactionPetition petition) {

        TransactionTicked ticked;

        if (!validateExistingBothAccount(petition.origen, petition.destination)) {
            ticked = tickedBuilder.buildWithAccountNotFound();
            return ticked;
        }

        if (!amountIsValid(petition.origen)) {
            ticked = tickedBuilder.buildWithOverMoney();
            return ticked;
        }

        makeTransition(petition.amount, petition.origen, petition.destination);
        Date date = getDateResolver();
        ticked = tickedBuilder.buildSuccess(
                date, petition.amount, petition.conceptTransfer,
                petition.origen, petition.destination
        );
        return ticked;
    }


    /**
     * get the current date
     *
     * @return Date
     */
    private Date getDateResolver() {
        return dateResolver.resolve();
    }


    /**
     * this function decrement the money of the origen account
     * and increment the money of the destination account
     *
     * @param amount      to transfer
     * @param origen      account to decrement money
     * @param destination account to increment money
     */
    private void makeTransition(
            double amount, OrigenAccount origen, DestinationAccount destination
    ) {
        manager.decrement(amount, origen.number);
        manager.increment(amount, destination.number);
    }

    private boolean validateExistingBothAccount(
            OrigenAccount origen, DestinationAccount destination
    ) {
        FindAccountResult origenResult = finder.tryGet(origen.number);
        FindAccountResult destinationResult = finder.tryGet(destination.number);
        return origenResult instanceof AccountFound &&
                destinationResult instanceof AccountFound;
    }

    private boolean amountIsValid(OrigenAccount origen) {
        return manager.validate(origen.money, origen.number);
    }

}
