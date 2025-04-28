package com.pharmalink.users;
import com.pharmalink.db.dao.MedicineDAO;
import com.pharmalink.db.JdbcUtil;
import com.pharmalink.exceptions.DatabaseConnectionException;
import com.pharmalink.exceptions.UserExistsException;
import com.pharmalink.models.Medicine;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static java.sql.JDBCType.NULL;

public class Customer extends User {

    public Customer(String id, String name, String email, String password, String role, String pharmacyId) {
        super(id, name, email, password, role, null); // No pharmacyId
    }

    public String getId() {
        return this.id;
    }


    @Override
    public void login() throws DatabaseConnectionException {
        try (Connection conn = JdbcUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE email=? AND password=? AND role='CUSTOMER'";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.email);
            stmt.setString(2, this.password);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {

                throw new RuntimeException("Login failed! Please sign up first.");
            } else {
                this.id = rs.getString("id");
                this.name = rs.getString("name");
                System.out.println("Login successful! Welcome, Customer " + this.name);
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database error during login: " + e.getMessage());
        }
    }

    @Override
    public void signup(String id, String name, String email, String password, String role, String pharmacyId) throws DatabaseConnectionException {
        try (Connection conn = JdbcUtil.getConnection()) {
            // Check if the user already exists
            String checkQuery = "SELECT * FROM users WHERE email=?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                throw new UserExistsException("User already exists! Please login.");
            }

            // If not, proceed to register the user
            id = UUID.randomUUID().toString();
            String insertQuery = "INSERT INTO users (id, name, email, password, role, pharmacy_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, id);
            insertStmt.setString(2, name);
            insertStmt.setString(3, email);
            insertStmt.setString(4, password);
            insertStmt.setString(5, role);
            insertStmt.setString(6, String.valueOf(NULL));
            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User signed up successfully! Now you can login.");
            } else {
                throw new DatabaseConnectionException("Signup failed, please try again.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database error during signup: " + e.getMessage());
        } catch (UserExistsException e) {
            System.out.println(e.getMessage());
        }

    }

    public void delete() throws DatabaseConnectionException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to delete your account? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println("Account deletion cancelled.");
            return;
        }

        try (Connection conn = JdbcUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE email = ?")) {

            stmt.setString(1, this.email);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("No account found to delete.");
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to delete user: " + e.getMessage());
        }
    }



    @Override
    public List<Medicine> viewAllMedicines() {
        return MedicineDAO.getMedicinesForAllPharmacies(); // Returns all from all pharmacies
    }
}
