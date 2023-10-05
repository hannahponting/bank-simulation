package com.example.banksimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CDAccount extends Account {
    ArrayList <Integer> yearList = new ArrayList<>();

    public ArrayList<Double> getInterestRate() {
        return interestRate;
    }

    ArrayList<Double> interestRate = new ArrayList<>();

    private static final DecimalFormat df = new DecimalFormat("0.00");






    CDAccount(int accountNumber, double accountBalance, Customer accountHolder) {
        super(accountNumber, accountBalance, accountHolder);
    }

    CDAccount(Customer accountHolder) {
        super(accountHolder);
    }



    public double getInterest() {
        return 0;
    }

    public void readCSVBankAndCustomerBook (){
        FileInputStream fis;
        Scanner fileScanner;
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/CDInterestRates.csv");
            fileScanner = new Scanner(fis);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String [] interestRateInfo = line.split(Pattern.quote(","));

                yearList.add(Integer.parseInt(interestRateInfo[0]));
                interestRate.add(Double.parseDouble(interestRateInfo[1]));
            }
            fis.close();

        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("Hey, we couldn't find the file.");
        }
        catch (IOException ae){
            throw new RuntimeException();
        }
    }



    public ArrayList<Double> calculateInterest(double initialInvestmentAmount) {
        ArrayList<Double> investment = new ArrayList<>();
        readCSVBankAndCustomerBook();
        for (int i = 0; i < yearList.size(); i++) {
            double totalAmountWithInterest = initialInvestmentAmount * (Math.pow((1 + (interestRate.get(i)/100)), yearList.get(i)) - 1);
            String trimmedNumber = df.format(totalAmountWithInterest);
            double totalAmountWithInterest2 = Double.parseDouble(trimmedNumber);
            investment.add(totalAmountWithInterest2);
        }
        return investment;
    }
    @Override
    public void deposit(Account account, double depositAmount){
        throw new IllegalArgumentException("CD accounts cannot receive new deposits");
    }
    @Override
    public void withdraw(Account account, double withdrawAmount){
        throw new IllegalArgumentException("CD accounts cannot perform withdrawals");
    }





}
