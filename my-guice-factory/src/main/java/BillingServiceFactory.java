package com.mnardi.javalearning.myguicefactory;


public interface BillingServiceFactory {
    BillingServiceInterface create(int discount);
}