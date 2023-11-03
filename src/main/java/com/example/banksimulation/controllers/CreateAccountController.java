package com.example.banksimulation.controllers;

import com.example.banksimulation.Bank;
import com.example.banksimulation.Customer;
import com.example.banksimulation.ProductTypes;
import com.example.banksimulation.accounts.Account;
import com.example.banksimulation.accounts.CurrentAccount;
import com.example.banksimulation.accounts.SavingsAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController {
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Bank bank;

    private Account account;
    private Customer customer;

    public void initialiseToggleGroup() {
        currentAccountRadioButton.setToggleGroup(toggleGroup);
        savingsAccountRadioButton.setToggleGroup(toggleGroup);
        cdAccountRadioButton.setToggleGroup(toggleGroup);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    private String selectedAccountType;
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
    private void selectCurrentAccount() {
        selectedAccountType = ProductTypes.Current.name();
    }

    @FXML
    private void selectSavingsAccount() {
        selectedAccountType = ProductTypes.Savings.name();
    }

    @FXML
    private void selectCdAccount() {
        selectedAccountType = ProductTypes.CD.name();
    }

    @FXML
    private void createSelectedAccount() throws IOException {

        try {

            switch (selectedAccountType.toLowerCase()) {
                case "current":
                    createAccountAndLogin(new CurrentAccount(customer));
                    break;
                case "savings":
                    createAccountAndLogin(new SavingsAccount(customer));
                    break;
                case "cd":
                    launchCdWindow();
                    break;
                default:
                    statusLabel.setText("You must select an account type first");
                    statusLabel.setTextFill(Paint.valueOf("red"));
                    break;
            }

        }
        catch (IllegalArgumentException illegalArgumentException){
            statusLabel.setText(illegalArgumentException.getMessage());
            statusLabel.setTextFill(Paint.valueOf("red"));
        }
        catch (NullPointerException npe) {
            statusLabel.setText("You must select an account type first");
            statusLabel.setTextFill(Paint.valueOf("red"));
        }

    }

    public void createAccountAndLogin(Account account) throws IOException {
        bank.createAccount(account, customer, 0, 0);
        statusLabel.setText("New account created");
        statusLabel.setTextFill(Paint.valueOf("black"));
        loginController.onHelloButtonClick();
    }



    Stage stage = new Stage();

    private void launchCdWindow() throws IOException {
        FXMLLoader fxmlLoaderCD = new FXMLLoader(CdCreateController.class.getResource("/com/example/banksimulation/CdCreateView.fxml"));
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
