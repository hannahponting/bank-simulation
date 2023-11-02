package com.example.banksimulation.controllers;

import com.example.banksimulation.Bank;
import com.example.banksimulation.Customer;
import com.example.banksimulation.TemporaryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateLoanController {
    private LoginController loginController;
    public void setLoginController(LoginController loginController){
        this.loginController = loginController;
    }
    private Bank bank;
    private Customer customer;
    public void initialiseToggleGroup(){
        personalLoanRadioButton.setToggleGroup(toggleGroup);
        homeLoanRadioButton.setToggleGroup(toggleGroup);
        carLoanRadioButton.setToggleGroup(toggleGroup);
    }
    public void setCustomer(Customer customer) { this.customer = customer;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    private String loanType;
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private RadioButton personalLoanRadioButton;
    @FXML
    private RadioButton homeLoanRadioButton;
    @FXML
    private RadioButton carLoanRadioButton;
    @FXML
    private Button createLoanButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void selectPersonalLoan(){
        loanType = "PersonalLoan";
    }
    @FXML
    private void selectHomeLoan(){
        loanType = "HomeLoan";
    }
    @FXML
    private void selectCarLoan(){
        loanType = "CarLoan";
    }
    @FXML
    private void createSelectedLoan() throws IOException {
        try {
            switch (loanType){
                case "PersonalLoan"-> {launchPersonalLoanWindow();
                    statusLabel.setText(""); }

                case "HomeLoan" -> {launchHomeLoanWindow();
                    statusLabel.setText("");
                }

                case "CarLoan" -> {
                    launchCarLoanWindow();
                    statusLabel.setText("");

                }
                default -> statusLabel.setText("You must select a loan type first");
            }
        } catch (NullPointerException nullPointerException) {
            statusLabel.setText("You must select a loan type first");
            statusLabel.setTextFill(Paint.valueOf("red"));

        }

    }
Stage stage = new Stage();
    private void launchCarLoanWindow() throws IOException {
        FXMLLoader fxmlLoaderCarLoan = new FXMLLoader(TemporaryApplication.class.getResource("CarLoanView.fxml"));
        CarLoanController controller = new CarLoanController();
        fxmlLoaderCarLoan.setController(controller);
        Scene sceneCarLoan = new Scene(fxmlLoaderCarLoan.load(), 500, 600);
        controller.setBank(bank);
        controller.setCurrentCustomer(customer);
        controller.setLoginController(loginController);
        stage.setTitle("Create Car Loan");
        stage.setScene(sceneCarLoan);
        stage.show();

    }

    private void launchHomeLoanWindow() throws IOException {
        FXMLLoader fxmlLoaderHouseLoan = new FXMLLoader(TemporaryApplication.class.getResource("HouseLoanView.fxml"));
        HouseLoanController controller = new HouseLoanController();
        fxmlLoaderHouseLoan.setController(controller);
        Scene sceneHouseLoan = new Scene(fxmlLoaderHouseLoan.load(), 500, 600);
        controller.setBank(bank);
        controller.setCurrentCustomer(customer);
        controller.setLoginController(loginController);
        stage.setTitle("Create House Loan");
        stage.setScene(sceneHouseLoan);
        stage.show();
    }

    private void launchPersonalLoanWindow() throws IOException {
        FXMLLoader fxmlLoaderPersonalLoan = new FXMLLoader(TemporaryApplication.class.getResource("PersonalLoanView.fxml"));
        PersonalLoanController controller = new PersonalLoanController();
        fxmlLoaderPersonalLoan.setController(controller);
        Scene scenePersonalLoan = new Scene(fxmlLoaderPersonalLoan.load(), 500, 600);
        controller.setBank(bank);
        controller.setCurrentCustomer(customer);
        controller.setLoginController(loginController);
        stage.setTitle("Create Personal Loan");
        stage.setScene(scenePersonalLoan);
        stage.show();
    }


}
