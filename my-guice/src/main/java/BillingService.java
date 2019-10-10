package com.mnardi.javalearning.myguice;


import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Inject;
import com.google.inject.Guice;
import java.lang.*;

public class BillingService  {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;
    private int counter;


    @Inject
    BillingService(  CreditCardProcessor processor,
                     TransactionLog transactionlog)
    {
        this.processor = processor;
        this.transactionLog = transactionlog;
     }



    public boolean chargeOrder(long charge)
    {
        counter++;
        return processor.process(charge);
    }

    public void logTotalCharge()
    {
        System.out.println("Total charged in " + counter + " operations:  " +  this.processor.getCharged());
    }

    public static void main(String[] args)
    {

        //Injector injector = Guice.createInjector(new BillingModule());
        Injector injector = Guice.createInjector(new AbstractModule()
                                                {
                                                   protected void configure() {
                                                        bind(TransactionLog.class).to(TransactionLogDummy.class);
                                                        bind(CreditCardProcessor.class).to(CreditCardDoubleDummy.class);
                                                    }
                                              });


        BillingService billingService = injector.getInstance(BillingService.class);
        billingService.chargeOrder(10);
        billingService.chargeOrder(8);
        billingService.chargeOrder(-5);
        billingService.logTotalCharge();



    }

}
