package com.pharmalink.db.dao;

import com.pharmalink.db.JdbcUtil;
import com.pharmalink.models.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    public static Medicine getMedicineById(String medId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "SELECT * FROM medicines WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medicine(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("pharmacy_id")
                );
            }
        }
        return null;
    }

    public static boolean addMedicine(Medicine medicine) throws SQLException {
        String query = "INSERT INTO medicines (id, name, manufacturer, price, stock_quantity, category, description, image_url, pharmacy_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, medicine.getId());
            stmt.setString(2, medicine.getName());
            stmt.setString(3, medicine.getManufacturer());
            stmt.setBigDecimal(4, medicine.getPrice());
            stmt.setInt(5, medicine.getStockQuantity());
            stmt.setString(6, medicine.getCategory());
            stmt.setString(7, medicine.getDescription());
            stmt.setString(8, medicine.getImageUrl());
            stmt.setString(9, medicine.getPharmacyId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public static boolean updateMedicine(Medicine medicine) throws SQLException {
        String query = "UPDATE medicines SET name=?, manufacturer=?, price=?, stock_quantity=?, category=?, description=?, image_url=? WHERE id=? AND pharmacy_id=?";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getManufacturer());
            stmt.setBigDecimal(3, medicine.getPrice());
            stmt.setInt(4, medicine.getStockQuantity());
            stmt.setString(5, medicine.getCategory());
            stmt.setString(6, medicine.getDescription());
            stmt.setString(7, medicine.getImageUrl());
            stmt.setString(8, medicine.getId());
            stmt.setString(9, medicine.getPharmacyId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public static boolean deleteMedicine(String medicineId) throws SQLException {
        String query = "DELETE FROM medicines WHERE id = ?";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, medicineId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public static boolean updateStock(String medicineId, int newStock) throws SQLException {
        String query = "UPDATE medicines SET stock_quantity = ? WHERE id = ?";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newStock);
            stmt.setString(2, medicineId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }


    public static List<Medicine> getMedicinesForAllPharmacies() {
        List<Medicine> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicines";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("pharmacy_id")
                );
                medicines.add(med);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
        }

        return medicines;
    }
    public static List<Medicine> getMedicinesByPharmacy(String pharmacyId) {
        List<Medicine> medicines = new ArrayList<>();
        String query = (pharmacyId == null)
                ? "SELECT * FROM medicines"
                : "SELECT * FROM medicines WHERE pharmacy_id = ?";

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (pharmacyId != null) {
                stmt.setString(1, pharmacyId);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getString("pharmacy_id")
                );
                medicines.add(med);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
        }

        return medicines;
    }

}
