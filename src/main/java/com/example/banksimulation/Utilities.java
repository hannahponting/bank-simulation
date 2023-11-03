package com.example.banksimulation;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Utilities {

    public static double parsePositiveDouble(String input) {
         try {
            double output = Double.parseDouble(input);
            if (output < 0) {
                throw new NumberFormatException("Input must be a positive number");
            } else return output;
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("Input must be a positive number");
        }
    }


    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static double calculateLoanInterest(double initialLoan, int length, double interestRate) {

        double finalLoan;

        double x = Math.pow((1 + ((interestRate / 100) / 12)), (length * 12)) - 1;
        double y = ((interestRate / 100) / 12) * Math.pow((1 + ((interestRate / 100) / 12)), (length * 12));


        double loanWithInterest = initialLoan / (x / y);
        String trimmedNumber = df.format(loanWithInterest);
        double loanWithInterest2 = Double.parseDouble(trimmedNumber);
        finalLoan = loanWithInterest2;


        return finalLoan;
    }
}

