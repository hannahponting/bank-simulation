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
import java.util.HashMap;

public class CreateAccountController {
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Bank bank;
    private Customer customer;

    public HashMap<Integer, Account> accountBookHashMap = new HashMap<>();

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
    private void selectCurrentAccount() {
        accountType = ProductTypes.Current.name();
    }

    @FXML
    private void selectSavingsAccount() {
        accountType = ProductTypes.Savings.name();
    }

    @FXML
    private void selectCdAccount() {
        accountType = ProductTypes.CD.name();
    }

    @FXML
    private void createSelectedAccount() throws IOException {

        Account account = null;

        try {
            switch (accountType.toLowerCase()){
                case "savings" -> {
                    bank.checkAccountLimit(customer, ProductTypes.Savings.name(), 1, "You already have a savings account");
                    account = new SavingsAccount(customer);
                    Account.nextAccountNumber++;
                    accountBookHashMap.put(account.getAccountNumber(), account);
                    customer.accountArrayList.add(account);
                    statusLabel.setText("New account created");
                    statusLabel.setTextFill(Paint.valueOf("black"));
                    loginController.onHelloButtonClick();

                }

                case "current" -> {
                    bank.checkAccountLimit(customer, ProductTypes.Current.name(), 1, "You already have a current account");
                    account = new CurrentAccount(customer);
                    Account.nextAccountNumber++;
                    accountBookHashMap.put(account.getAccountNumber(), account);
                    customer.accountArrayList.add(account);
                    statusLabel.setText("New account created");
                    statusLabel.setTextFill(Paint.valueOf("black"));
                    loginController.onHelloButtonClick();

                }
                case "cd" -> launchCdWindow();
                default -> {
                    statusLabel.setText("You must select an account type first");
                    statusLabel.setTextFill(Paint.valueOf("red"));
                }
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
