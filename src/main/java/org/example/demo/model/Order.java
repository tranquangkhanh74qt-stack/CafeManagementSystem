package org.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private int employeeId;
    private int tableNumber;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status; // Pending, Completed, Cancelled
    private int paymentMethodId;
    private String notes;
    private List<OrderDetail> orderDetails;

    public Order() {}

    public Order(int orderId, int customerId, int employeeId, int tableNumber,
                LocalDateTime orderDate, double totalAmount, String status,
                int paymentMethodId, String notes) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.tableNumber = tableNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethodId = paymentMethodId;
        this.notes = notes;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
