package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
                bank.createAccount(customer, accountType,0,0);
                statusLabel.setText("New account created");
                loginController.onHelloButtonClick();
            }
            case "cd" -> launchCdWindow();
            default -> statusLabel.setText("You must select an account type first");
        }
    }
    Stage stage = new Stage();
    private void launchCdWindow() throws IOException {
        FXMLLoader fxmlLoaderCD = new FXMLLoader(CdCreateController.class.getResource("CdCreateView.fxml"));
        CdCreateController controller = new CdCreateController();
        fxmlLoaderCD.setController(controller);
        controller.setBank(bank);
        controller.setCurrentCustomer(customer);
        controller.setLoginController(loginController);
        Scene sceneCd = new Scene(fxmlLoaderCD.load(), 500, 630);
        stage.setTitle("Create CD account");
        stage.setScene(sceneCd);
        stage.show();

    }

}
