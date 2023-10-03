package com.example.banksimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class SavingsAccount extends Account {

    SavingsAccount(int accountNumber, int accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
    }
    SavingsAccount(Customer accountHolder) {
        super(accountHolder);
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
            double savingsWithInterest = initialInvestmentAmount * (((interestRate/100)*dayOftheYear)/daysInYear);
            String trimmedNumber = df.format(savingsWithInterest);
            double totalAmountWithInterest2 = Double.parseDouble(trimmedNumber);
            finalInvestmentAmount += totalAmountWithInterest2;
            System.out.println(finalInvestmentAmount);
        }
    }






    }




