package com.example.banksimulation;

public class CDAccount extends Account {




    CDAccount(int accountNumber, int accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
    }

    CDAccount(Customer accountHolder) {
        super(accountHolder);
    }



    public double getInterest() {
        return 0;
    }



    public void calculateInterest() {
        // A = P(1+RT) P = initial amount R= rate T=time in years

    }






}
