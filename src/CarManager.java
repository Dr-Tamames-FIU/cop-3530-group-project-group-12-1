import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class CarManager {

  // Contributor: Bosco Silva

  static ArrayList<CarDatabase> CarList = new ArrayList<>();

  Main main = new Main();

  private void carList(CarDatabase car) {
    try (FileWriter writer = new FileWriter("vehicleList.txt", true);
        BufferedWriter writer2 = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(writer2)) {
      out.println("Make: " + car.getMake() + " | " + "Model: " + car.getModel() + " | " +
          "Year: " + car.getYear() + " | " + "Color: " + car.getColor() + " | " + "Mileage: " + car.getMileage() + " | "
          +
          "Price: " + car.getPrice() + " | " + "ID: " + car.getVehicleID());
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
    }
  }

  public void carManagerMenu() {
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
    boolean isValid = false;

    while (!isValid) { // Car Manager Menu
      System.out.println("Car Manager");
      System.out.println(" ");

      System.out.println("What would you like to do?: ");
      System.out.println("---------------------------------");
      System.out.println("1. Add vehicle");
      System.out.println("2. Remove vehicle");
      System.out.println("3. Check current vehicle list");
      System.err.println("4. Return to Main Menu");
      String input = scanner.nextLine();

      if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
        choice = Integer.parseInt(input);
        isValid = true;
      } else {
        System.out.println("Invalid choice. Please choose a valid option.");
        System.out.println(" ");
      }
    }
    if (choice == 1) {
      addVehicle(); // Proceeds to the Add Vehicle Menu
    } else if (choice == 2) {
      removeVehicle(); // Proceeds to the Remove Vehicle Menu
    } else if (choice == 3) {
      currentVehicleList(); // Displays the current vehicle list
    } else if (choice == 4) {
      System.out.println("Returning to Main Menu...");
      System.out.println("--------------------------");
      return; // Returns to the Car Manager Menu
    }
  }

  public void addVehicle() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Add vehicle");

    System.out.println("Enter Make (Blank to cancel): ");

    String make = scanner.nextLine();
    if (make.equals("")) {
      return; // Returns to Main Menu if nothing is typed.
    }

    System.out.println("Enter Model (Blank to cancel): ");

    String model = scanner.nextLine();
    if (model.equals("")) {
      return; // Returns to Main Menu if nothing is typed.
    }

    System.out.println("Enter Year (Enter '-1' to cancel): ");

    int year = scanner.nextInt();
    if (year == -1) {
      return; // Returns to Main Menu if -1 is typed.
    }

    scanner.nextLine();

    System.out.println("Enter Color (Blank to cancel): ");

    String color = scanner.nextLine();
    if (color.equals("")) {
      return; // Returns to Main Menu if nothing is typed.
    }

    System.out.println("Enter Mileage (Enter '-1' to cancel): ");

    int mileage = scanner.nextInt();
    if (mileage == -1) {
      return; // Returns to Main Menu if nothing is typed.
    }
    scanner.nextLine();

    System.out.println("Enter Price (Enter '-1' to cancel): ");

    int price = scanner.nextInt();
    if (price == -1) {
      return; // Returns to Main Menu if -1 is typed.
    }
    scanner.nextLine();

    System.out.println("Enter Vehicle ID (Enter '-1' to cancel): ");

    int id = scanner.nextInt();
    if (id == -1) {
      return; // Returns to Main Menu if nothing is typed.
    }
    CarDatabase newCar = new CarDatabase(make, model, year, color, mileage, price, id); // Car is added to arraylist.
    CarList.add(newCar);
    System.out.println("Vehicle has successfully been added to inventory.");
    carList(newCar);
    carManagerMenu();
  }

  public void removeVehicle() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Removing a vehicle");
    System.out.println("-----------------------");

    try (BufferedReader reader = new BufferedReader(new FileReader("vehicleList.txt"))) { // Displays Current Vehicle
                                                                                          // List
      String line;
      System.out.println("Current Vehicle List:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    System.out.println("---------------------------");

    System.out.println("Enter the ID of the vehicle to remove: ");
    String vehicleID = scanner.nextLine();

    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("vehicleList.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.contains("| ID: " + vehicleID)) {
          lines.add(line);
          System.out.println("Vehicle has been successfully removed.");
        }
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    try (FileWriter writer = new FileWriter("vehicleList.txt");
        BufferedWriter bw = new BufferedWriter(writer)) {
      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
    }
    carManagerMenu();
  }

  public void currentVehicleList() {

    System.out.println("List of current vehicles: \n");
    try (BufferedReader reader = new BufferedReader(new FileReader("vehicleList.txt"))) {
      String line;
      System.out.println("Current Vehicle List:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    System.out.println("---------------------------");
    carManagerMenu();

  }

  public void currentVehicleListCustomer() {
  
    CustomerUI cu = new CustomerUI();
    System.out.println("List of current vehicles: \n");
    try (BufferedReader reader = new BufferedReader(new FileReader("vehicleList.txt"))) {
      String line;
      System.out.println("Current Vehicle List:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    System.out.println("---------------------------");
    cu.assistCustomer();

  }
}
