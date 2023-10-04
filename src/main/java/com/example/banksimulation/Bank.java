package com.example.banksimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.opencsv.CSVWriter;

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

    public void createAccount(Customer accountHolder, String accountType) {
        Account account;
        switch (accountType) {
            case "savings" -> {
                account = new SavingsAccount(accountHolder);
                account.accountType = "savings";
            }
            case "cd" -> {
                account = new CDAccount(accountHolder);
                account.accountType = "cd";
            }
            default -> {
                account = new CurrentAccount(accountHolder);
                account.accountType = "current";
            }
        }
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);
    }

    public void createAccount(Customer accountHolder, String accountType, int accountNumber, double accountBalance) {
        Account account;
        switch (accountType) {
            case "savings" -> {
                account = new SavingsAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "savings";
            }

            case "cd" -> {
                account = new CDAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "cd";
            }

            default -> {
                account = new CurrentAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "current";
            }
        }
//        customerHashMap.put(accountHolder.getCustomerName(),accountHolder);
//        int sizeOfAccountMap = accountBookHashMap.size() + 1;
        accountBookHashMap.put(account.getAccountNumber(), account);
        customerHashMap.put(accountHolder.getCustomerName(), accountHolder);
        accountHolder.accountArrayList.add(account);
    }


    public void createLoan(Customer customer, int length, double amount, String type, int loanNumber) {
        Loan loan;
        switch (type) {
            case "HomeLoan" -> {
                if (amount > 2000000001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new HomeLoan(customer, length, amount, loanNumber);

                    loanHashMap.put(loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
            case "CarLoan" -> {
                if (amount > 50001) {
                    System.err.println("Error, cannot borrow that much money");
                } else {
                    loan = new CarLoan(customer, length, amount, loanNumber);
                    loanHashMap.put(loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }
            default -> {
                if (amount > 45001) {
                    System.err.println("Error, cannot borrow that much money");

                } else {
                    loan = new PersonalLoan(customer, length, amount, loanNumber);
                    loanHashMap.put(loanNumber, loan);
                    customer.loanArrayList.add(loan);
                }
            }

        }

    }

    public void createLoan(Customer customer, int length, double amount, String type) {
        Loan loan;
        double maxLoanMoney = 0.9 * calculateTotalDeposit();
//        System.out.println(maxLoanMoney);
        double currentLoanMoney = calculateTotalLoans();
//        System.out.println(currentLoanMoney);
        if (!loanHashMap.containsKey(customer.getCustomerName())) {
            if (currentLoanMoney < maxLoanMoney) {
                createLoanDependingOnType(customer, length, amount, type);
            } else {
                System.err.println("Cannot lend money");
            }
        } else {
            System.out.println("Person does not exist");
        }
    }


    private void createLoanDependingOnType(Customer customer, int length, double amount, String type) {
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
                String accountType = accountInfo[3];
                createAccount(customer1,accountType, accountNumber, accountBalance);
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
                createLoan(customer1,loanLength,loanAmount, loanType, loanNumber);
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


    public void writeCSVBankAndCustomerBook(){
        File file = new File("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
        try{
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Account Number","Name","Account Balance (£)","Account Type"};
            writer.writeNext(header);

            for (int i = 1; (i < accountBookHashMap.size()+1); i++) {
                Account account = accountBookHashMap.get(valueOf(i));
                String accountNumber = ""+i;
                String name = account.accountHolder.getCustomerName();
                String accountBalance = ""+account.accountBalance;
                String accountType = account.accountType;
                String[] data = {accountNumber, name, accountBalance, accountType};
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

