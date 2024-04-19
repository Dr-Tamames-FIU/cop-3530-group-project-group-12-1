// Contributer: Artur Yadgarov
import java.io.*;
import java.util.*;

public class EmployeeManagerUI {
  public static final String FILE_PATH = "employees.txt"; // Path to the text file
  private Map<Integer, Employee> employees;

  public EmployeeManagerUI() {
    employees = new HashMap<>();
    loadEmployeesFromFile();
  }

  private void loadEmployeesFromFile() {
    try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String brandSpecialty = parts[2];
        employees.put(id, new Employee(name, id, brandSpecialty));
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    }
  }

  public void addEmployee(Employee employee) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
      writer.println(employee.getId() + "," + employee.getName() + "," + employee.getBrandSpecialty());
      System.out.println("Employee added successfully.");
      employees.put(employee.getId(), employee);
    } catch (IOException e) {
      System.out.println("Error adding employee: " + e.getMessage());
    }
  }

  public void removeEmployee(int employeeId) {
    List<String> lines = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        if (Integer.parseInt(parts[0]) != employeeId) {
          lines.add(line);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    }

    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
      for (String line : lines) {
        writer.println(line);
      }
      System.out.println("Employee removed successfully.");
      employees.remove(employeeId);
    } catch (IOException e) {
      System.out.println("Error removing employee: " + e.getMessage());
    }
  }

  public void listEmployeesByName() {
    employees.values().stream()
        .sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
        .forEach(System.out::println);
  }

  public void listEmployeesById() {
    employees.values().stream()
        .sorted(Comparator.comparingInt(Employee::getId))
        .forEach(System.out::println);
  }

  // Method to encapsulate the functionality of Employee Manager UI
  public void run() {
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
      System.out.println("Employee Manager");
      System.out.println("1. Add Employee");
      System.out.println("2. Remove Employee");
      System.out.println("3. List Employees by Name");
      System.out.println("4. List Employees by ID");
      System.out.println("5. Back to Main Menu");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          // Add Employee
          System.out.println("Enter employee name:");
          String name = scanner.nextLine();
          System.out.println("Enter employee ID:");
          int id = scanner.nextInt();
          scanner.nextLine(); // Consume newline
          System.out.println("Enter employee brand specialty:");
          String brandSpecialty = scanner.nextLine();
          Employee employee = new Employee(name, id, brandSpecialty);
          addEmployee(employee);
          break;
        case 2:
          // Remove Employee
          System.out.println("Enter employee ID to remove:");
          int removeId = scanner.nextInt();
          removeEmployee(removeId);
          break;
        case 3:
          // List Employees by Name
          listEmployeesByName();
          break;
        case 4:
          // List Employees by ID
          listEmployeesById();
          break;
        case 5:
          // Back to Main Menu
          Main.main(null);
          break;
        default:
          System.out.println("Invalid choice. Please select a valid option.");
      }
    }

    scanner.close();
  }
}
