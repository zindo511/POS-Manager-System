package com.pos.controller;

import java.io.IOException;

import com.pos.App;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
