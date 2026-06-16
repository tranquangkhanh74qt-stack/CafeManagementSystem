package org.example.demo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.demo.controller.MainController;
import org.example.demo.dao.OrderDAO;

public class ReportView {
    private MainController controller;
    private OrderDAO orderDAO;
    
    public ReportView(MainController controller) {
        this.controller = controller;
        this.orderDAO = new OrderDAO();
    }
    
    public BorderPane getView() {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #f5e6d3;");
        mainPane.setPadding(new Insets(20));
        
        VBox content = new VBox();
        content.setSpacing(20);
        
        // Title
        Label titleLabel = new Label("Báo cáo doanh thu");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#3d2817"));
        
        // Statistics boxes
        HBox statsBox = createStatisticsBox();
        
        // Detailed report
        VBox detailsBox = createDetailsBox();
        
        content.getChildren().addAll(titleLabel, statsBox, detailsBox);
        mainPane.setCenter(content);
        
        return mainPane;
    }
    
    private HBox createStatisticsBox() {
        HBox statsBox = new HBox();
        statsBox.setSpacing(15);
        statsBox.setPrefHeight(200);
        
        VBox totalRevenueBox = createStatBox("Tổng doanh thu", 
            formatCurrency(orderDAO.getTotalRevenue()), "#e8dcc5", 60);
        
        VBox totalOrdersBox = createStatBox("Tổng số hóa đơn", 
            String.valueOf(orderDAO.getTotalOrders()), "#e8dcc5", 60);
        
        VBox avgOrderBox = createStatBox("Giá trị tb/đơn", 
            formatCurrency(orderDAO.getAverageOrderValue()), "#e8dcc5", 60);
        
        statsBox.getChildren().addAll(totalRevenueBox, totalOrdersBox, avgOrderBox);
        
        return statsBox;
    }
    
    private VBox createStatBox(String title, String value, String bgColor, int fontSize) {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + bgColor + "; -fx-border-radius: 10; -fx-padding: 20;");
        box.setPrefWidth(350);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", 14));
        titleLabel.setTextFill(Color.web("#5d4037"));
        
        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("System", FontWeight.BOLD, fontSize));
        valueLabel.setTextFill(Color.web("#3d2817"));
        valueLabel.setWrapText(true);
        
        box.getChildren().addAll(titleLabel, valueLabel);
        
        return box;
    }
    
    private VBox createDetailsBox() {
        VBox detailsBox = new VBox();
        detailsBox.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 20;");
        detailsBox.setSpacing(15);
        
        Label detailsTitle = new Label("Chi tiết doanh thu");
        detailsTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        detailsTitle.setTextFill(Color.web("#3d2817"));
        
        // Create detail rows
        HBox row1 = createDetailRow("Tổng doanh thu:", formatCurrency(orderDAO.getTotalRevenue()));
        HBox row2 = createDetailRow("Tổng số đơn:", String.valueOf(orderDAO.getTotalOrders()));
        HBox row3 = createDetailRow("Giá trị trung bình/đơn:", formatCurrency(orderDAO.getAverageOrderValue()));
        HBox row4 = createDetailRow("Tổng lợi nhuận dự kiến:", formatCurrency(orderDAO.getTotalRevenue() * 0.3));
        
        detailsBox.getChildren().addAll(detailsTitle, row1, row2, row3, row4);
        
        return detailsBox;
    }
    
    private HBox createDetailRow(String label, String value) {
        HBox row = new HBox();
        row.setSpacing(10);
        row.setPadding(new Insets(10));
        row.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");
        
        Label labelText = new Label(label);
        labelText.setFont(Font.font("System", 12));
        labelText.setTextFill(Color.web("#5d4037"));
        labelText.setPrefWidth(200);
        
        Label valueText = new Label(value);
        valueText.setFont(Font.font("System", FontWeight.BOLD, 14));
        valueText.setTextFill(Color.web("#3d2817"));
        
        row.getChildren().addAll(labelText, valueText);
        
        return row;
    }
    
    private String formatCurrency(double value) {
        return String.format("%,.0f ₫", value);
    }
}