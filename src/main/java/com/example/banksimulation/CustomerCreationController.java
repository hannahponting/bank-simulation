package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerCreationController {
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private Bank bank;

    @FXML
    private TextField nameInput;
    @FXML
    private Button registerButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField passwordInput;

    @FXML
    private void registerNewCustomer(){
        String proposedName = nameInput.getText();
        String password = passwordInput.getText();
        if (bank.customerHashMap.get(proposedName) == null){
            bank.createCustomer(proposedName,password);
            statusLabel.setText("Account created");
        }
        else{
            statusLabel.setText("That user already exists");
        }

    }

}
