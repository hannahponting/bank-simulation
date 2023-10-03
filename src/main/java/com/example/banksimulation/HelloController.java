package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField accountNumberInput;
    @FXML
    private TextField customerNameInput;
    @FXML
    private Label allAccountDetails;
    @FXML
    private TextField depositTextField;
    @FXML
    private TextField withdrawalTextField;
    @FXML
    private Label depositWithdrawalStatus;

    @FXML
    private VBox accountSelectionRadioButtonHolder;
//    @FXML
//    private Button confirmAccountSelection;

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
    protected void getAccountDetails(int selectedAccount){
        String output = "Balance: ";
//        int requestedAccountNumber = Integer.parseInt(accountNumberInput.getText());
        requestedAccount= bank.accountBookHashMap.get(selectedAccount);
        NumberFormat format = new DecimalFormat("#0.00");
        output += format.format(requestedAccount.accountBalance);
        allAccountDetails.setText(output);
    }
    @FXML
    protected void getCustomerAccounts(){
        String customerName = customerNameInput.getText();
        Customer currentCustomer = bank.customerHashMap.get(customerName);
        System.out.println(currentCustomer);
        ToggleGroup toggleGroup = new ToggleGroup();
        for (Account accountToBeAdded: currentCustomer.accountArrayList
             ) {
            HBox accountHbox = new HBox();
            RadioButton radioButton = new RadioButton(accountToBeAdded.accountType);
            accountHbox.getChildren().add(radioButton);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnAction(e -> getAccountDetails(accountToBeAdded.getAccountNumber()));
//            Label accountBalance = new Label("Balance: " + accountToBeAdded.accountBalance);
//            accountHbox.getChildren().add(accountBalance);
            accountSelectionRadioButtonHolder.getChildren().add(accountHbox);

        }
    }

    @FXML
        protected void makeDeposit(){
        Account depositAccount = requestedAccount;
        try{
            double depositAmount = Double.parseDouble(depositTextField.getText());
        depositAccount.deposit(depositAccount,depositAmount);
        depositWithdrawalStatus.setText("Deposit successful, balance updated.");
        getAccountDetails(requestedAccount.getAccountNumber());}
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
                getAccountDetails(requestedAccount.getAccountNumber());
            } catch (IllegalArgumentException overdrawn) {
                depositWithdrawalStatus.setText(overdrawn.getMessage());
            }
        }
        catch (NumberFormatException numberFormatException){
            depositWithdrawalStatus.setText("Please check you have entered a valid number");}
    }


}