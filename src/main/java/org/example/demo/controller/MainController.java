package org.example.demo.controller;

import org.example.demo.dao.CategoryDAO;
import org.example.demo.dao.CustomerDAO;
import org.example.demo.dao.OrderDAO;
import org.example.demo.dao.ProductDAO;
import org.example.demo.model.Category;
import org.example.demo.model.Customer;
import org.example.demo.model.Order;
import org.example.demo.model.Product;

import java.util.List;

public class MainController {
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    
    public MainController() {
        this.categoryDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
        this.customerDAO = new CustomerDAO();
        this.orderDAO = new OrderDAO();
    }
    
    // Category methods
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
    
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }
    
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }
    
    public void deleteCategory(int categoryID) {
        categoryDAO.deleteCategory(categoryID);
    }
    
    // Product methods
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    
    public List<Product> getProductsByCategory(int categoryID) {
        return productDAO.getProductsByCategory(categoryID);
    }
    
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }
    
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
    
    public void deleteProduct(int productID) {
        productDAO.deleteProduct(productID);
    }
    
    public List<Product> getTopSellingProducts(int limit) {
        return productDAO.getTopSellingProducts(limit);
    }
    
    // Customer methods
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
    
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }
    
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
    
    public void deleteCustomer(int customerID) {
        customerDAO.deleteCustomer(customerID);
    }
    
    public int getTotalCustomers() {
        return customerDAO.getTotalCustomers();
    }
    
    // Order methods
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
    
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }
    
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }
    
    public void deleteOrder(int orderID) {
        orderDAO.deleteOrder(orderID);
    }
    
    public int getTotalOrders() {
        return orderDAO.getTotalOrders();
    }
    
    public double getTotalRevenue() {
        return orderDAO.getTotalRevenue();
    }
    
    public double getAverageOrderValue() {
        return orderDAO.getAverageOrderValue();
    }
}