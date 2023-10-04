package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateAccountController {
private LoginController loginController;
public void setLoginController(LoginController loginController){
    this.loginController = loginController;
}
    private Bank bank;
    private Customer customer;
    public void initialiseToggleGroup(){
        currentAccountRadioButton.setToggleGroup(toggleGroup);
        savingsAccountRadioButton.setToggleGroup(toggleGroup);
        cdAccountRadioButton.setToggleGroup(toggleGroup);
    }
    public void setCustomer(Customer customer) { this.customer = customer;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    private String accountType;
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private RadioButton currentAccountRadioButton;
    @FXML
    private RadioButton savingsAccountRadioButton;
    @FXML
    private RadioButton cdAccountRadioButton;
    @FXML
    private Button requestNewAccount;
    @FXML
    private Label statusLabel;

    @FXML
    private void selectCurrentAccount(){
        accountType = "current";
    }
    @FXML
    private void selectSavingsAccount(){
        accountType = "savings";
    }
    @FXML
    private void selectCdAccount(){
        accountType = "cd";
    }
    @FXML
    private void createSelectedAccount() throws IOException {
        switch (accountType){
            case "current", "savings" -> {
                bank.createAccount(customer, accountType);
                statusLabel.setText("New account created");
                loginController.onHelloButtonClick();
            }
            case "cd" -> launchCdWindow();
            default -> statusLabel.setText("You must select an account type first");
        }
    }
    private void launchCdWindow(){

    }

}
