package com.example.banksimulation;

public class Utilities {

    public static double parsePositiveDouble(String input){
        double output = Double.parseDouble(input);
        if(output < 0){
            throw new NumberFormatException("Input must be a positive number");
        }
        else return output;
    }
}
