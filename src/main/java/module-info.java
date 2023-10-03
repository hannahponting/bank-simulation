module com.example.banksimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;


    opens com.example.banksimulation to javafx.fxml;
    exports com.example.banksimulation;
}