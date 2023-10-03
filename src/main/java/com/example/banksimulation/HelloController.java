package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField accountNumberInput;
    @FXML
    private Label allAccountDetails;
    @FXML
    private TextField depositTextField;
    @FXML
    private TextField withdrawalTextField;
    @FXML
    private Label depositWithdrawalStatus;


    Bank bank;
    public void setBank(Bank bank){
        this.bank = bank;
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    Account requestedAccount;
    @FXML
    protected void getAccountDetails(){
        String output = "Balance: ";
        int requestedAccountNumber = Integer.parseInt(accountNumberInput.getText());
        requestedAccount= bank.accountBookHashMap.get(requestedAccountNumber);
        NumberFormat format = new DecimalFormat("#0.00");
        output += format.format(requestedAccount.accountBalance);
        allAccountDetails.setText(output);

    }

    @FXML
        protected void makeDeposit(){
        Account depositAccount = requestedAccount;
        try{
            double depositAmount = Double.parseDouble(depositTextField.getText());
        depositAccount.deposit(depositAccount,depositAmount);
        depositWithdrawalStatus.setText("Deposit successful, balance updated.");
        getAccountDetails();}
        catch (NumberFormatException numberFormatException){
            depositWithdrawalStatus.setText("Please check you have entered a valid number");
        }

    }
    @FXML
    protected void makeWithdrawal(){
        Account withdrawalAccount = requestedAccount;
        try {
            double withdrawalAmount = Double.parseDouble(withdrawalTextField.getText());
            try {
                withdrawalAccount.withdraw(withdrawalAccount, withdrawalAmount);
                depositWithdrawalStatus.setText("Withdrawal successful, balance updated.");
                getAccountDetails();
            } catch (IllegalArgumentException overdrawn) {
                depositWithdrawalStatus.setText(overdrawn.getMessage());
            }
        }
        catch (NumberFormatException numberFormatException){
            depositWithdrawalStatus.setText("Please check you have entered a valid number");}
    }


}