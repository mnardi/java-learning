package com.mnardi.javalearning.myguicefactory;

public interface CreditCardProcessor {
    public boolean process(long charge);
    public long getCharged();
}
