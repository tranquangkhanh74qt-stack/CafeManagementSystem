package org.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.demo.view.LoginWindow;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}