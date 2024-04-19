// Contributer: Artur Yadgarov
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerUI {
  private Map<Integer, Employee> employees;
  private Map<Integer, Customer> customers;

  public CustomerUI() {
    employees = new HashMap<>();
    customers = new HashMap<>();
    loadEmployeesFromFile("employees.txt");
  }

  private void loadEmployeesFromFile(String fileName) {
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        if (parts.length == 3) {
          int id = Integer.parseInt(parts[0].trim());
          String name = parts[1].trim();
          String brandSpecialty = parts[2].trim();
          Employee employee = new Employee(name, id, brandSpecialty);
          employees.put(id, employee);
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("Invalid data format in file: " + e.getMessage());
    }
  }

  public void assignEmployee() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Available Employees:");
    for (Employee employee : employees.values()) {
      System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() +
          ", Brand Specialty: " + employee.getBrandSpecialty());
    }

    System.out.println("Select an Employee by ID:");
    int employeeId = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    Employee selectedEmployee = employees.get(employeeId);
    if (selectedEmployee != null) {
      System.out.println("Enter your name:");
      String name = scanner.nextLine();
      System.out.println("Enter your phone number:");
      String phoneNumber = scanner.nextLine();

      // Record customer details with the selected employee
      selectedEmployee.addCustomer(name, phoneNumber);

      // Write customer details to a text file
      try {
        FileWriter writer = new FileWriter("customers.txt", true);
        writer.write("Employee ID: " + selectedEmployee.getId() + ", Customer Name: " + name +
            ", Phone Number: " + phoneNumber + "\n");
        writer.close();
        System.out.println("Customer assigned to Employee successfully.");
      } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
      }
    } else {
      System.out.println("Invalid Employee ID.");
    }
  }

  public void assistCustomer() {
    UpcomingCarManager ucm = new UpcomingCarManager();
    CarManager cm = new CarManager();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("Customer Menu:");
      System.out.println("1. Assign Employee");
      System.out.println("2. View all available cars");
      System.out.println("3. View upcoming cars");
      System.out.println("4. Main Menu");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          assignEmployee();
          break;
        case 2:
          cm.currentVehicleListCustomer();
          break;
        case 3:
          ucm.display();
          break;
        case 4:
          Main.main(null);
          break;
        default:
          System.out.println("Invalid choice. Please select a valid option.");
      }
    }
  }
}
