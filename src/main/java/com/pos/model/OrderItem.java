package com.pos.model;

import javafx.beans.property.*;

public class OrderItem {

    public final IntegerProperty id;
    public final IntegerProperty orderId;
    public final IntegerProperty productId;
    public final StringProperty productName;
    public final IntegerProperty quantity;
    public final DoubleProperty price;
    public final DoubleProperty subtotal;

    //Constructor no-argument
    public OrderItem() {
        this.id = new SimpleIntegerProperty();
        this.orderId = new SimpleIntegerProperty();
        this.productId = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.subtotal = new SimpleDoubleProperty();
    }

    //Constructor full-argument
    public OrderItem(int id, int orderId, int productId, String productName, int quantity, double price, double subtotal) {
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

    //OrderId
    public int getOrderId() {
        return orderId.get();
    }

    public void setOrderId(int orderId) {
        this.orderId.set(orderId);
    }

    public IntegerProperty orderIdProperty() {
        return orderId;
    }

    //ProductID
    public int getProductId() {
        return productId.get();
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public DoubleProperty subtotalProperty() {
        return subtotal;
    }
}
