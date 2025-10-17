package com.pos.model;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Order {

    public final IntegerProperty id;
    public final IntegerProperty employeeId;
    public final DoubleProperty totalAmount;
    public final StringProperty paymentMethod;
    public final DoubleProperty customerPaid;
    public final DoubleProperty changeAmount;
    public final ObjectProperty<LocalDateTime> createdAt;

    //Constructor no-argument
    public Order() {
        this.id = new SimpleIntegerProperty();
        this.employeeId = new SimpleIntegerProperty();
        this.totalAmount = new SimpleDoubleProperty();
        this.paymentMethod = new SimpleStringProperty();
        this.customerPaid = new SimpleDoubleProperty();
        this.changeAmount = new SimpleDoubleProperty();
        this.createdAt = new SimpleObjectProperty<>();
    }

    //Constructor full-argument
    public Order(int id, String employeeId, double totalAmount, String paymentMethod, double customerPaid,
                 double changeAmount, LocalDateTime createdAt) {
        this();

    }

    //ID
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    //EmployeeID
    public int getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    public IntegerProperty employeeIdProperty() {
        return employeeId;
    }

    //TotalAmount
    public double getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    //PaymentMethod
    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
    }

    public StringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    //CustomerPaid
    public double getCustomerPaid() {
        return customerPaid.get();
    }

    public void setCustomerPaid(double customerPaid) {
        this.customerPaid.set(customerPaid);
    }

    public DoubleProperty customerPaidProperty() {
        return customerPaid;
    }

    //ChangeAmount
    public double getChangeAmount() {
        return changeAmount.get();
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount.set(changeAmount);
    }

    public DoubleProperty changeAmountProperty() {
        return changeAmount;
    }

    //CreatedAt
    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }
}
