package com.example.libraryapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Library {
    ArrayList<Document> documents;
    ArrayList<User> users;
    DatabaseManagement db = new DatabaseManagement();

    private void loadDocuments() {
        String query = "SELECT * FROM documents";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int copies = rs.getInt("copies");
                Document doc = new Document(title, author, copies);
                documents.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private  void loadUsers() {
        String query = "SELECT * FROM users";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                String name = rs.getString("name");
                int borrowedBooks = rs.getInt("borrowedBooks");
                User user = new User(name, borrowedBooks);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Library() {
        //Load documents and users from database
        documents = new ArrayList<>();
        users = new ArrayList<>();
        loadDocuments();
        loadUsers();
    }

    //manage functions
    public void addDocument(Document document) {
        String[] attributes = {"title", "author", "copies"};
        String[] values = {document.getTitle(), document.getAuthor(), String.valueOf(document.getCopies())};
        db.insert("documents", attributes, values);
        documents.add(document);
    }

    public void removeDocument(String title) {
        Document doc = findDocument(title);
        if(doc != null) {
            System.out.println("Document removed.");
            documents.remove(doc);
            String[] attributes = {"title"};
            String[] values = {title};
            db.delete("documents", attributes, values);
        }else {
            System.out.println("Document not found.");
        }
    }

    public void updateDocument(String title, String newTitle, String newAuthor, int newCopies) {
        Document doc = findDocument(title);
        if(doc != null) {
            doc.setTitle(newTitle);
            doc.setAuthor(newAuthor);
            doc.setCopies(newCopies);
            System.out.println("Document updated.");
        }else {
            System.out.println("Document not found.");
        }
    }

    public Document findDocument(String title) {
        for(Document doc : documents) {
            if(doc.getTitle().equals(title)) {
                return doc;
            }
        }
        return null;
    }

    public void displayDocuments() {
        if(documents.isEmpty()) {
            System.out.println("No documents.");
            return;
        }
        documents.forEach(Document::printInfo);
    }

    //manage users
    public void addUser(User user) {
        users.add(user);
    }

    public void borrowDocument(String title, String name)
    {
        Document doc = findDocument(title);
        User user = findUser(name);
        if(doc != null && user != null) {
            if(doc.getCopies() > 0) {
                doc.setCopies(doc.getCopies() - 1);
                user.borrowDocument();
                System.out.println("Document borrowed.");
            }else {
                System.out.println("No copies available.");
            }
        }else {
            System.out.println("Document or user not found.");
        }
    }

    public void returnDocument(String username, String Title) {
        Document doc = findDocument(Title);
        User user = findUser(username);
        if(doc != null && user != null) {
            doc.setCopies(doc.getCopies() + 1);
            user.returnDocument();
            System.out.println("Document returned.");
        }else {
            System.out.println("Document or user not found.");
        }
    }

    public void displayUsers() {
        if(users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        users.forEach(user -> System.out.println("Name: " + user.getName() + ", Borrowed books: " + user.getBorrowedBooks()));
    }

    public User findUser(String name) {
        for(User user : users) {
            if(user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
