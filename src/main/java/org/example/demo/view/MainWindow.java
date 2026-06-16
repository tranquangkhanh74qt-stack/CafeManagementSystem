package org.example.demo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.demo.controller.MainController;

public class MainWindow {
    private MainController controller;
    private TabPane tabPane;
    
    public MainWindow() {
        this.controller = new MainController();
    }
    
    public void show(Stage primaryStage) {
        primaryStage.setTitle("CAFE MANAGEMENT");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(800);
        primaryStage.setResizable(true);
        
        BorderPane root = new BorderPane();
        
        // Header
        VBox headerBox = createHeader();
        root.setTop(headerBox);
        
        // Sidebar
        VBox sidebar = createSidebar(primaryStage);
        root.setLeft(sidebar);
        
        // Content area with tabs
        tabPane = createTabbedContent();
        root.setCenter(tabPane);
        
        // Set colors
        root.setStyle("-fx-background-color: #f5e6d3;");
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox createHeader() {
        VBox headerBox = new VBox();
        headerBox.setPadding(new Insets(15));
        headerBox.setStyle("-fx-background-color: #5d4037; -fx-border-color: #3d2817; -fx-border-width: 0 0 2 0;");
        
        HBox headerContent = new HBox();
        headerContent.setAlignment(Pos.SPACE_BETWEEN);
        headerContent.setPadding(new Insets(10));
        
        Label titleLabel = new Label("Quản lý nhân viên");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.WHITE);
        
        Label appNameLabel = new Label("Cafe Management");
        appNameLabel.setFont(Font.font("System", 14));
        appNameLabel.setTextFill(Color.WHITE);
        
        headerContent.getChildren().addAll(titleLabel, appNameLabel);
        headerBox.getChildren().add(headerContent);
        
        return headerBox;
    }
    
    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(215);
        sidebar.setStyle("-fx-background-color: #a0714f;");
        sidebar.setPadding(new Insets(10));
        sidebar.setSpacing(5);
        
        // Main menu label
        Label mainMenuLabel = new Label("Trang Chủ");
        mainMenuLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        mainMenuLabel.setTextFill(Color.WHITE);
        mainMenuLabel.setPadding(new Insets(10));
        
        // Menu buttons
        Button dashboardBtn = createMenuButton("Trang Chủ");
        Button customerBtn = createMenuButton("Nhân viên");
        Button productBtn = createMenuButton("Sản phẩm");
        Button orderBtn = createMenuButton("Nguyên liệu");
        Button reportBtn = createMenuButton("Doanh thu");
        
        // Set active button style
        dashboardBtn.setStyle("-fx-background-color: #d4a574; -fx-text-fill: white; -fx-font-weight: bold;");
        
        dashboardBtn.setOnAction(e -> {
            tabPane.getSelectionModel().select(0);
            updateMenuButtons(dashboardBtn, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        });
        
        customerBtn.setOnAction(e -> {
            tabPane.getSelectionModel().select(1);
            updateMenuButtons(customerBtn, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        });
        
        productBtn.setOnAction(e -> {
            tabPane.getSelectionModel().select(2);
            updateMenuButtons(productBtn, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        });
        
        orderBtn.setOnAction(e -> {
            tabPane.getSelectionModel().select(3);
            updateMenuButtons(orderBtn, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        });
        
        reportBtn.setOnAction(e -> {
            tabPane.getSelectionModel().select(4);
            updateMenuButtons(reportBtn, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        });
        
        sidebar.getChildren().addAll(mainMenuLabel, dashboardBtn, customerBtn, productBtn, orderBtn, reportBtn);
        
        return sidebar;
    }
    
    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(35);
        button.setFont(Font.font("System", 12));
        button.setStyle("-fx-background-color: #c89968; -fx-text-fill: white; -fx-border-radius: 0;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #b8885c; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> {
            if (!button.getStyle().contains("#d4a574")) {
                button.setStyle("-fx-background-color: #c89968; -fx-text-fill: white;");
            }
        });
        return button;
    }
    
    private void updateMenuButtons(Button active, Button... buttons) {
        for (Button btn : buttons) {
            if (btn == active) {
                btn.setStyle("-fx-background-color: #d4a574; -fx-text-fill: white; -fx-font-weight: bold;");
            } else {
                btn.setStyle("-fx-background-color: #c89968; -fx-text-fill: white;");
            }
        }
    }
    
    private TabPane createTabbedContent() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-font-size: 12;");
        
        Tab dashboardTab = new Tab("Trang Chủ", new DashboardView().getView());
        dashboardTab.setClosable(false);
        
        Tab customerTab = new Tab("Nhân viên", new CustomerView(controller).getView());
        customerTab.setClosable(false);
        
        Tab productTab = new Tab("Sản phẩm", new ProductView(controller).getView());
        productTab.setClosable(false);
        
        Tab orderTab = new Tab("Nguyên liệu", new OrderView(controller).getView());
        orderTab.setClosable(false);
        
        Tab reportTab = new Tab("Doanh thu", new ReportView(controller).getView());
        reportTab.setClosable(false);
        
        tabPane.getTabs().addAll(dashboardTab, customerTab, productTab, orderTab, reportTab);
        
        return tabPane;
    }
}