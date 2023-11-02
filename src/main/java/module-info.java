module com.example.banksimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;

    opens com.example.banksimulation to javafx.fxml;
    exports com.example.banksimulation;
    exports com.example.banksimulation.controllers;
    opens com.example.banksimulation.controllers to javafx.fxml;
    exports com.example.banksimulation.loans;
    opens com.example.banksimulation.loans to javafx.fxml;
    exports com.example.banksimulation.accounts;
    opens com.example.banksimulation.accounts to javafx.fxml;
}