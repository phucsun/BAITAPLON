package com.example.libraryapp;

public class User {
    private String name;
    private int borrowedBooks;

    public User(String name, int borrowedBooks) {
        this.name = name;
        this.borrowedBooks = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowDocument() {
        this.borrowedBooks++;
    }

    public void returnDocument() {
        if(this.borrowedBooks > 0) {
            this.borrowedBooks--;
        }else {
            System.out.println("You have no borrowed books.");
        }
    }
}
