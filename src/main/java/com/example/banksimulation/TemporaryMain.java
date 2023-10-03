package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        System.out.println(barclays.customerHashMap);
        System.out.println(barclays.accountBookHashMap);
        Customer customer1 = new Customer("Joe Bloggs");

        barclays.readLoanCSV();
        barclays.createLoan(customer1, 10, 10000, "HomeLoan");

//        barclays.createLoan(customer1, 2, 1300, "PersonalLoan");
        barclays.writeCSVBankAndCustomerBook("src/main/resources/com/example/banksimulation/ExampleMixOfAccounts.txt");
        System.out.println("Dome");
    }
}
