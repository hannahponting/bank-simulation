package com.example.banksimulation.controllers;

import com.example.banksimulation.*;
import com.example.banksimulation.accounts.Account;
import com.example.banksimulation.accounts.CDAccount;
import com.example.banksimulation.accounts.SavingsAccount;
import com.example.banksimulation.loans.Loan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HelloController {
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @FXML
    public Label welcomeLabel;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField accountNumberInput;
    @FXML
    private TextField customerNameInput;
    @FXML
    private Label allAccountDetails;
    @FXML
    private TextField depositTextField;

    @FXML
    private TextField destinationAccountTextField;
    @FXML
    private TextField transferAmountTextField;

    @FXML
    private TextField withdrawalTextField;
    @FXML
    private TextField payLoanTextField;
    @FXML
    private Label depositWithdrawalStatus;

    @FXML
    private VBox accountSelectionRadioButtonHolder;
    @FXML
    private Hyperlink createNewAccountHyperLink;

    @FXML
    private Hyperlink createLoanHyperLink;

    @FXML
    private Label loanAmountLabel;
    @FXML
    private TextField payBillTextField1;
    @FXML
    private TextField referenceTextField1;

    @FXML
    private Button payBillConfirmButton1;
    @FXML
    private ComboBox loanComboBox;
    @FXML
    private Button payLoanConfirmButton;
    private Customer currentCustomer;

    Stage createLoanStage = new Stage();

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }


