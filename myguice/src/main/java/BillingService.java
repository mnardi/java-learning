package com.mnardi.javalearning.myguice;

import com.google.inject.Injector;
import com.google.inject.Inject;
import com.google.inject.Guice;
import java.lang.*;
import java.util.*;

public class BillingService {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    BillingService(CreditCardProcessor processor, TransactionLog transactionlog) {
        this.processor = processor;
        this.transactionLog = transactionlog;
    }

    public boolean chargeOrder(long charge)
    {
        return processor.process(charge);
    }


    public void logTotalCharge()
    {
        System.out.println("Total charged " +  this.processor.getCharged());
    }

    public static void main(String[] args)
    {
        Injector injector = Guice.createInjector(new BillingModule());
        BillingService billingService = injector.getInstance(BillingService.class);
        billingService.chargeOrder(10);
        billingService.chargeOrder(8);
        billingService.chargeOrder(5);
        billingService.logTotalCharge();

    }

}
