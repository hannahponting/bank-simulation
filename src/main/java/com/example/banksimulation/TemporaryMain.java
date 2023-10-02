package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        Customer firstCustomer = barclays.createCustomer("Joe Bloggs");
        barclays.createAccount(firstCustomer,"current");
        Customer secondCustomer = barclays.createCustomer("Jane Doe");
        barclays.createAccount(secondCustomer, "current");
        System.out.println(barclays.customerHashMap);
        System.out.println(barclays.accountBookHashMap);
    }
}
