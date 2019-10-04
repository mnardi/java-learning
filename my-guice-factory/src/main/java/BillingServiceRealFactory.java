package com.mnardi.javalearning.myguicefactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BillingServiceRealFactory implements BillingServiceFactory {
    private final Provider<TransactionLog> transactionLogProvider;
    private final Provider<CreditCardProcessor> creditCardProcessorProvider;

    @Inject
    public BillingServiceRealFactory( Provider<TransactionLog> transactionLogProvider, Provider<CreditCardProcessor> creditCardProcessorProvider)
    {
        this.transactionLogProvider =  transactionLogProvider;
        this.creditCardProcessorProvider =  creditCardProcessorProvider;
    }

    @Override
    public BillingServiceInterface create(int discount)
    {
        return new BillingService(
                creditCardProcessorProvider.get(),
                transactionLogProvider.get(),
                discount);
    }

}