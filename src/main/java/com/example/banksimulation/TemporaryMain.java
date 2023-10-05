package com.example.banksimulation;

public class TemporaryMain {
    public static void main(String[] args) {
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        barclays.readLoanCSV();

        System.out.println(barclays.customerHashMap);
        System.out.println(barclays.accountBookHashMap);

       Customer customer = new Customer("Hannah");
//        barclays.createLoan(customer, 2, 1, "PersonalLoan");

        barclays.writeLoanCSV();


//        barclays.createAccount(customer, "cd", 4, 3.4);
        barclays.writeCSVBankAndCustomerBook();

        HomeLoan homeLoan = new HomeLoan(customer, 3, 10000);

        double moneys = homeLoan.addInterestToLoan(10000, 3);
        System.out.println(moneys);
    }
}
