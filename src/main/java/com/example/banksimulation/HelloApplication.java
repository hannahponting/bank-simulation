package com.example.banksimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Bank barclays = new Bank();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAccountDetails.fxml"));
        HelloController controller = new HelloController();
        fxmlLoader.setController(controller);
        controller.setBank(barclays);
        Customer firstCustomer = barclays.createCustomer("Joe Bloggs");
        barclays.createAccount(firstCustomer,"current");
        Customer secondCustomer = barclays.createCustomer("Jane Doe");
        barclays.createAccount(secondCustomer, "current");
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}