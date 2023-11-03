package com.example.banksimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.example.banksimulation.accounts.Account;
import com.example.banksimulation.accounts.CDAccount;
import com.example.banksimulation.accounts.CurrentAccount;
import com.example.banksimulation.accounts.SavingsAccount;
import com.example.banksimulation.loans.CarLoan;
import com.example.banksimulation.loans.HomeLoan;
import com.example.banksimulation.loans.Loan;
import com.example.banksimulation.loans.PersonalLoan;
import com.opencsv.CSVWriter;

import static java.lang.Integer.valueOf;


public class Bank {
    public HashMap<Integer, Loan> loanHashMap = new HashMap<>();
    public HashMap<String, Customer> customerHashMap = new HashMap<>();
    public HashMap<Integer, Account> accountBookHashMap = new HashMap<>();
    ArrayList<Double> loanAmountList = new ArrayList<>();
    public final double MAXIMUM_LOAN_RATIO = 0.9;
    public final double MAXIMUM_CUSTOMER_LOAN_EXPOSURE = 0.1;

    public double totalDeposit;


    FileInputStream fis;
    Scanner fileScanner;

    //todo: fix createCustomer to work after the hashmap has been read to not repeat account Numbers
//    public Customer createCustomer(String customerName) {
//        Customer customer = new Customer(customerName);
//        customerHashMap.put(customerName, customer);
//        return customer;
//    }
    public Customer createCustomer(String customerName, String password) {
        Customer customer = new Customer(customerName, password);
        customerHashMap.put(customerName, customer);
        return customer;
    }


