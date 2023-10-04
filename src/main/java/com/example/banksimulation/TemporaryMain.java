package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        barclays.readLoanCSV();

        System.out.println(barclays.customerHashMap);
        System.out.println(barclays.accountBookHashMap);
//
//      barclays.createLoan(customer1, 2, 1300, "PersonalLoan");

        barclays.writeLoanCSV();
        barclays.writeCSVBankAndCustomerBook();
    }
}
