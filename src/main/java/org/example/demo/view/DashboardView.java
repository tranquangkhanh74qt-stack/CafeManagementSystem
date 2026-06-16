package org.example.demo.view;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import org.example.demo.dao.CustomerDAO;
import org.example.demo.dao.OrderDAO;
import org.example.demo.dao.ProductDAO;

public class DashboardView {
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    
    public DashboardView() {
        this.customerDAO = new CustomerDAO();
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
    }
    
    public BorderPane getView() {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #f5e6d3;");
        mainPane.setPadding(new Insets(20));
        
        VBox content = new VBox();
        content.setSpacing(20);
        
        // Title
        Label titleLabel = new Label("Trang Chủ");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        // Statistics boxes
        HBox statsBox = createStatisticsBox();
        
        // Top products and revenue box
        HBox contentBox = createContentBox();
        
        content.getChildren().addAll(titleLabel, statsBox, contentBox);
        mainPane.setCenter(content);
        
        return mainPane;
    }
    
    private HBox createStatisticsBox() {
        HBox statsBox = new HBox();
        statsBox.setSpacing(15);
        statsBox.setPrefHeight(150);
        
        VBox totalCustomersBox = createStatBox("Tổng nhân viên", String.valueOf(customerDAO.getTotalCustomers()), "#e8dcc5");
        VBox totalProductsBox = createStatBox("Tổng sản phẩm", String.valueOf(productDAO.getAllProducts().size()), "#e8dcc5");
        VBox totalOrdersBox = createStatBox("Tổng nguyên liệu", String.valueOf(orderDAO.getTotalOrders()), "#e8dcc5");
        
        statsBox.getChildren().addAll(totalCustomersBox, totalProductsBox, totalOrdersBox);
        
        return statsBox;
    }
    
    private VBox createStatBox(String title, String value, String bgColor) {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + bgColor + "; -fx-border-radius: 10; -fx-padding: 20;");
        box.setPrefWidth(300);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", 14));
        titleLabel.setTextFill(Color.web("#5d4037"));
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("System", FontWeight.BOLD, 32));
        valueLabel.setTextFill(Color.web("#3d2817"));
        
        box.getChildren().addAll(titleLabel, valueLabel);
        
        return box;
    }
    
    private HBox createContentBox() {
        HBox mainBox = new HBox();
        mainBox.setSpacing(20);
        
        VBox topProductsBox = createTopProductsBox();
        VBox revenueBox = createRevenueBox();
        
        mainBox.getChildren().addAll(topProductsBox, revenueBox);
        
        return mainBox;
    }
    
    private VBox createTopProductsBox() {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 20;");
        box.setPrefWidth(600);
        
        Label titleLabel = new Label("Top sản phẩm bán chạy");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        // Create a simple table view for top products
        Label productContent = new Label("SP02 - Cà Phê Sữa - 9\nSP05 - Trà Sữa Trân Châu - 6\nSP08 - Latte - 6\nSP04 - Trà Đào - 5\nSP09 - Espresso - 5");
        productContent.setFont(Font.font("System", 12));
        productContent.setTextFill(Color.web("#5d4037"));
        productContent.setWrapText(true);
        
        box.getChildren().addAll(titleLabel, productContent);
        
        return box;
    }
    
    private VBox createRevenueBox() {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 20;");
        box.setPrefWidth(300);
        box.setAlignment(Pos.TOP_LEFT);
        
        Label titleLabel = new Label("Tóm tắt doanh thu");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        Label revenueTitle = new Label("Tổng doanh thu");
        revenueTitle.setFont(Font.font("System", 12));
        revenueTitle.setTextFill(Color.web("#5d4037"));
        
        Label revenueValue = new Label(formatCurrency(orderDAO.getTotalRevenue()));
        revenueValue.setFont(Font.font("System", FontWeight.BOLD, 24));
        revenueValue.setTextFill(Color.web("#3d2817"));
        
        Label orderCountLabel = new Label("Tổng số hóa đơn: " + orderDAO.getTotalOrders());
        orderCountLabel.setFont(Font.font("System", 12));
        orderCountLabel.setTextFill(Color.web("#5d4037"));
        
        Label avgOrderLabel = new Label("Giá trị tb/đơn: " + formatCurrency(orderDAO.getAverageOrderValue()));
        avgOrderLabel.setFont(Font.font("System", 12));
        avgOrderLabel.setTextFill(Color.web("#5d4037"));
        
        box.getChildren().addAll(titleLabel, revenueTitle, revenueValue, orderCountLabel, avgOrderLabel);
        
        return box;
    }
    
    private String formatCurrency(double value) {
        return String.format("%,.0f ₫", value);
    }
}