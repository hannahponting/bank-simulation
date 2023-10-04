package com.example.banksimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CarLoanController {

    private Bank bank;
    public void setBank(Bank bank) {
        this.bank = bank;

    }

    @FXML
    private Label interestRateLabel;

    @FXML
    private Label repaymentLabel;

    @FXML
    private TextField loanAmount;

    @FXML
    private SplitMenuButton loanLengthSelector;
    public void createLoan(ActionEvent event) {

    }


}
