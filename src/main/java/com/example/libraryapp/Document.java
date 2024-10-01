package com.example.libraryapp;

public class Document {
    private String title;
    private String author;
    private int copies;

    public Document(String title, String author, int copies) {
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void printInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Copies: " + copies);
    }
}
