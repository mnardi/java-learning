package com.mnardi.javalearning.myguice;

import com.google.inject.AbstractModule;

public class BillingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransactionLog.class).to(TransactionLogDummy.class);
        bind(CreditCardProcessor.class).to(CreditCardDummy.class);

    }
}
