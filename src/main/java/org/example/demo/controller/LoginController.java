package org.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Vui lòng nhập username và password!");
            return;
        }

        // TODO: Add authentication logic here
        System.out.println("Login with: " + username);
        errorLabel.setText("");
    }

    @FXML
    private void handleCancel() {
        System.exit(0);
    }
}
