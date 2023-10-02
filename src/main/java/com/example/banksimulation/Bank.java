package com.example.banksimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Bank {
    HashMap<Integer,Account> accountBookHashMap = new HashMap<>();
    HashMap<String,Customer> customerHashMap = new HashMap<>();

    FileInputStream fis;
    Scanner fileScanner;

    //todo: write method to read in a csv of existing accounts and populate the HashMaps

    public Customer createCustomer(String customerName){
        Customer customer = new Customer(customerName);
        customerHashMap.put(customerName,customer);
        return customer;
    }
    public void createAccount(Customer accountHolder, String accountType){
        Account account;
        switch(accountType){
            default ->{
                account = new CurrentAccount(accountHolder);
            }
        }
//        customerHashMap.put(accountHolder.getCustomerName(),accountHolder);
        accountBookHashMap.put(account.getAccountNumber(),account);
        accountHolder.accountArrayList.add(account);
    }

    public void readCSVBankAndCustomerBook (){
        try {
            fis = new FileInputStream("src/main/resources/com/example/banksimulation/ExampleCurrentAccounts.txt");
            fileScanner = new Scanner (fis);
            fileScanner.nextLine();
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String [] accountInfo = line.split(Pattern.quote(","));
                Customer customer1 = new Customer(accountInfo[1]);
                int accountNumber = Integer.parseInt(accountInfo[0]);
                int accountBalance = Integer.parseInt(accountInfo[2]);
                Account newAccount = new Account(accountNumber, accountBalance,customer1);
                accountBookHashMap.put(accountNumber, newAccount);

                customerHashMap.put(accountInfo[1], customer1);

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
}

