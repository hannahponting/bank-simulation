package com.example.banksimulation;

import com.example.banksimulation.accounts.Account;
import com.example.banksimulation.loans.Loan;

import java.util.ArrayList;

public class Customer {

    Customer(String customerName){
        this.customerName = customerName;
    }
    Customer(String customerName, String customerPassword){
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }
    private String customerName;
    private String customerPassword =  "password";
    void setCustomerPassword(String customerPassword){
        this.customerPassword = customerPassword;
    }
   public String getCustomerPassword(){
        return customerPassword;
    }

   public ArrayList<Account> accountArrayList = new ArrayList<>();
   public ArrayList<Loan> loanArrayList = new ArrayList<>();
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String toString(){
        return (customerName);
    }

    public double getCustomerLoansBalance(){
        Double balance = 0.0;
        for (Loan loan: loanArrayList
        ) {balance += loan.loanAmount;}
        return balance;
    }

}
