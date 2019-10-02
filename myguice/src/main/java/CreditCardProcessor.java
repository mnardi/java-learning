package com.mnardi.javalearning.myguice;

public interface CreditCardProcessor {
    public boolean process(long charge);
    public long getCharged();
}
