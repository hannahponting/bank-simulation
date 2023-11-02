package com.example.banksimulation.accounts;

import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;

import java.text.DecimalFormat;


public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, double accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
        this.accountType = ProductTypes.Savings.name();
    }
    public SavingsAccount(Customer accountHolder) {
        super(accountHolder);
        this.accountType = ProductTypes.Savings.name();
    }
    public double getInterest() {
        return 0;
    }

    double interestRate = 4.1;
    int daysInYear = 365;
    private static final DecimalFormat df = new DecimalFormat("0.00");


    public void calculateInterest(double initialInvestmentAmount, int dayOftheYear) {
        double finalInvestmentAmount = initialInvestmentAmount;
        for (int i = 0; i < dayOftheYear; i++) {
            double savingsWithInterest = initialInvestmentAmount * (Math.pow((1 + (interestRate/100)), 1) - 1);
            String trimmedNumber = df.format(savingsWithInterest);
            double totalAmountWithInterest2 = Double.parseDouble(trimmedNumber);
            finalInvestmentAmount += totalAmountWithInterest2;
            System.out.println(finalInvestmentAmount);
        }
    }






    }




