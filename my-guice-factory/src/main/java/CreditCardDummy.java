package com.mnardi.javalearning.myguicefactory;

public class CreditCardDummy implements CreditCardProcessor{
    CreditCardDummy()
    {
        this.charge=0;
    }

    @Override
    public boolean process(long charge)
    {
        this.charge += charge;
        return true;
    }

    @Override
    public long getCharged()
    {
        return this.charge;
    }

    private long charge;

}
