package com.pos.service;

import com.pos.model.Order;
import com.pos.model.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    List<OrderItem> getAllOrderItems(int orderId);

    double getTotalSalesToday();

    double getTotalOrdersToday();

    int createOrder(Order order, List<OrderItem> orderItems);


}
