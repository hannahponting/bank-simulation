package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        Customer firstCustomer = barclays.createCustomer("Joe Bloggs");
        barclays.createAccount(firstCustomer,"current");
        barclays.customerHashMap.get("Joe Bloggs");

        Customer secondCustomer = barclays.createCustomer("Jane Doe");
        barclays.createAccount(secondCustomer, "current");
        //System.out.println(barclays.customerHashMap);
        //System.out.println(barclays.accountBookHashMap);
        System.out.println(barclays.accountBookHashMap.get(1));



    }
}
