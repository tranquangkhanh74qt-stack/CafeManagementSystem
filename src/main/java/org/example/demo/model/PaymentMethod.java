package org.example.demo.model;

public class PaymentMethod {
    private int paymentMethodId;
    private String methodName;
    private String description;
    private boolean isActive;

    public PaymentMethod() {}

    public PaymentMethod(int paymentMethodId, String methodName, String description, boolean isActive) {
        this.paymentMethodId = paymentMethodId;
        this.methodName = methodName;
        this.description = description;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
