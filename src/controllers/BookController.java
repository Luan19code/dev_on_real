package controllers;

import application.BookService;
import domain.entities.Book;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookController {
    private final BookService bookService;
    private final Scanner scanner;

    public BookController(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void manageBooks() {
        while (true) {
            System.out.println("=== Manage Books ===");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Search Books");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    listAllBooks();
                    break;
                case "3":
                    searchBooks();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        String id = UUID.randomUUID().toString();
        Book book = new Book(id, title, author, genre);
        bookService.addBook(book);

        System.out.println("Book added successfully.");
    }

    private void listAllBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("=== List of Books ===");
        for (Book book : books) {
            System.out.printf("ID: %s | Title: %s | Author: %s | Genre: %s%n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
        }
    }

    private void searchBooks() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        List<Book> books = bookService.searchBooks(keyword);
        System.out.println("=== Search Results ===");
        for (Book book : books) {
            System.out.printf("ID: %s | Title: %s | Author: %s | Genre: %s%n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
        }
    }
}
