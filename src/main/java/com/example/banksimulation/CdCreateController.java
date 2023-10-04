package com.example.banksimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
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
    public void setLoginController(LoginController loginController){
        this.loginController = loginController;
    }

    public void setBank(Bank bank) {
        this.bank = bank;

    }
    private Customer currentCustomer; //TODO: This is just a placeholder
    public void setCurrentCustomer(Customer customer){
        this.currentCustomer = customer;
    }

    public void printPotentialMoney(ActionEvent event) {
        CDAccount cdAccount1 = new CDAccount(currentCustomer); //TODO: Change to actual client from login, this is just placeholder
        ArrayList<Double> totalInvestment;
        totalInvestment = cdAccount1.calculateInterest(Double.parseDouble(initialInvetsmentInput.getText()));

        oneYearMoney.setText("(£"+String.valueOf(totalInvestment.get(0))+" at "+String.valueOf(cdAccount1.getInterestRate().get(0))+ "%)");
        twoYearMoney.setText("(£"+String.valueOf(totalInvestment.get(1))+" at "+String.valueOf(cdAccount1.getInterestRate().get(1))+ "%)");
        threeYearMoney.setText("(£"+String.valueOf(totalInvestment.get(2))+" at "+String.valueOf(cdAccount1.getInterestRate().get(2))+ "%)");
        fourYearMoney.setText("(£"+String.valueOf(totalInvestment.get(3))+" at "+String.valueOf(cdAccount1.getInterestRate().get(3))+ "%)");
        fiveYearMoney.setText("(£"+String.valueOf(totalInvestment.get(4))+" at "+String.valueOf(cdAccount1.getInterestRate().get(4))+ "%)");

    }
    //TODO: Do something in case no value is inputted and check is pressed

    public void addCDAccountToCSV(ActionEvent event) throws IOException {
        double accountBalance = Double.parseDouble(initialInvetsmentInput.getText());
        bank.createNewCdAccount(currentCustomer,cdLength,interestrateWeSet,accountBalance);
        printCongratulationMessagebutton.setText("done");
        loginController.onHelloButtonClick();



//        cdAccount.interestRateFromCSV = cdAccount.getInterestRate().get(cdLength-1);
//        cdAccount.accountTerm = (cdLength-1);


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
