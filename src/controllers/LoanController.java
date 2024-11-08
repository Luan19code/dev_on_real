package controllers;

import application.BookService;
import application.LoanService;
import application.UserService;
import domain.entities.Book;
import domain.entities.Loan;
import domain.entities.User;

import java.util.List;
import java.util.Scanner;

public class LoanController {
    private final LoanService loanService;
    private final BookService bookService;
    private final UserService userService;
    private final Scanner scanner;

    // Constructor
    public LoanController(LoanService loanService, BookService bookService, UserService userService, Scanner scanner) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.userService = userService;
        this.scanner = scanner;
    }

    // Methods
    public void manageLoans() {
        while (true) {
            System.out.println("=== Manage Loans ===");
            System.out.println("1. Loan Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Loan History");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    loanBook();
                    break;
                case "2":
                    returnBook();
                    break;
                case "3":
                    viewLoanHistory();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void loanBook() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        loanService.loanBook(book, user);
        System.out.println("Book loaned successfully.");
    }

    private void returnBook() {
        System.out.print("Enter Loan ID: ");
        String loanId = scanner.nextLine();
        loanService.returnBook(loanId);
    }

    private void viewLoanHistory() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        List<Loan> loans = loanService.getLoansByUserId(userId);

        System.out.println("=== Loan History ===");
        for (Loan loan : loans) {
            System.out.printf("Loan ID: %s | Book: %s | Loan Date: %s | Returned: %s | Fine: $%.2f%n",
                    loan.getId(),
                    loan.getBook().getTitle(),
                    loan.getLoanDate(),
                    loan.isReturned() ? "Yes" : "No",
                    loan.getFine());
        }
    }
}