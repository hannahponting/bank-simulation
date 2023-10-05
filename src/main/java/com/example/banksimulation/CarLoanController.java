package com.example.banksimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.ComboBox;


import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CarLoanController implements Initializable {

    private Bank bank;


    public void setBank(Bank bank) {
        this.bank = bank;

    }
    int loanTerm;

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





//TODO: Add exceptions to everything in case of wrong string input in TextField
    public void createLoan(ActionEvent event) {

    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        loanLengthSelector.setValue("3");
        loanLengthSelector.setItems(options);
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");
        @FXML
    void calculateInterest (ActionEvent event){
        Customer customer = new Customer ("Hannah");
        double initialLoan = Double.parseDouble(loanAmount.getText());

        CarLoan carLoan = new CarLoan(customer, loanTerm, initialLoan);


        loanTerm = Integer.parseInt(loanLengthSelector.getValue());




        double loanWithInterest = initialLoan + carLoan.addInterestToLoan(initialLoan);
        String trimmedNumber2 = df.format(loanWithInterest);
        double loanWithInterest2 = Double.parseDouble(trimmedNumber2);
        totalAmountWithInterest.setText(String.valueOf(loanWithInterest2));


        double repaymentPerMonth = loanWithInterest/12/loanTerm;
        String trimmedNumber = df.format(repaymentPerMonth);
        double repaymentPerMonth2 = Double.parseDouble(trimmedNumber);
        repaymentLabel.setText(String.valueOf(repaymentPerMonth2));
    }

}
