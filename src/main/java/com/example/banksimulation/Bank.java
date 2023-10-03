package com.example.banksimulation;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.opencsv.CSVWriter;

import static java.lang.Integer.valueOf;


public class Bank {
    HashMap<Integer,Account> accountBookHashMap = new HashMap<>();
    HashMap<String,Loan> loanHashMap = new HashMap<>();
    HashMap<String,Customer> customerHashMap = new HashMap<>();
    public double totalDeposit;

    FileInputStream fis;
    Scanner fileScanner;

    //todo: fix createCustomer to work after the hashmap has been read to not repeat account Numbers
    public Customer createCustomer(String customerName){
        Customer customer = new Customer(customerName);
        customerHashMap.put(customerName,customer);
        return customer;
    }
    public void createAccount(Customer accountHolder, String accountType){
        Account account;
        switch(accountType){
            case "savings" -> {account = new SavingsAccount(accountHolder);
            account.accountType = "savings";
            }
            case "cd" ->{account = new CDAccount(accountHolder);
                account.accountType = "cd";}
            default -> {account = new CurrentAccount(accountHolder);
                account.accountType = "current";
            }
        }
        accountBookHashMap.put(account.getAccountNumber(), account);
        accountHolder.accountArrayList.add(account);
    }

    public void createAccount(Customer accountHolder, String accountType, int accountNumber, double accountBalance){
        Account account;
        switch(accountType){
            case "savings" -> {
                account = new SavingsAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "savings";
            }

            case "cd" -> {
                account = new CDAccount(accountNumber, accountBalance, accountHolder);
                account.accountType = "cd";
            }

            default ->  {
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


    public void createLoan(Customer customer, int length, double amount, String type) {
        Loan loan;
        switch(type){
            case "HomeLoan"->{
                loan = new HomeLoan(customer, length, amount, type);
            }
            case "CarLoan"->{
                loan = new CarLoan(customer, length, amount);
            }
            default -> {
                loan = new PersonalLoan(customer, length, amount);
            }

        }
        loanHashMap.put(customer.getCustomerName(), loan);
        customer.loanArrayList.add(loan);

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

    public void writeCSVBankAndCustomerBook(String filepath){
        File file = new File(filepath);
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
    public void calculateTotalDeposit (){

        for (int i = 1; i < accountBookHashMap.size()+1; i++) {
            Account account = accountBookHashMap.get(valueOf(i));
            double accountBalance = account.accountBalance;
            totalDeposit = accountBalance + totalDeposit;
            System.out.println(totalDeposit);

        }
    }
}

