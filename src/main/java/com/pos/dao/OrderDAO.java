package com.pos.dao;

import com.pos.model.Order;
import com.pos.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// CRUD: Create(add) - Read(get) - Update - Delete
public class OrderDAO {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.*, e.full_name as employee_name FROM orders as o " +
                "LEFT JOIN employees as e on e.id = o.employee_id "
                + "ORDER BY o.created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            Order order = new Order();
            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setEmployeeId(rs.getInt("employee_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setCustomerPaid(rs.getDouble("customer_paid"));
                order.setChangeAmount(rs.getDouble("change_amount"));
                order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Detail item Order
    public List<OrderItem> getAllOrderItems(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT * FROM order_item WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("product_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                orderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    // total sales today
    public double getTotalSalesToday() {
        String query = "SELECT IFNULL(SUM(total_amout), 0) as total FROM orders WHERE DATE(created_at) = CURDATE()";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // total orders today
    public double getTotalOrdersToday() {
        String query = "SELECT COUNT(*) as total FROM orders WHERE DATE(create_at) = CURDATE()";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Create new order
    public int createOrder(Order order, List<OrderItem> orderItems) {

        Connection conn = null;
        PreparedStatement pstmtOrder = null;
        PreparedStatement pstmtItem = null;
        ResultSet rs = null;
        int orderId = -1;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String orderQuery = "INSERT INTO orders (employee_id, total_amount, payment_method, customer_paid, change_amount) "
                    + "VALUES(?, ?, ?, ?, ?)";
            pstmtOrder = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtOrder.setInt(1, order.getEmployeeId());
            pstmtOrder.setDouble(2, order.getTotalAmount());
            pstmtOrder.setString(3, order.getPaymentMethod());
            pstmtOrder.setDouble(4, order.getCustomerPaid());
            pstmtOrder.setDouble(5, order.getChangeAmount());
            pstmtOrder.executeUpdate();

            // ID
            rs = pstmtOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt("id");
            }

            // add detail item
            String itemQuery = "INSERT INTO order_item(order_id, product_id, product_name, quantity, price, subtotal) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            pstmtItem = conn.prepareStatement(itemQuery);
            for (OrderItem item : orderItems) {
                pstmtItem.setInt(1, orderId);
                pstmtItem.setInt(2, item.getProductId());
                pstmtItem.setString(3, item.getProductName());
                pstmtItem.setInt(4, item.getQuantity());
                pstmtItem.setDouble(5, item.getPrice());
                pstmtItem.setDouble(6, item.getSubtotal());
                pstmtItem.executeUpdate();

                // update stock
                ProductDAO productDAO = new ProductDAO();
                productDAO.updateStock(item.getProductId(), item.getQuantity());
            }

            conn.commit();
            return orderId;

        } catch (SQLException e) {
            // Rollback if fail
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtOrder != null) pstmtOrder.close();
                if (pstmtItem != null) pstmtItem.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
