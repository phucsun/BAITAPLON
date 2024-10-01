package com.example.libraryapp;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("----------------------------------------");
            System.out.println("\nWelcome to the Library Management System");
            System.out.println("[0] Exit");
            System.out.println("[1] Add Document");
            System.out.println("[2] Remove Document");
            System.out.println("[3] Update Document");
            System.out.println("[4] Find Documents");
            System.out.println("[5] Display Documents");
            System.out.println("[6] Add User");
            System.out.println("[7] Borrow Document");
            System.out.println("[8] Return Document");
            System.out.println("[9] Display Users");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.println("----------------------------------------");
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Enter the title to add: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the number of copies: ");
                    int copies = scanner.nextInt();
                    scanner.nextLine();
                    Document document = new Document(title, author, copies);
                    library.addDocument(document);
                    System.out.println("Document added.");
                    break;
                case 2:
                    System.out.print("Enter the title to remove: ");
                    title = scanner.nextLine();
                    library.removeDocument(title);
                    break;
                case 3:
                    System.out.print("Enter the title to update: ");
                    title = scanner.nextLine();
                    System.out.print("Enter the new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter the new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter the new number of copies: ");
                    int newCopies = scanner.nextInt();
                    scanner.nextLine();
                    library.updateDocument(title, newTitle, newAuthor, newCopies);
                    break;
                case 4:
                    System.out.print("Enter the title to find: ");
                    title = scanner.nextLine();
                    Document doc = library.findDocument(title);
                    if(doc != null) {
                        doc.printInfo();
                    }else {
                        System.out.println("Document not found.");
                    }
                    break;
                case 5:
                    library.displayDocuments();
                    break;
                case 6:
                    System.out.println("Enter the name to add: ");
                    String name = scanner.nextLine();
                    User user = new User(name, 0);
                    library.addUser(user);
                    break;
                case 7:
                    System.out.print("Enter the title to borrow: ");
                    title = scanner.nextLine();
                    System.out.print("Enter the name: ");
                    name = scanner.nextLine();
                    library.borrowDocument(title, name);
                    break;
                case 8:
                    System.out.println("Enter the username to return: ");
                    name = scanner.nextLine();
                    System.out.println("Enter the title: ");
                    title = scanner.nextLine();
                    library.returnDocument(title, name);
                    break;
                case 9:
                    library.displayUsers();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
