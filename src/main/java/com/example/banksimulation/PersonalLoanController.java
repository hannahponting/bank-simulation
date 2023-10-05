package com.example.banksimulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PersonalLoanController implements Initializable {

    private Bank bank;
    private LoginController loginController;
    public void setLoginController(LoginController loginController){
        this.loginController = loginController;
    }

    private Customer currentCustomer;
    public void setCurrentCustomer(Customer customer){
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
    ObservableList<String> options = FXCollections.observableArrayList("15", "20", "30");






    @FXML
    public void createHouseLoan(ActionEvent event) throws IOException {
        try {

            double amount = Double.parseDouble(loanAmount.getText());
            if (amount > 0) {
                initialLoan = Double.parseDouble(loanAmount.getText());
                loanTerm = Integer.parseInt(loanLengthSelector.getValue());
                boolean confirmCreation = bank.createLoan(currentCustomer, loanTerm, initialLoan, "PersonalLoan");
                loginController.onHelloButtonClick();

                if (confirmCreation) {
                    printCongratulationMessagebutton.setText("Loan application approved");
                } else printCongratulationMessagebutton.setText("Loan application denied");
                printCongratulationMessagebutton.setTextFill(Paint.valueOf("red"));
            }
        } catch (NumberFormatException nfe) {
            printCongratulationMessagebutton.setText("Input valid amount");
        }




    }


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        loanLengthSelector.setValue("15");
        loanLengthSelector.setItems(options);
    }
    double initialLoan;
    private static final DecimalFormat df = new DecimalFormat("0.00");


        @FXML
    void calculateHouseInterest (ActionEvent event){
            try {
                initialLoan = Double.parseDouble(loanAmount.getText());

                HomeLoan homeLoan = new HomeLoan(currentCustomer, loanTerm, initialLoan);


                loanTerm = Integer.parseInt(loanLengthSelector.getValue());




                double loanWithInterest = initialLoan + homeLoan.addInterestToLoan(initialLoan);
                String trimmedNumber2 = df.format(loanWithInterest);
                double loanWithInterest2 = Double.parseDouble(trimmedNumber2);
                totalAmountWithInterest.setText(String.valueOf(loanWithInterest2));


                double repaymentPerMonth = loanWithInterest/12/loanTerm;
                String trimmedNumber = df.format(repaymentPerMonth);
                double repaymentPerMonth2 = Double.parseDouble(trimmedNumber);
                repaymentLabel.setText(String.valueOf(repaymentPerMonth2));
            } catch (NumberFormatException nfe) {
                printCongratulationMessagebutton.setText("Input valid amount");
            }

    }

}
