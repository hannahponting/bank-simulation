package com.example.banksimulation.controllers;

import com.example.banksimulation.Bank;
import com.example.banksimulation.ProductTypes;
import com.example.banksimulation.Utilities;
import com.example.banksimulation.loans.CarLoan;
import com.example.banksimulation.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Paint;


import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CarLoanController implements Initializable {

    private Bank bank;
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    private Customer currentCustomer;

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setBank(Bank bank) {
        this.bank = bank;

    }

    int loanTerm;
    @FXML
    private Label printCongratulationMessagebutton;
    @FXML
    private Label interestRateLabel;

    @FXML
    private Label repaymentLabel;

    @FXML
    private TextField loanAmount;

    @FXML
    private Label totalAmountWithInterest;

    @FXML
    private ComboBox<String> loanLengthSelector;
    ObservableList<String> options = FXCollections.observableArrayList("3", "4", "5");


    @FXML
    public void createLoan(ActionEvent event) throws IOException {
        try {
            double initialLoan = Utilities.parsePositiveDouble(loanAmount.getText());
            loanTerm = Integer.parseInt(loanLengthSelector.getValue());
            boolean confirmCreation = false;
            try {
                confirmCreation = bank.createLoan(currentCustomer, loanTerm, initialLoan, ProductTypes.CarLoan.name());
                if (confirmCreation) {
                    printCongratulationMessagebutton.setText("Loan application approved");
                    printCongratulationMessagebutton.setTextFill(Paint.valueOf("black"));
                    loginController.onHelloButtonClick();
                } else printCongratulationMessagebutton.setText("Loan application denied");
                printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
            } catch (IllegalArgumentException illegalArgumentException) {
                printCongratulationMessagebutton.setText(illegalArgumentException.getMessage());
                printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
            }
        } catch (NumberFormatException nfe) {
            printCongratulationMessagebutton.setText(nfe.getMessage());
            printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loanLengthSelector.setValue("3");
        loanLengthSelector.setItems(options);
    }

    double initialLoan;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    void calculateInterest(ActionEvent event) {
        try {
            initialLoan = Utilities.parsePositiveDouble(loanAmount.getText());

            CarLoan carLoan = new CarLoan(currentCustomer, loanTerm, initialLoan);


            loanTerm = Integer.parseInt(loanLengthSelector.getValue());


            double loanWithInterest = (12 * loanTerm) * carLoan.addInterestToLoan(initialLoan, loanTerm);

            String trimmedNumber2 = df.format(loanWithInterest);
            double loanWithInterest2 = Double.parseDouble(trimmedNumber2);
            totalAmountWithInterest.setText(String.valueOf(loanWithInterest2));


            double repaymentPerMonth = carLoan.addInterestToLoan(initialLoan, loanTerm);
            String trimmedNumber = df.format(repaymentPerMonth);
            double repaymentPerMonth2 = Double.parseDouble(trimmedNumber);
            repaymentLabel.setText(String.valueOf(repaymentPerMonth2));

        } catch (NumberFormatException nfe) {
            printCongratulationMessagebutton.setText(nfe.getMessage());
            printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
        }

    }

}
