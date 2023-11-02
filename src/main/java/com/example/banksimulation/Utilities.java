package com.example.banksimulation;

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
}
