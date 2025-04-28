package com.pharmalink.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/pharmalink"; // Replace with thy DB name
    private static final String USER = "root"; // Replace with thy username
    private static final String PASSWORD = "st@r"; // Replace with thy password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("⚠ JDBC Driver not found!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
