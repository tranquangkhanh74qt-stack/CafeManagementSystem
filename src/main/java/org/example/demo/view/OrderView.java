package org.example.demo.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.demo.controller.MainController;
import org.example.demo.model.Order;

public class OrderView {
    private MainController controller;
    private TableView<Order> tableView;
    
    public OrderView(MainController controller) {
        this.controller = controller;
    }
    
    public BorderPane getView() {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #f5e6d3;");
        mainPane.setPadding(new Insets(20));
        
        VBox content = new VBox();
        content.setSpacing(15);
        
        // Title
        Label titleLabel = new Label("Quản lý nguyên liệu");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        // Search section
        HBox searchBox = createSearchBox();
        
        // Buttons section
        HBox buttonBox = createButtonBox();
        
        // Table view
        tableView = createTableView();
        
        content.getChildren().addAll(titleLabel, searchBox, buttonBox, tableView);
        mainPane.setCenter(content);
        
        loadOrders();
        
        return mainPane;
    }
    
    private HBox createSearchBox() {
        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-background-color: white; -fx-border-radius: 5;");
        
        Label searchLabel = new Label("Tìm kiếm:");
        searchLabel.setFont(Font.font("System", 12));
        
        TextField orderIdField = new TextField();
        orderIdField.setPromptText("Mã đơn");
        orderIdField.setPrefWidth(150);
        
        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.setPromptText("Trạng thái");
        statusCombo.setPrefWidth(150);
        statusCombo.setItems(FXCollections.observableArrayList("Pending", "Completed", "Cancelled"));
        
        searchBox.getChildren().addAll(searchLabel, orderIdField, statusCombo);
        
        return searchBox;
    }
    
    private HBox createButtonBox() {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        
        Button addButton = new Button("Thêm");
        addButton.setPrefWidth(80);
        addButton.setStyle("-fx-background-color: #5d4037; -fx-text-fill: white;");
        
        Button editButton = new Button("Sửa");
        editButton.setPrefWidth(80);
        editButton.setStyle("-fx-background-color: #c89968; -fx-text-fill: white;");
        
        Button deleteButton = new Button("Xóa");
        deleteButton.setPrefWidth(80);
        deleteButton.setStyle("-fx-background-color: #8b6f47; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteOrder());
        
        Button refreshButton = new Button("Làm mới");
        refreshButton.setPrefWidth(80);
        refreshButton.setStyle("-fx-background-color: #a0714f; -fx-text-fill: white;");
        refreshButton.setOnAction(e -> loadOrders());
        
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);
        
        return buttonBox;
    }
    
    private TableView<Order> createTableView() {
        tableView = new TableView<>();
        tableView.setPrefHeight(400);
        
        TableColumn<Order, Integer> idCol = new TableColumn<>("Mã Đơn");
        idCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        idCol.setPrefWidth(80);
        
        TableColumn<Order, Integer> customerCol = new TableColumn<>("Mã KH");
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerCol.setPrefWidth(100);
        
        TableColumn<Order, String> dateCol = new TableColumn<>("Ngày");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        dateCol.setPrefWidth(150);
        
        TableColumn<Order, Double> amountCol = new TableColumn<>("Tổng tiền");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        amountCol.setPrefWidth(120);
        
        TableColumn<Order, String> statusCol = new TableColumn<>("Trạng thái");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(120);
        
        tableView.getColumns().addAll(idCol, customerCol, dateCol, amountCol, statusCol);
        tableView.setStyle("-fx-background-color: white;");
        
        return tableView;
    }
    
    private void loadOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList(
            controller.getAllOrders()
        );
        tableView.setItems(orders);
    }
    
    private void deleteOrder() {
        Order selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.deleteOrder(selected.getOrderID());
            loadOrders();
        }
    }
}