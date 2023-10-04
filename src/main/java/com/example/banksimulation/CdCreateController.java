package com.example.banksimulation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CdCreateController {
    @FXML
    private TextField initialInvetsmentInput;


    private Bank bank;

    public void setBank(Bank bank) {
        this.bank = bank;

    }
}
