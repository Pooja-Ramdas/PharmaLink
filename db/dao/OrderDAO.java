// OrderDAO.java
package com.pharmalink.db.dao;

import com.pharmalink.db.JdbcUtil;
import com.pharmalink.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static void placeOrder(Order order) throws SQLException {
        String insertOrderQuery = "INSERT INTO orders (id, customer_id, total_price, status, created_at) VALUES (?, ?, ?, ?, ?)";
        String insertOrderItemQuery = "INSERT INTO order_items (id, order_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false); // start transaction

            // Insert into orders table
            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderQuery)) {
                orderStmt.setString(1, order.getId());
                orderStmt.setString(2, order.getCustomerId());
                orderStmt.setBigDecimal(3, order.getTotalPrice());
                orderStmt.setString(4, "PENDING");
                orderStmt.setTimestamp(5, order.getCreatedAt());
                orderStmt.executeUpdate();
            }

            // Insert into order_items table
            try (PreparedStatement itemStmt = conn.prepareStatement(insertOrderItemQuery)) {
                itemStmt.setString(1, java.util.UUID.randomUUID().toString());
                itemStmt.setString(2, order.getId());
                itemStmt.setString(3, order.getMedicineId());
                itemStmt.setInt(4, order.getQuantity());
                itemStmt.setBigDecimal(5, order.getTotalPrice()); // assuming price here is per item * qty
                itemStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
    }

    public static List<Order> getOrdersByPharmacy(String pharmacyId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = """
            SELECT o.id, o.customer_id, oi.medicine_id, oi.quantity, o.total_price, o.created_at
            FROM orders o
            JOIN order_items oi ON o.id = oi.order_id
            JOIN medicines m ON oi.medicine_id = m.id
            WHERE m.pharmacy_id = ?
        """;

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pharmacyId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        rs.getString("customer_id"),
                        pharmacyId,
                        rs.getString("medicine_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("total_price"),
                        rs.getTimestamp("created_at")
                );
                orders.add(order);
            }
        }

        return orders;
    }

    public static List<Order> getOrdersByCustomerId(String customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = """
            SELECT o.id, o.customer_id, oi.medicine_id, oi.quantity, o.total_price, o.created_at
            FROM orders o
            JOIN order_items oi ON o.id = oi.order_id
            WHERE o.customer_id = ?
        """;

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("id"),
                        customerId,
                        null,
                        rs.getString("medicine_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("total_price"),
                        rs.getTimestamp("created_at")
                );
                orders.add(order);
            }
        }

        return orders;
    }
}
