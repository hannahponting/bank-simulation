package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HelloController {
    private LoginController loginController;
    public void setLoginController(LoginController loginController){
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
    private TextField withdrawalTextField;
    @FXML
    private TextField destinationAccountTextField;
    @FXML
    private TextField transferAmountTextField;
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

            if (accountToBeAdded.accountType =="CD"){

                radioButton = new RadioButton(accountToBeAdded.accountType +"  (Acc No." + accountToBeAdded.getAccountNumber()+
                        " - Interest Rate "+accountToBeAdded.interestRateFromCSV+"% "+ " - Term "+accountToBeAdded.accountTerm+" years"+ " )");}
             else
            {
                 radioButton = new RadioButton(accountToBeAdded.accountType +"  (Acc No." + accountToBeAdded.getAccountNumber()+" )");}

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
            HBox accountHbox = new HBox();
            RadioButton radioButton = new RadioButton(loanToBeAdded.loanType+" (Loan No."+loanToBeAdded.loanNumber+" )");
            accountHbox.getChildren().add(radioButton);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnAction(e -> getLoanDetails(loanToBeAdded.loanNumber));
//            Label accountBalance = new Label("Balance: " + accountToBeAdded.accountBalance);
//            accountHbox.getChildren().add(accountBalance);
            loanSelectionRadioButtonHolder.getChildren().add(accountHbox);

        }
    }
    @FXML
    private void getLoanDetails(int selectedLoan){

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
            double depositAmount = Double.parseDouble(depositTextField.getText());
            depositAccount.deposit(depositAccount, depositAmount);
            depositWithdrawalStatus.setText("Deposit successful, balance updated.");
            getAccountDetails(requestedAccount.getAccountNumber());
            depositTextField.clear();
        } catch (NumberFormatException numberFormatException) {
            depositWithdrawalStatus.setText("Please check you have entered a valid number");
        }

    }

    @FXML
    protected void makeWithdrawal() {
        Account withdrawalAccount = requestedAccount;
        try {
            double withdrawalAmount = Double.parseDouble(withdrawalTextField.getText());
            try {
                withdrawalAccount.withdraw(withdrawalAccount, withdrawalAmount);
                depositWithdrawalStatus.setText("Withdrawal successful, balance updated.");
                getAccountDetails(requestedAccount.getAccountNumber());
                withdrawalTextField.clear();
            } catch (IllegalArgumentException overdrawn) {
                depositWithdrawalStatus.setText(overdrawn.getMessage());
            }
        } catch (NumberFormatException numberFormatException) {
            depositWithdrawalStatus.setText("Please check you have entered a valid number");
        }
    }

    @FXML
    protected void makeTransfer() {
        Account fromAccount = requestedAccount;
        Account toAccount = null;
        try {
            int destinationAccountNumber = Integer.parseInt(destinationAccountTextField.getText());
            toAccount = bank.accountBookHashMap.get(destinationAccountNumber);
            if (toAccount == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException accountNotFound) {
            depositWithdrawalStatus.setText("Recipient account number invalid");
        } catch (NumberFormatException invalidAccountInput) {
            depositWithdrawalStatus.setText("Recipient account number invalid");
        }
        try {
            double transferAmount = Double.parseDouble(transferAmountTextField.getText());
            if (transferAmount <= 0) {
                throw new NumberFormatException("You can't transfer a negative number");
            }
            fromAccount.withdraw(fromAccount, transferAmount);
            toAccount.deposit(toAccount, transferAmount);
            System.out.println(toAccount);
            getAccountDetails(requestedAccount.getAccountNumber());
            depositWithdrawalStatus.setText("Transfer successful, balance updated");
            transferAmountTextField.clear();
            destinationAccountTextField.clear();
        } catch (NumberFormatException invalidDouble) {
            depositWithdrawalStatus.setText("Transfer amount number invalid");
        } catch (IllegalArgumentException overdrawn) {
            depositWithdrawalStatus.setText(overdrawn.getMessage());
        }

    }

    @FXML
    protected void launchNewAccountWindow() throws IOException {
        FXMLLoader fxmlLoaderCreateAccount = new FXMLLoader(HelloApplication.class.getResource("AccountCreationView.fxml"));
        CreateAccountController controller = new CreateAccountController();
        fxmlLoaderCreateAccount.setController(controller);
        controller.setBank(bank);
        controller.setCustomer(currentCustomer);
        Scene scene = new Scene(fxmlLoaderCreateAccount.load(), 500, 600);
        createLoanStage.setTitle("Create Account");
        createLoanStage.setScene(scene);
        createLoanStage.show();
        controller.initialiseToggleGroup();
        controller.setLoginController(loginController);

    }

    @FXML
    protected void launchLoanAppWindow() throws IOException {
        FXMLLoader fxmlLoaderCreateLoan = new FXMLLoader(HelloApplication.class.getResource("LoanCreationView.fxml"));
        CreateLoanController controller = new CreateLoanController();
        fxmlLoaderCreateLoan.setController(controller);
        controller.setBank(bank);
        controller.setCustomer(currentCustomer);
        Scene scene = new Scene(fxmlLoaderCreateLoan.load(), 500, 600);
        createLoanStage.setTitle("Apply for loan");
        createLoanStage.setScene(scene);
        createLoanStage.show();
        controller.initialiseToggleGroup();
        controller.setLoginController(loginController);

    }








}