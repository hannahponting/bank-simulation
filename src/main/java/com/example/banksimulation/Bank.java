package com.example.banksimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.opencsv.CSVWriter;
import javafx.scene.control.SpinnerValueFactory;

import static java.lang.Integer.valueOf;


public class Bank {
    HashMap<Integer, Loan> loanHashMap = new HashMap<>();
    HashMap<String, Customer> customerHashMap = new HashMap<>();
    HashMap<Integer,Account> accountBookHashMap = new HashMap<>();
    ArrayList<Double> loanAmountList = new ArrayList<>();
  
  public double totalDeposit;


    FileInputStream fis;
    Scanner fileScanner;

    //todo: fix createCustomer to work after the hashmap has been read to not repeat account Numbers
    public Customer createCustomer(String customerName) {
        Customer customer = new Customer(customerName);
        customerHashMap.put(customerName, customer);
        return customer;
    }

    public void createAccount(Customer accountHolder, String accountType, double accountTerm, double interestRate) {
        Account account;
        switch (accountType) {
            case "savings" -> {
                account = new SavingsAccount(accountHolder);
                account.accountType = "Savings";
            }
            case "cd" -> {
                account = new CDAccount(accountHolder);
                account.accountType = "CD";
                account.interestRateFromCSV = interestRate;
                account.accountTerm = accountTerm;
            }
            default -> {
                account = new CurrentAccount(accountHolder);
                account.accountType = "Current";
            }
        }
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);
    }
    public void createNewCdAccount(Customer accountHolder, double accountTerm, double interestRate, double balance) {
        Account account = new CDAccount(accountHolder);
        account.accountBalance = balance;
        account.accountType = "CD";
        account.interestRateFromCSV = interestRate;
        account.accountTerm = accountTerm;
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);}

    public void createAccount(Customer accountHolder, String accountType, int accountNumber, double accountBalance, double accountTerm, double interestRate) {
        Account account;
        switch (accountType) {
            case "savings" -> {
                account = new SavingsAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "Savings";
            }

            case "cd" -> {
                account = new CDAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "CD";
                account.accountTerm = accountTerm;
                account.interestRateFromCSV = interestRate;
            }

            default -> {
                account = new CurrentAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "Current";
            }
        }
//        customerHashMap.put(accountHolder.getCustomerName(),accountHolder);
//        int sizeOfAccountMap = accountBookHashMap.size() + 1;
        accountBookHashMap.put(account.getAccountNumber(), account);
        customerHashMap.put(accountHolder.getCustomerName(), accountHolder);
        accountHolder.accountArrayList.add(account);
    }


    public void createLoan(Customer customer, int length, double amount, String type) {
        double maxLoanMoney = 0.9 * calculateTotalDeposit();
        double currentLoanMoney = calculateTotalLoans();
        if (!loanHashMap.containsKey(customer.getCustomerName())) {
            if ((currentLoanMoney < maxLoanMoney) && (amount < (currentLoanMoney * 0.1))){
                createLoanDependingOnType(customer, length, amount, type);
            } else {
                System.err.println("Cannot lend money");
            }
        } else {
            System.out.println("Person does not exist");
        }
    }


   public void createLoanDependingOnType(Customer customer, int length, double amount, String type) {
        Loan loan;
        switch (type) {
            case "HomeLoan" -> {
                if (amount > 2000000001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new HomeLoan(customer, length, amount);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
            case "CarLoan" -> {
                if (amount > 50001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new CarLoan(customer, length, amount);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }

            default -> {
                if (amount > 45001) {
                    System.err.println("Error, cannot borrow that much money");

                } else {
                    loan = new PersonalLoan(customer, length, amount);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
        }
    }

    public void createLoanDependingOnType(Customer customer, int length, double amount, String type, int loanNumber) {
        Loan loan;
        switch (type) {
            case "HomeLoan" -> {
                if (amount > 2000000001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new HomeLoan(customer, length, amount, loanNumber);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
            case "CarLoan" -> {
                if (amount > 50001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new CarLoan(customer, length, amount, loanNumber);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }

            default -> {
                if (amount > 45001) {
                    System.err.println("Error, cannot borrow that much money");

                } else {
                    loan = new PersonalLoan(customer, length, amount, loanNumber);
                    loanHashMap.put(loan.loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
        }
    }



    public void readCSVBankAndCustomerBook (){
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
            fileScanner = new Scanner (fis);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String [] accountInfo = line.split(Pattern.quote(","));
                Customer customer1;
                if(!customerHashMap.containsKey(accountInfo[1])){
                    customer1 = new Customer(accountInfo[1]);}
                else{
                    customer1 = customerHashMap.get(accountInfo[1]);
                }
                int accountNumber = Integer.parseInt(accountInfo[0]);
                double accountBalance = Double.parseDouble(accountInfo[2]);
                double accountTerm = Double.parseDouble(accountInfo[4]);
                double interestRate = Double.parseDouble(accountInfo[5]);
                String accountType = accountInfo[3];
                createAccount(customer1,accountType, accountNumber, accountBalance, accountTerm, interestRate);
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



    public void readLoanCSV (){
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/ExampleLoans.csv");
            fileScanner = new Scanner (fis);
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String [] loanInfo = line.split(Pattern.quote(","));
                Customer customer1;
                if(!customerHashMap.containsKey(loanInfo[1])){
                    customer1 = new Customer(loanInfo[1]);}
                else{
                    customer1 = customerHashMap.get(loanInfo[1]);
                }
             
                double loanAmount = Double.parseDouble(loanInfo[2]);
                String loanType = loanInfo[3];
                int loanLength = Integer.parseInt(loanInfo[4]);
                int loanNumber = Integer.parseInt(loanInfo[0]);
                createLoanDependingOnType(customer1,loanLength,loanAmount, loanType, loanNumber);
                loanAmountList.add(loanAmount);

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

//TODO: Add the accountTerm and the interestRate
    public void writeCSVBankAndCustomerBook(){
        File file = new File("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
        try{
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Account Number","Name","Account Balance (£)","Account Type","Term","InterestRate"};
            writer.writeNext(header);

            for (int i = 1; (i < accountBookHashMap.size()+1); i++) {
                Account account = accountBookHashMap.get(valueOf(i));
                String accountNumber = String.valueOf(i);
                String name = account.accountHolder.getCustomerName();
                String accountBalance = String.valueOf(account.accountBalance);
                String accountType = account.accountType;
                String accountTerm = String.valueOf(account.accountTerm);
                String interestRate = String.valueOf(account.interestRateFromCSV);


                String[] data = {accountNumber, name, accountBalance, accountType, accountTerm, interestRate};
                writer.writeNext(data);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("cant do this");
        }
    }

    public void writeLoanCSV(){
        File file = new File("src/main/resources/com/example/banksimulation/ExampleLoans.csv");
        try{
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Loan Number", "Name","Loan Amount(£)","Loan Type", "Year"};
            writer.writeNext(header);

            for (int i = 1; (i < loanHashMap.size()+1); i++) {
                Loan loan = loanHashMap.get(valueOf(i));
                String loanNumber = ""+i;
                String name = loan.customer.getCustomerName();
                String  loanAmount = ""+ loan.loanAmount;
                String LoanType = loan.loanType;
                String loanDuration = ""+loan.loanDuration;
                String[] data = {loanNumber, name, loanAmount, LoanType, loanDuration};
                writer.writeNext(data);
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("cant do this");
        }
    }

  
    public double calculateTotalDeposit (){
        double totalDeposit = 0;
        for (int i = 1; i < accountBookHashMap.size()+1; i++) {
            Account account = accountBookHashMap.get(valueOf(i));
            double accountBalance = account.accountBalance;
            totalDeposit = accountBalance + totalDeposit;
        }
        return totalDeposit;
    }

    public double calculateTotalLoans (){
        double totalLoans = 0;

        for (Double x:loanAmountList) {
            totalLoans = totalLoans + x;

        }

        return totalLoans;
    }
}

