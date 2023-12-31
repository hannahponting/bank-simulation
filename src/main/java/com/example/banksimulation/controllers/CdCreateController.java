package com.example.banksimulation.controllers;

import com.example.banksimulation.Bank;
import com.example.banksimulation.Utilities;
import com.example.banksimulation.accounts.CDAccount;
import com.example.banksimulation.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CdCreateController {
    @FXML
    private TextField initialInvetsmentInput;
    @FXML
    private Label oneYearMoney;
    @FXML
    private Label twoYearMoney;
    @FXML
    private Label threeYearMoney;
    @FXML
    private Label fourYearMoney;
    @FXML
    private Label fiveYearMoney;
    @FXML
    private Label printCongratulationMessagebutton;

    private int cdLength;

    private Bank bank;
    private CDAccount cdAccount;
    private double interestrateWeSet;
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setBank(Bank bank) {
        this.bank = bank;

    }

    private Customer currentCustomer;

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void printPotentialMoney(ActionEvent event) {

        try {
            double amount = Utilities.parsePositiveDouble(initialInvetsmentInput.getText());
            CDAccount cdAccount1 = new CDAccount(currentCustomer);
            ArrayList<Double> totalInvestment;
            totalInvestment = cdAccount1.calculateInterest(amount);


            Label[] labelArray = {oneYearMoney,twoYearMoney,threeYearMoney,fourYearMoney,fiveYearMoney};
            for (int i = 0; i < 5; i++) {
                labelArray[i].setText("(£" + String.valueOf(totalInvestment.get(i)) + " at " + String.valueOf(cdAccount1.getInterestRate().get(i)) + "%)");
            }

        } catch (NumberFormatException nfe) {
            printCongratulationMessagebutton.setText(nfe.getMessage());
            printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));

        }


    }
    //TODO: Do something in case no value is inputted and check is pressed

    public void addCDAccountToCSV(ActionEvent event) throws IOException {
        try {
            if (cdLength == 0) {
                printCongratulationMessagebutton.setText("You need to choose an account term");
                printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
            } else {
                double amount = Utilities.parsePositiveDouble(initialInvetsmentInput.getText());
                try {
                    bank.createNewCdAccount(currentCustomer, cdLength, interestrateWeSet, amount);
                    printCongratulationMessagebutton.setText("Your account has been created");
                    printCongratulationMessagebutton.setTextFill(Paint.valueOf("black"));
                    loginController.onHelloButtonClick();
                } catch (IllegalArgumentException illegalArgumentException) {
                    printCongratulationMessagebutton.setText(illegalArgumentException.getMessage());
                    printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
                }
            }

//        cdAccount.interestRateFromCSV = cdAccount.getInterestRate().get(cdLength-1);
//        cdAccount.accountTerm = (cdLength-1);

        } catch (NumberFormatException nfe) {
            printCongratulationMessagebutton.setText(nfe.getMessage());
            printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
        }

    }

    public void createOneYearCDAcc(ActionEvent event) {
        cdLength = 1;
        interestrateWeSet = 2.5;
    }

    public void createTwoYearCDAcc(ActionEvent event) {
        cdLength = 2;
        interestrateWeSet = 2.8;
    }

    public void createThreeYearCDAcc(ActionEvent event) {
        cdLength = 3;
        interestrateWeSet = 3.0;
    }

    public void createFourYearCDAcc(ActionEvent event) {
        cdLength = 4;
        interestrateWeSet = 3.1;
    }

    public void createFiveYearCDAcc(ActionEvent event) {
        cdLength = 5;
        interestrateWeSet = 3.3;
    }

}
