package com.example.libraryapp;

public interface DatabaseOperation {
    void insert(String tableName, String[] attributes, String[] values);
    void delete(String tableName, String[] attributes, String[] values);
}
