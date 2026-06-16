package org.example.demo.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.demo.controller.MainController;
import org.example.demo.model.Customer;

public class CustomerView {
    private MainController controller;
    private TableView<Customer> tableView;
    private TextField searchField;
    
    public CustomerView(MainController controller) {
        this.controller = controller;
    }
    
    public BorderPane getView() {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #f5e6d3;");
        mainPane.setPadding(new Insets(20));
        
        VBox content = new VBox();
        content.setSpacing(15);
        
        // Title
        Label titleLabel = new Label("Quản lý nhân viên");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        // Search and filter section
        HBox searchBox = createSearchBox();
        
        // Buttons section
        HBox buttonBox = createButtonBox();
        
        // Table view
        tableView = createTableView();
        
        content.getChildren().addAll(titleLabel, searchBox, buttonBox, tableView);
        mainPane.setCenter(content);
        
        loadCustomers();
        
        return mainPane;
    }
    
    private HBox createSearchBox() {
        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-background-color: white; -fx-border-radius: 5;");
        
        Label searchLabel = new Label("Tìm kiếm:");
        searchLabel.setFont(Font.font("System", 12));
        
        searchField = new TextField();
        searchField.setPromptText("Mã nhân viên");
        searchField.setPrefWidth(150);
        
        TextField nameField = new TextField();
        nameField.setPromptText("Tên nhân viên");
        nameField.setPrefWidth(150);
        
        TextField phoneField = new TextField();
        phoneField.setPromptText("Số điện thoại");
        phoneField.setPrefWidth(150);
        
        TextField positionField = new TextField();
        positionField.setPromptText("Chức vụ");
        positionField.setPrefWidth(150);
        
        TextField salaryField = new TextField();
        salaryField.setPromptText("Lương");
        salaryField.setPrefWidth(150);
        
        searchBox.getChildren().addAll(searchLabel, searchField, nameField, phoneField, positionField, salaryField);
        
        return searchBox;
    }
    
    private HBox createButtonBox() {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        
        Button addButton = new Button("Thêm");
        addButton.setPrefWidth(80);
        addButton.setStyle("-fx-background-color: #5d4037; -fx-text-fill: white;");
        addButton.setOnAction(e -> openAddCustomerDialog());
        
        Button editButton = new Button("Sửa");
        editButton.setPrefWidth(80);
        editButton.setStyle("-fx-background-color: #c89968; -fx-text-fill: white;");
        
        Button deleteButton = new Button("Xóa");
        deleteButton.setPrefWidth(80);
        deleteButton.setStyle("-fx-background-color: #8b6f47; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteCustomer());
        
        Button refreshButton = new Button("Làm mới");
        refreshButton.setPrefWidth(80);
        refreshButton.setStyle("-fx-background-color: #a0714f; -fx-text-fill: white;");
        refreshButton.setOnAction(e -> loadCustomers());
        
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);
        
        return buttonBox;
    }
    
    private TableView<Customer> createTableView() {
        tableView = new TableView<>();
        tableView.setPrefHeight(400);
        
        TableColumn<Customer, Integer> idCol = new TableColumn<>("Mã NV");
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        idCol.setPrefWidth(80);
        
        TableColumn<Customer, String> nameCol = new TableColumn<>("Tên nhân viên");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        nameCol.setPrefWidth(150);
        
        TableColumn<Customer, String> phoneCol = new TableColumn<>("SĐT");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.setPrefWidth(120);
        
        TableColumn<Customer, String> emailCol = new TableColumn<>("Chức vụ");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(120);
        
        TableColumn<Customer, String> addressCol = new TableColumn<>("Lương");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(150);
        
        tableView.getColumns().addAll(idCol, nameCol, phoneCol, emailCol, addressCol);
        tableView.setStyle("-fx-background-color: white;");
        
        return tableView;
    }
    
    private void loadCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(
            controller.getAllCustomers()
        );
        tableView.setItems(customers);
    }
    
    private void openAddCustomerDialog() {
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Thêm nhân viên");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        TextField nameField = new TextField();
        nameField.setPromptText("Tên nhân viên");
        
        TextField phoneField = new TextField();
        phoneField.setPromptText("Số điện thoại");
        
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        
        TextField addressField = new TextField();
        addressField.setPromptText("Địa chỉ");
        
        grid.add(new Label("Tên nhân viên:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Số điện thoại:"), 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Địa chỉ:"), 0, 3);
        grid.add(addressField, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Customer customer = new Customer();
                customer.setCustomerName(nameField.getText());
                customer.setPhoneNumber(phoneField.getText());
                customer.setEmail(emailField.getText());
                customer.setAddress(addressField.getText());
                return customer;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(customer -> {
            controller.addCustomer(customer);
            loadCustomers();
        });
    }
    
    private void deleteCustomer() {
        Customer selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.deleteCustomer(selected.getCustomerID());
            loadCustomers();
        }
    }
}