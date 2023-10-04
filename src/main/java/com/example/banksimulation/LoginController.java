package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label messageLabel;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink registerHyperlink;

    Stage userInterfaceStage = new Stage();
    Stage registrationStage = new Stage();
    @FXML
    public void onHelloButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAccountDetails.fxml"));
        HelloController controller = new HelloController();
        controller.setLoginController(this);
        fxmlLoader.setController(controller);
        controller.setBank(bank);
        String userName = userTextField.getText();
        Customer currentCustomer  = bank.customerHashMap.get(userName);
        if(currentCustomer != null){
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        userInterfaceStage.setTitle("Hello!");
        userInterfaceStage.setScene(scene);
        userInterfaceStage.show();
        controller.setCurrentCustomer(currentCustomer);
        controller.welcomeLabel.setText("Welcome, " + userName);
        controller.getCustomerAccounts();
        controller.getCustomerLoans();
        }
        else{
            messageLabel.setText("That account does not exist. Check input or register new account.");
        }

//        String password = passwordTextField.getText();
//
//        if (isValidLogin(userName, password)) {
//            welcomeText.setText("Welcome, " + userName + "!");
//        } else {
//            welcomeText.setText("Invalid login. Please try again.");
//        }
    }
    @FXML
    private void launchCustomerRegistration() throws IOException {
        FXMLLoader fxmlLoaderRegistration = new FXMLLoader(HelloApplication.class.getResource("CustomerCreationView.fxml"));
        CustomerCreationController controller = new CustomerCreationController();
        fxmlLoaderRegistration.setController(controller);
        controller.setBank(bank);
        Scene scene = new Scene(fxmlLoaderRegistration.load(), 500, 600);
        registrationStage.setTitle("Registration");
        registrationStage.setScene(scene);
        registrationStage.show();
    }

    private boolean isValidLogin(String userName, String password) {
        return userName.equals("rui") && password.equals("123");
    }

    private  Bank bank;
    public void setBank(Bank bank) {
        this.bank = bank;
    }
}