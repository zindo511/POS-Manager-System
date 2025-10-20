package com.pos.service.impl;

import com.pos.model.Order;
import com.pos.model.OrderItem;
import com.pos.repository.OrderRepository;
import com.pos.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderItem> getAllOrderItems(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getOrderItems();
    }

    @Override
    public double getTotalSalesToday() {
        return orderRepository.getTotalSalesToday();
    }

    @Override
    public double getTotalOrdersToday() {
        return orderRepository.getTotalOrdersToday();
    }

    @Override
    @Transactional
    public int createOrder(Order order, List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }

        order.setOrderItems(orderItems);

        double total = orderItems.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
}
