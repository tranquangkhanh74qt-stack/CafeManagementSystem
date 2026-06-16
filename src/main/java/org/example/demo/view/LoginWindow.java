package org.example.demo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginWindow {
    
    public void show(Stage primaryStage) {
        primaryStage.setTitle("CAFE MANAGEMENT");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(800);
        
        // Main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5e6d3;");
        
        // Center content
        VBox centerBox = new VBox();
        centerBox.setPadding(new Insets(50));
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(20);
        centerBox.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 40;");
        centerBox.setMaxWidth(400);
        centerBox.setMaxHeight(300);
        
        // Title
        Label titleLabel = new Label("CAFE MANAGEMENT");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        Label subtitleLabel = new Label("Quản lý quán cafe");
        subtitleLabel.setFont(Font.font("System", 14));
        subtitleLabel.setTextFill(Color.web("#a0714f"));
        
        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Tên đăng nhập");
        usernameField.setPrefHeight(40);
        usernameField.setStyle("-fx-font-size: 12; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5;");
        
        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Mật khẩu");
        passwordField.setPrefHeight(40);
        passwordField.setStyle("-fx-font-size: 12; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5;");
        
        // Login button
        Button loginButton = new Button("Đăng nhập");
        loginButton.setPrefWidth(150);
        loginButton.setPrefHeight(45);
        loginButton.setFont(Font.font("System", FontWeight.BOLD, 14));
        loginButton.setStyle("-fx-background-color: #5d4037; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;");
        
        loginButton.setOnMouseEntered(e -> loginButton.setStyle("-fx-background-color: #4e342e; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle("-fx-background-color: #5d4037; -fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"));
        
        loginButton.setOnAction(e -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.show(primaryStage);
        });
        
        centerBox.getChildren().addAll(titleLabel, subtitleLabel, usernameField, passwordField, loginButton);
        
        // Wrap center box in another container to center it
        VBox wrapper = new VBox();
        wrapper.setAlignment(Pos.CENTER);
        wrapper.getChildren().add(centerBox);
        
        root.setCenter(wrapper);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}