// Contributer: Artur, Bosco, Lisette
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;
    EmployeeManagerUI em = new EmployeeManagerUI();
    CarManager cm = new CarManager();
    CustomerUI cu = new CustomerUI();
    UpcomingCarManager ucm = new UpcomingCarManager();

    while (!exit) {
      System.out.println("Welcome to the Car Dealership Management System!");
      System.out.println("Please select your role:");
      System.out.println("1. Customer");
      System.out.println("2. Employee Manager");
      System.out.println("3. Car Manager");
      System.out.println("4. Upcoming Car Manager");
      System.out.println("5. Exit");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          // Customer functionality
          cu.assistCustomer();
          break;
        case 2:
          // Employee Manager functionality
          em.run();
          break;
        case 3:
          // Car Manager functionality
          cm.carManagerMenu();
          break;
        case 4:
          // Upcoming Car Manager functionality
          ucm.UpcomingCarMenu();
          break;
        case 5:
          // Exit
          System.out.println("Exiting the program...");
          exit = true;
          break;
        default:
          System.out.println("Invalid choice. Please select a valid option.");
      }
    }

    scanner.close();
  }
}
