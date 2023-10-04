package com.example.banksimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 520, 340);
        stage.setTitle("Login page");
        stage.setScene(sceneLogin);
        stage.show();
        Bank barclays = new Bank();
        barclays.readCSVBankAndCustomerBook();
        barclays.readLoanCSV();

        LoginController loginController = fxmlLoaderLogin.getController();
        loginController.setBank(barclays);

    }

    public static void main(String[] args) {
        launch();
    }
}