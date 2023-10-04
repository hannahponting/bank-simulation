package com.example.banksimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TemporaryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderCD = new FXMLLoader(HelloApplication.class.getResource("CdCreateView.fxml"));
        Scene sceneLogin = new Scene(fxmlLoaderCD.load(), 520, 340);
        stage.setTitle("Create CD");
        stage.setScene(sceneLogin);
        stage.show();
        Bank barclays = new Bank();
        CdCreateController cdController = fxmlLoaderCD.getController();
        cdController.setBank(barclays);


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewAccountDetails.fxml"));
//        HelloController controller = new HelloController();
//        fxmlLoader.setController(controller);
//        controller.setBank(barclays);
//        barclays.readCSVBankAndCustomerBook();
//        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }
}
