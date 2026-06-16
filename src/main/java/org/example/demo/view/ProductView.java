package org.example.demo.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import org.example.demo.model.Product;

public class ProductView {
    private MainController controller;
    private TableView<Product> tableView;
    
    public ProductView(MainController controller) {
        this.controller = controller;
    }
    
    public BorderPane getView() {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #f5e6d3;");
        mainPane.setPadding(new Insets(20));
        
        VBox content = new VBox();
        content.setSpacing(15);
        
        // Title
        Label titleLabel = new Label("Quản lý sản phẩm");
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
        
        loadProducts();
        
        return mainPane;
    }
    
    private HBox createSearchBox() {
        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-background-color: white; -fx-border-radius: 5;");
        
        Label searchLabel = new Label("Tìm kiếm:");
        searchLabel.setFont(Font.font("System", 12));
        
        ComboBox<String> categoryCombo = new ComboBox<>();
        categoryCombo.setPromptText("Loại sản phẩm");
        categoryCombo.setPrefWidth(150);
        categoryCombo.setItems(FXCollections.observableArrayList("Cà Phê", "Trà", "Nước Ngọt", "Bánh"));
        
        TextField priceField = new TextField();
        priceField.setPromptText("Giá sản phẩm");
        priceField.setPrefWidth(150);
        
        searchBox.getChildren().addAll(searchLabel, categoryCombo, priceField);
        
        return searchBox;
    }
    
    private HBox createButtonBox() {
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        
        Button addButton = new Button("Thêm");
        addButton.setPrefWidth(80);
        addButton.setStyle("-fx-background-color: #5d4037; -fx-text-fill: white;");
        addButton.setOnAction(e -> openAddProductDialog());
        
        Button editButton = new Button("Sửa");
        editButton.setPrefWidth(80);
        editButton.setStyle("-fx-background-color: #c89968; -fx-text-fill: white;");
        
        Button deleteButton = new Button("Xóa");
        deleteButton.setPrefWidth(80);
        deleteButton.setStyle("-fx-background-color: #8b6f47; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteProduct());
        
        Button refreshButton = new Button("Làm mới");
        refreshButton.setPrefWidth(80);
        refreshButton.setStyle("-fx-background-color: #a0714f; -fx-text-fill: white;");
        refreshButton.setOnAction(e -> loadProducts());
        
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);
        
        return buttonBox;
    }
    
    private TableView<Product> createTableView() {
        tableView = new TableView<>();
        tableView.setPrefHeight(400);
        
        TableColumn<Product, Integer> idCol = new TableColumn<>("Mã SP");
        idCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        idCol.setPrefWidth(80);
        
        TableColumn<Product, String> nameCol = new TableColumn<>("Tên sản phẩm");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        nameCol.setPrefWidth(150);
        
        TableColumn<Product, Integer> categoryCol = new TableColumn<>("Loại");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        categoryCol.setPrefWidth(100);
        
        TableColumn<Product, Double> priceCol = new TableColumn<>("Giá");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(100);
        
        TableColumn<Product, Integer> quantityCol = new TableColumn<>("Số lượng");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setPrefWidth(100);
        
        tableView.getColumns().addAll(idCol, nameCol, categoryCol, priceCol, quantityCol);
        tableView.setStyle("-fx-background-color: white;");
        
        return tableView;
    }
    
    private void loadProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList(
            controller.getAllProducts()
        );
        tableView.setItems(products);
    }
    
    private void openAddProductDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Thêm sản phẩm");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        
        TextField nameField = new TextField();
        nameField.setPromptText("Tên sản phẩm");
        
        ComboBox<String> categoryCombo = new ComboBox<>();
        categoryCombo.setPromptText("Loại sản phẩm");
        categoryCombo.setItems(FXCollections.observableArrayList("Cà Phê", "Trà", "Nước Ngọt", "Bánh"));
        
        TextField priceField = new TextField();
        priceField.setPromptText("Giá");
        
        TextField quantityField = new TextField();
        quantityField.setPromptText("Số lượng");
        
        grid.add(new Label("Tên sản phẩm:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Loại:"), 0, 1);
        grid.add(categoryCombo, 1, 1);
        grid.add(new Label("Giá:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Số lượng:"), 0, 3);
        grid.add(quantityField, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                Product product = new Product();
                product.setProductName(nameField.getText());
                product.setPrice(Double.parseDouble(priceField.getText()));
                product.setQuantity(Integer.parseInt(quantityField.getText()));
                return product;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(product -> {
            controller.addProduct(product);
            loadProducts();
        });
    }
    
    private void deleteProduct() {
        Product selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.deleteProduct(selected.getProductID());
            loadProducts();
        }
    }
}