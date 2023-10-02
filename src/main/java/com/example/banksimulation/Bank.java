package com.example.banksimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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

    public void readCSVbankbook (){
        try {
            fis = new FileInputStream("src/main/resources");
            fileScanner = new Scanner (fis);
            while (fileScanner.hasNextLine()){

            }
            fis.close();

        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("Placeholder");
        }
        catch (IOException ae){
            throw new RuntimeException();
        }

    }





}

