package com.example.banksimulation;

import java.util.ArrayList;

public class Customer {

    Customer(String customerName){
        this.customerName = customerName;
    }
    private String customerName;

    ArrayList<Account> accountArrayList = new ArrayList<>();
    ArrayList<Loan> loanArrayList = new ArrayList<>();
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String toString(){
        return (customerName);
    }

}
