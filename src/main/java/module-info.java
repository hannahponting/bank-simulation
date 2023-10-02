module com.example.banksimulation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.banksimulation to javafx.fxml;
    exports com.example.banksimulation;
}