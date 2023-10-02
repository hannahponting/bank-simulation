package com.example.banksimulation;

import java.util.HashMap;

public class Bank {
    HashMap<Integer,Account> accountBookHashMap = new HashMap<>();
    HashMap<String,Customer> customerHashMap = new HashMap<>();

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
//    public void createAccount(double balance, String accountNameHolder) {
//
//        Account account = new Account(balance);
//        account.setAccountHolderName(accountNameHolder);
//        accountMap.put(account.getAccountNumber(), account);
//        System.out.println("Account created");
}

