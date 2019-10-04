package com.mnardi.javalearning.myguicefactory;


import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.*;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.Guice;

import java.awt.geom.RoundRectangle2D;
import java.lang.*;
import java.util.*;

public class BillingService implements BillingServiceInterface  {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;
    private int discount;
    private int countOperation;

    @Inject
    BillingService(  CreditCardProcessor processor,
                     TransactionLog transactionlog,
                     @Assisted int discount)
    {
        this.processor = processor;
        this.transactionLog = transactionlog;

        this.discount = discount;

        this.countOperation=0;
    }



    @Override
    public boolean chargeOrder(long charge)
    {
        countOperation++;
        if(charge < discount)
            return processor.process(0);
        else
            return processor.process(charge-discount);
    }

    @Override
    public void printTotalCharge()
    {
        System.out.println("Total charged in " + countOperation + " operations:  " +  this.processor.getCharged());
    }

    public static void main(String[] args)
    {

        Injector guice = Guice.createInjector(new BillingModule());
        BillingServiceFactory billingServicefactory = guice.getInstance(BillingServiceRealFactory.class);
        BillingServiceInterface billingService = billingServicefactory.create(2);
        billingService.chargeOrder(10);
        billingService.chargeOrder(8);
        billingService.chargeOrder(-5);
        billingService.printTotalCharge();



    }

}
