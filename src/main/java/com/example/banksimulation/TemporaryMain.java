package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        Customer firstCustomer = barclays.createCustomer("Joe Bloggs");
        barclays.createAccount(firstCustomer,"current");
        Customer secondCustomer = barclays.createCustomer("Jane Doe");
        barclays.createAccount(secondCustomer, "current");



        CDAccount cdAccount = new CDAccount(firstCustomer);
        cdAccount.calculateInterest(100);

        SavingsAccount savingsAccount = new SavingsAccount(firstCustomer);
        savingsAccount.calculateInterest(100, 5);


    }
}
