package com.pharmalink.db;// File: com.pharmalink.db.DBTest.java

import com.pharmalink.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) {
        try {
            Connection conn = JdbcUtil.getConnection();

            if (conn != null) {
                System.out.println("JDBC connection successful!");
                conn.close(); // Always close the connection
            } else {
                System.out.println("Failed to establish JDBC connection.");
            }
        } catch (SQLException e) {
            System.out.println("⚠SQLException occurred:");
            e.printStackTrace();
        }
    }
}
