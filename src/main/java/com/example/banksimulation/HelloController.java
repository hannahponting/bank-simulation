package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField accountNumberInput;
    @FXML
    private Label allAccountDetails;

    Bank bank;

    HelloController(Bank bank){
        this.bank= bank;
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void getAccountDetails(){
        String output = "";
        int requestedAccountNumber = Integer.parseInt(accountNumberInput.getText());
        Account requestedAccount= bank.accountBookHashMap.get(requestedAccountNumber);
        output += requestedAccount.accountBalance;
        allAccountDetails.setText(output);

    }


}