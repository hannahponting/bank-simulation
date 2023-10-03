package com.example.banksimulation;

public class Account {
    private int accountNumber;
    String accountType;
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    double accountBalance;
    Customer accountHolder;
    private static int nextAccountNumber = 1;
    public void deposit(Account account, double depositAmount){
        account.accountBalance += depositAmount;
    }
    public void withdraw(Account account, double withdrawAmount){
        if (withdrawAmount <= account.accountBalance) {
            account.accountBalance -= withdrawAmount;
        }
        else {
            System.out.println("Insufficient funds");
        }

        //todo: add method to return account details including balance for UI to show to customer or bank staff
    }
    Account(int accountNumber, double accountBalance, Customer accountHolder){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
        nextAccountNumber++;
    }
    Account(Customer accountHolder){
        this.accountHolder = accountHolder;
        accountBalance = 0;
        accountNumber = nextAccountNumber++;
    }
}
