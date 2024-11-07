package controllers;

import application.UserService;
import domain.entities.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void manageUsers() {
        while (true) {
            System.out.println("=== Manage Users ===");
            System.out.println("1. Add User");
            System.out.println("2. List All Users");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    listAllUsers();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String id = UUID.randomUUID().toString();
        User user = new User(id, name);
        userService.addUser(user);

        System.out.println("User added successfully.");
    }

    private void listAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("=== List of Users ===");
        for (User user : users) {
            System.out.printf("ID: %s | Name: %s%n", user.getId(), user.getName());
        }
    }
}