//    @FXML
//    private Button confirmAccountSelection;

    Bank bank;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    Account requestedAccount;

    Loan requestedLoan;
    @FXML
    VBox loanSelectionRadioButtonHolder;

    @FXML
    protected void getAccountDetails(int selectedAccount) {
        String output = "Account Balance: £";
//        int requestedAccountNumber = Integer.parseInt(accountNumberInput.getText());
        requestedAccount = bank.accountBookHashMap.get(selectedAccount);
        NumberFormat format = new DecimalFormat("#0.00");
        output += format.format(requestedAccount.accountBalance);
        allAccountDetails.setText(output);
    }

    @FXML
    protected void getCustomerAccounts() {
//        String customerName = customerNameInput.getText();
//        currentCustomer = bank.customerHashMap.get(customerName);
        ToggleGroup toggleGroup = new ToggleGroup();
        for (Account accountToBeAdded : currentCustomer.accountArrayList
        ) {
            HBox accountHbox = new HBox();
            RadioButton radioButton;

            if (accountToBeAdded.getClass().equals(CDAccount.class)) {

                radioButton = new RadioButton(accountToBeAdded.accountType + "  (Acc No." + accountToBeAdded.getAccountNumber() +
                        " - Interest Rate " + accountToBeAdded.interestRateFromCSV + "% " + " - Term " + accountToBeAdded.accountTerm + " years" + " )");
            } else {
                radioButton = new RadioButton(accountToBeAdded.accountType + "  (Acc No." + accountToBeAdded.getAccountNumber() + " )");
            }

            accountHbox.getChildren().add(radioButton);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnAction(e -> getAccountDetails(accountToBeAdded.getAccountNumber()));
//            Label accountBalance = new Label("Balance: " + accountToBeAdded.accountBalance);
//            accountHbox.getChildren().add(accountBalance);
            accountSelectionRadioButtonHolder.getChildren().add(accountHbox);

        }
    }


    @FXML
    protected void getCustomerLoans() {
        ToggleGroup toggleGroup = new ToggleGroup();
        for (Loan loanToBeAdded : currentCustomer.loanArrayList
        ) {
            RadioButton radioButton;
            HBox accountHbox = new HBox();
            if (loanToBeAdded.loanType ==ProductTypes.PersonalLoan.name()){
                 radioButton = new RadioButton(loanToBeAdded.loanType + " (Loan No." + loanToBeAdded.loanNumber + " )");
            }
            else {
                radioButton = new RadioButton(loanToBeAdded.loanType + " (Loan No." + loanToBeAdded.loanNumber + " - Term " + loanToBeAdded.loanDuration + " years" + " )");
            }
            accountHbox.getChildren().add(radioButton);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnAction(e -> getLoanDetails(loanToBeAdded.loanNumber));
            loanComboBox.getItems().add(loanToBeAdded.loanNumber);
//            Label accountBalance = new Label("Balance: " + accountToBeAdded.accountBalance);
//            accountHbox.getChildren().add(accountBalance);
            loanSelectionRadioButtonHolder.getChildren().add(accountHbox);

        }
    }

    @FXML
    private void getLoanDetails(int selectedLoan) {

        String output = "Loan Balance: £";
//        int requestedAccountNumber = Integer.parseInt(accountNumberInput.getText());
        requestedLoan = bank.loanHashMap.get(selectedLoan);
        NumberFormat format = new DecimalFormat("#0.00");
        output += format.format(requestedLoan.loanAmount);
        loanAmountLabel.setText(output);

    }


    @FXML
    protected void makeDeposit() {
        Account depositAccount = requestedAccount;

        try {
            double depositAmount = Utilities.parsePositiveDouble(depositTextField.getText());
            depositAccount.deposit(depositAccount, depositAmount);
            depositWithdrawalStatus.setText("Deposit successful, balance updated.");
            depositWithdrawalStatus.setTextFill(Paint.valueOf("black"));
            getAccountDetails(requestedAccount.getAccountNumber());
            depositTextField.clear();
        } catch (IllegalArgumentException cdDepsosit){
            depositWithdrawalStatus.setText(cdDepsosit.getMessage());
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
        }
        catch ( NullPointerException nullPointerException) {
            depositWithdrawalStatus.setText("Please select an account to deposit first");
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
        }

    }

    @FXML
    protected void makeWithdrawal() {
        Account withdrawalAccount = requestedAccount;
        try {
            double withdrawalAmount = Utilities.parsePositiveDouble(withdrawalTextField.getText());
            try {
                withdrawalAccount.withdraw(withdrawalAccount, withdrawalAmount);
                depositWithdrawalStatus.setText("Withdrawal successful, balance updated.");
                depositWithdrawalStatus.setTextFill(Paint.valueOf("black"));
                getAccountDetails(requestedAccount.getAccountNumber());
                withdrawalTextField.clear();
            } catch (IllegalArgumentException overdrawn) {
                depositWithdrawalStatus.setText(overdrawn.getMessage());
                depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
            }
        } catch (NumberFormatException numberFormatException) {
            depositWithdrawalStatus.setText(numberFormatException.getMessage());
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));

        }
        catch ( NullPointerException nullPointerException) {
            depositWithdrawalStatus.setText("Please select an account to withdraw first");
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
        }
    }


    @FXML
    protected void payBill() {
        Account withdrawalAccount = requestedAccount;
        double withdrawalAmount = 0.0;
        try {
            withdrawalAmount = Utilities.parsePositiveDouble(payBillTextField1.getText());
            try {
                withdrawalAccount.withdraw(withdrawalAccount, withdrawalAmount);
                depositWithdrawalStatus.setText("Bill payment successful, balance updated.");
                depositWithdrawalStatus.setTextFill(Paint.valueOf("black"));
                getAccountDetails(requestedAccount.getAccountNumber());
                payBillTextField1.clear();
                referenceTextField1.clear();

            } catch (IllegalArgumentException overdrawn) {
                Boolean hasSavingAccount = false;

                for (Account account : currentCustomer.accountArrayList
                ) {
                    System.out.println(account);
                    if (account.getClass().equals(SavingsAccount.class)) {
                        hasSavingAccount = true;
                        System.out.println(account);
                        if (account.accountBalance >= withdrawalAmount) {
                            System.out.println("saving account balance is big enough");
                            depositWithdrawalStatus.setText("insufficient funds, but you have enough money in saving account ");
                            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
                            break;
                            // TODO: 05/10/2023  pop up a window to move into saving, pay from saving account
                        } else {
                            depositWithdrawalStatus.setText(overdrawn.getMessage());
                            System.out.println("Saving account balance is too low");
                            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
                        }
                    }
                }
                if (!hasSavingAccount)
                    depositWithdrawalStatus.setText(overdrawn.getMessage());
                depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));


            }
        } catch (NumberFormatException numberFormatException) {
            depositWithdrawalStatus.setText(numberFormatException.getMessage());
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));


        }catch ( NullPointerException nullPointerException) {
            depositWithdrawalStatus.setText("Please select an account to pay the bill first");
        }
    }

    @FXML
    private void payLoan(){
        Account fromAccount = requestedAccount;
        int loanNumber = (int) loanComboBox.getValue();
        Loan toLoan = bank.loanHashMap.get(loanNumber);
        try {
            double loanPaymentAmount = Utilities.parsePositiveDouble(payLoanTextField.getText());
            fromAccount.withdraw(fromAccount, loanPaymentAmount);
            toLoan.makeRepayment(loanPaymentAmount);
            getAccountDetails(requestedAccount.getAccountNumber());
            getLoanDetails(loanNumber);
            depositWithdrawalStatus.setText("Repayment successful, balances updated");
            depositWithdrawalStatus.setTextFill(Paint.valueOf("black"));
            payLoanTextField.clear();
        } catch (IllegalArgumentException illegalArgumentException) {
            depositWithdrawalStatus.setText(illegalArgumentException.getMessage());
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
        } catch (NullPointerException nullPointerException) {
            depositWithdrawalStatus.setText("Please select an account to pay the loan first");
            depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));

        }


    }





        @FXML
        protected void makeTransfer () {
            Account fromAccount = requestedAccount;
            Account toAccount = null;
            try {
                int destinationAccountNumber = Integer.parseInt(destinationAccountTextField.getText());
                toAccount = bank.accountBookHashMap.get(destinationAccountNumber);
                if (toAccount == null) {
                    throw new NullPointerException();
                }
            } catch (NullPointerException | NumberFormatException accountNotFound) {
                depositWithdrawalStatus.setText("Recipient account number invalid");
                depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
            }
            try {
                double transferAmount = Utilities.parsePositiveDouble(transferAmountTextField.getText());
                fromAccount.withdraw(fromAccount, transferAmount);
                toAccount.deposit(toAccount, transferAmount);
                System.out.println(toAccount);
                getAccountDetails(requestedAccount.getAccountNumber());
                depositWithdrawalStatus.setText("Transfer successful, balance updated");
                depositWithdrawalStatus.setTextFill(Paint.valueOf("black"));
                transferAmountTextField.clear();
                destinationAccountTextField.clear();
            } catch (IllegalArgumentException overdrawn) {
                depositWithdrawalStatus.setText(overdrawn.getMessage());
                depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
            } catch (NullPointerException nullPointerException) {
                depositWithdrawalStatus.setText("Please select an account to transfer the money first");
                depositWithdrawalStatus.setTextFill(Paint.valueOf("red"));
            }

        }

        @FXML
        protected void launchNewAccountWindow () throws IOException {
            FXMLLoader fxmlLoaderCreateAccount = new FXMLLoader(HelloApplication.class.getResource("AccountCreationView.fxml"));
            CreateAccountController controller = new CreateAccountController();
            fxmlLoaderCreateAccount.setController(controller);
            controller.setBank(bank);
            controller.setCustomer(currentCustomer);
            Scene scene = new Scene(fxmlLoaderCreateAccount.load(), 520, 340);
            createLoanStage.setTitle("Create Account");
            createLoanStage.setScene(scene);
            createLoanStage.show();
            controller.initialiseToggleGroup();
            controller.setLoginController(loginController);

        }

        @FXML
        protected void launchLoanAppWindow () throws IOException {
            FXMLLoader fxmlLoaderCreateLoan = new FXMLLoader(HelloApplication.class.getResource("LoanCreationView.fxml"));
            CreateLoanController controller = new CreateLoanController();
            fxmlLoaderCreateLoan.setController(controller);
            controller.setBank(bank);
            controller.setCustomer(currentCustomer);
            Scene scene = new Scene(fxmlLoaderCreateLoan.load(), 520, 340);
            createLoanStage.setTitle("Apply for loan");
            createLoanStage.setScene(scene);
            createLoanStage.show();
            controller.initialiseToggleGroup();
            controller.setLoginController(loginController);

        }


    }