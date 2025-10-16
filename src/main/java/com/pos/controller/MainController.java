package com.pos.controller;

import java.io.IOException;

import com.pos.App;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}