    public void createAccount(Account account, Customer accountHolder, String accountType, double accountTerm, double interestRate) {
        checkAccountLimit(accountHolder, accountType, 1, "You already have a " + accountType + " account");

        Account.nextAccountNumber++;
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);
    }

    public void checkAccountLimit(Customer accountHolder, String accountType, int maxLimit, String errorMessage) {
        int accountCount = 0;
        boolean alreadyHasAccount = false;

        for (Account account : customerHashMap.get(accountHolder.getCustomerName()).accountArrayList) {
            if (account.accountType.equals(accountType)) {
                accountCount++;
                if (accountCount >= maxLimit) {
                    throw new IllegalArgumentException(errorMessage);
                }
                alreadyHasAccount = true;
            }
        }
        if (accountType != ProductTypes.CD.name() && alreadyHasAccount) {
            throw new IllegalArgumentException("You already have a " + accountType + " account");
        }
    }


    public void createNewCdAccount(Customer accountHolder, double accountTerm, double interestRate, double balance) {
        checkAccountLimit(accountHolder, ProductTypes.CD.name(), 3, "You may only have up to three CD accounts");
        Account account = new CDAccount(accountHolder);
        account.accountBalance = balance;
        Account.nextAccountNumber++;
        account.interestRateFromCSV = interestRate;
        account.accountTerm = accountTerm;
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);
    }

    public void createAccount(Customer accountHolder, String accountType, int accountNumber, double accountBalance, double accountTerm, double interestRate) {
        Account account;
        switch (accountType.toLowerCase()) {
            case "savings" -> account = new SavingsAccount(accountNumber, accountBalance, accountHolder);

            case "cd" -> {
                account = new CDAccount(accountNumber, accountBalance, accountHolder);
                account.accountTerm = accountTerm;
                account.interestRateFromCSV = interestRate;
            }

            default -> account = new CurrentAccount(accountNumber, accountBalance, accountHolder);
        }
        Account.nextAccountNumber++;
        accountBookHashMap.put(account.getAccountNumber(), account);
        customerHashMap.put(accountHolder.getCustomerName(), accountHolder);
        accountHolder.accountArrayList.add(account);
    }


    public boolean createLoan(Customer customer, int length, double amount, String type) {
        if (checkInitialLoanEligibility(customer, amount)) {
            createLoanDependingOnType(customer, length, amount, type);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInitialLoanEligibility(Customer customer, double loanAmount) {
        double maxLoanMoney = MAXIMUM_LOAN_RATIO * calculateTotalDeposit();
        double currentLoanMoney = calculateTotalLoans();
        double customerCurrentLoanValue = customer.getCustomerLoansBalance();
        if (((currentLoanMoney + loanAmount) > maxLoanMoney)) {
            System.err.println("The bank does not have sufficient deposits to provide this loan");
            return false;
        }
        if (((customerCurrentLoanValue + loanAmount) > ((currentLoanMoney + loanAmount) * MAXIMUM_CUSTOMER_LOAN_EXPOSURE))) {
            System.err.println("The loan would expose the bank too much to this customer");
            return false;
        }
        return true;
    }

    public void createLoanDependingOnType(Customer customer, int length, double amount, String type) {
        Loan loan;
        checkLoanTypeNull(customer, type);
        switch (type.toLowerCase()) {
            case "homeloan" -> loan = new HomeLoan(customer, length, amount);
            case "carloan" -> loan = new CarLoan(customer, length, amount);
            default -> loan = new PersonalLoan(customer, length, amount);
        }
        Loan.nextLoan++;
        loanHashMap.put(loan.loanNumber, loan);
        customer.loanArrayList.add(loan);
    }


    private void checkLoanTypeNull(Customer accountHolder, String type) {
        boolean customerHasLoanType = false;
        for (Loan loan : customerHashMap.get(accountHolder.getCustomerName()).loanArrayList
        ) {
            if (loan.loanType.equals(type)) {
                customerHasLoanType = true;
                break;
            }
        }
        if (customerHasLoanType) {
            throw new IllegalArgumentException("You may only have one loan of this type");
        }

    }

    public void createLoanDependingOnType(Customer customer, int length, double amount, String type, int loanNumber) {
        Loan loan;
        switch (type) {
            case "HomeLoan" -> loan = new HomeLoan(customer, length, amount, loanNumber);
            case "CarLoan" -> loan = new CarLoan(customer, length, amount, loanNumber);
            default -> loan = new PersonalLoan(customer, length, amount, loanNumber);
        }
        Loan.nextLoan++;
        loanHashMap.put(loan.loanNumber, loan);
        customer.loanArrayList.add(loan);
    }


    public void readCSVBankAndCustomerBook() {
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
            fileScanner = new Scanner(fis);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] accountInfo = line.split(Pattern.quote(","));
                Customer customer1;
                if (!customerHashMap.containsKey(accountInfo[1])) {
                    customer1 = new Customer(accountInfo[1]);
                } else {
                    customer1 = customerHashMap.get(accountInfo[1]);
                }
                int accountNumber = Integer.parseInt(accountInfo[0]);
                double accountBalance = Double.parseDouble(accountInfo[2]);
                double accountTerm = Double.parseDouble(accountInfo[4]);
                double interestRate = Double.parseDouble(accountInfo[5]);
                String accountType = accountInfo[3];
                createAccount(customer1, accountType, accountNumber, accountBalance, accountTerm, interestRate);
            }
            fis.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Hey, we couldn't find the file.");
        } catch (IOException ae) {
            throw new RuntimeException();
        }
    }


    public void readLoanCSV() {
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/ExampleLoans.csv");
            fileScanner = new Scanner(fis);
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] loanInfo = line.split(Pattern.quote(","));
                Customer customer1;
                if (!customerHashMap.containsKey(loanInfo[1])) {
                    customer1 = new Customer(loanInfo[1]);
                } else {
                    customer1 = customerHashMap.get(loanInfo[1]);
                }

                double loanAmount = Double.parseDouble(loanInfo[2]);
                String loanType = loanInfo[3];
                int loanLength = Integer.parseInt(loanInfo[4]);
                int loanNumber = Integer.parseInt(loanInfo[0]);
                createLoanDependingOnType(customer1, loanLength, loanAmount, loanType, loanNumber);
                loanAmountList.add(loanAmount);

            }
            fis.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Hey, we couldn't find the file.");
        } catch (IOException ae) {
            throw new RuntimeException();
        }
    }

    //TODO: Add the accountTerm and the interestRate
    public void writeCSVBankAndCustomerBook() {
        File file = new File("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Account Number", "Name", "Account Balance (£)", "Account Type", "Term", "InterestRate"};
            writer.writeNext(header);

            for (int i = 1; (i < accountBookHashMap.size() + 1); i++) {
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
        } catch (IOException e) {
            System.out.println("cant do this");
        }
    }

    public void writeLoanCSV() {
        File file = new File("src/main/resources/com/example/banksimulation/ExampleLoans.csv");
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            String[] header = {"Loan Number", "Name", "Loan Amount(£)", "Loan Type", "Year"};
            writer.writeNext(header);

            for (int i = 1; (i < loanHashMap.size() + 1); i++) {
                Loan loan = loanHashMap.get(valueOf(i));
                String loanNumber = "" + i;
                String name = loan.customer.getCustomerName();
                String loanAmount = "" + loan.loanAmount;
                String LoanType = loan.loanType;
                String loanDuration = "" + loan.loanDuration;
                String[] data = {loanNumber, name, loanAmount, LoanType, loanDuration};
                writer.writeNext(data);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("cant do this");
        }
    }


    public double calculateTotalDeposit() {
        double totalDeposit = 0;
        for (int i = 1; i < accountBookHashMap.size() + 1; i++) {
            Account account = accountBookHashMap.get(valueOf(i));
            double accountBalance = account.accountBalance;
            totalDeposit = accountBalance + totalDeposit;
        }
        return totalDeposit;
    }

    public double calculateTotalLoans() {
        double totalLoans = 0;

        for (Double x : loanAmountList) {
            totalLoans = totalLoans + x;

        }

        return totalLoans;
    }
}

