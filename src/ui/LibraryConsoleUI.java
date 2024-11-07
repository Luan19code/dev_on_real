package ui;

import application.BookService;
import application.LoanService;
import application.UserService;
import controllers.BookController;
import controllers.LoanController;
import controllers.UserController;
import infrastructure.repositories.*;
import java.util.Scanner;

public class LibraryConsoleUI {
    private final BookController bookController;
    private final UserController userController;
    private final LoanController loanController;
    private final Scanner scanner;

    public LibraryConsoleUI() {
        scanner = new Scanner(System.in);

        InMemoryBookRepository bookRepository = new InMemoryBookRepository();
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryLoanRepository loanRepository = new InMemoryLoanRepository();

        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);
        LoanService loanService = new LoanService(loanRepository);

        bookController = new BookController(bookService, scanner);
        userController = new UserController(userService, scanner);
        loanController = new LoanController(loanService, bookService, userService, scanner);
    }

    public void start() {
        while (true) {
            try {
                System.out.println("=== Library Management System ===");
                System.out.println("1. Manage Books");
                System.out.println("2. Manage Users");
                System.out.println("3. Manage Loans");
                System.out.println("0. Exit");

                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        bookController.manageBooks();
                        break;
                    case "2":
                        userController.manageUsers();
                        break;
                    case "3":
                        loanController.manageLoans();
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }
        }
    }
}