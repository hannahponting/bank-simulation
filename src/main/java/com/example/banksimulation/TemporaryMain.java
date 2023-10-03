package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();

        System.out.println(barclays.customerHashMap);
        System.out.println(barclays.accountBookHashMap);
        barclays.writeCSVBankAndCustomerBook("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
    }
}
