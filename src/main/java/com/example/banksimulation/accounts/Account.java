package com.example.banksimulation.accounts;

import com.example.banksimulation.Customer;

public abstract class Account {
    private int accountNumber;
    public String accountType;
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double accountBalance;
    public Customer accountHolder;
   public double interestRateFromCSV = 00.00;

   public double accountTerm = 00;
    public static int nextAccountNumber = 1;
    public void deposit(Account account, double depositAmount){
        account.accountBalance += depositAmount;
    }
    public void withdraw(Account account, double withdrawAmount){
        if (withdrawAmount <= account.accountBalance) {
            account.accountBalance -= withdrawAmount;
        }
        else {
            throw new IllegalArgumentException("Insufficient funds");
        }


    }

    //todo: add method to return account details including balance for UI to show to customer or bank staff
    public String toString(){
        return("Account number: "+accountNumber +"\n" +
                "Account holder: "+accountHolder+"\n" +
                "Balance: "+accountBalance
        );

    }

    Account(int accountNumber, double accountBalance, Customer accountHolder){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;

    }
    Account(Customer accountHolder){
        this.accountHolder = accountHolder;
        accountBalance = 0;
        accountNumber = nextAccountNumber;
    }



}
