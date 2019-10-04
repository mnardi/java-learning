package com.mnardi.javalearning.myguicefactory;

public class CreditCardDoubleDummy implements CreditCardProcessor{
    CreditCardDoubleDummy()
    {
        this.charge=0;
    }

    @Override
    public boolean process(long charge)
    {
        this.charge += (2*charge);
        return true;
    }

    @Override
    public long getCharged()
    {
        return this.charge;
    }

    private long charge;

}
