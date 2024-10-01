package com.example.libraryapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManagement implements DatabaseOperation  {

    @Override
    public void insert(String tableName, String[] attributes, String[] values) {
        // Construct the SQL query with placeholders
        String query = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < attributes.length; i++) {
            query += attributes[i];
            if (i < attributes.length - 1) {
                query += ", ";
            }
        }
        query += ") VALUES (";
        for (int i = 0; i < values.length; i++) {
            query += "?"; // Use placeholders for values
            if (i < values.length - 1) {
                query += ", ";
            }
        }
        query += ");";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values for placeholders
            for (int i = 0; i < values.length; i++) {
                stmt.setString(i + 1, values[i]);
            }

            // Execute the update
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(String tableName, String[] attributes, String[] values) {
        String query = "DELETE FROM " + tableName + " WHERE ";
        for (int i = 0; i < attributes.length; i++) {
            query += attributes[i] + " = ?";
            if (i < attributes.length - 1) {
                query += " AND ";
            }
        }
        query += ";";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set values for placeholders
            for (int i = 0; i < values.length; i++) {
                stmt.setString(i + 1, values[i]);
            }
            // Execute the update
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
