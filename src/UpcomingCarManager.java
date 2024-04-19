// Contributer: Lisette Hawkins

import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class UpcomingCarManager implements UpcomingCars<UpcomingCar> {

  public LinkedList<UpcomingCar> upcomingCarsList;
  private static final String FILE_PATH = "upcomingCars.txt";
  private Scanner scanner = new Scanner(System.in);

  public UpcomingCarManager() {
    upcomingCarsList = new LinkedList<>();
  }

  public void UpcomingCarMenu() {
    System.out.println("Upcoming Car Manager selected.");
    while (true) {
      System.out.println(" ");
      System.out.println("What would you like to do?");
      System.out.println("---------------------------------");
      System.out.println("1. Add an upcoming car");
      System.out.println("2. Remove an upcoming car.");
      System.out.println("3. Display upcoming car list");
      System.out.println("4. Main Menu");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          addUpcomingCar();
          break;
        case 2:
          removeUpcomingCar();
          break;
        case 3:
          upcomingCarsList();
          break;
        case 4:
          return;
        default:
          System.out.println("Invalid choice, please select a valid option.");
      }
    }
  }

  private void addUpcomingCar() {
    scanner.nextLine();
    System.out.println("Adding an upcoming car.");

    System.out.println("Enter Make: ");
    String make = scanner.nextLine();

    System.out.println("Enter Model: ");
    String model = scanner.nextLine();

    System.out.println("Enter Year: ");
    int year = scanner.nextInt();

    scanner.nextLine();
    System.out.println("Enter Color: ");
    String color = scanner.nextLine();

    System.out.println("Enter Date of Arrival(MM-DD-YYYY): ");
    String arrivalDate = scanner.nextLine();

    System.out.println("Enter Price: ");
    int price = scanner.nextInt();

    scanner.nextLine();
    int id;

    while (true) {
      System.out.println("Enter Vehicle ID: ");
      id = scanner.nextInt();
      if (!idCheck(id)) {
        break;
      }
      System.out.println("Error: This ID is already in use. Please enter a different ID.");
    }

    UpcomingCar upcomingCar = new UpcomingCar(make, model, year, color, arrivalDate, price, id);
    upcomingCarsList.add(upcomingCar);
    addCarToFile(upcomingCar);
    System.out.println("Upcoming car successfully added.");

  }

  public void upcomingCarsList() {
    System.out.println("Upcoming Car List: \n");
    display();
  }

  private boolean idCheck(int id) {
    for (UpcomingCar car : upcomingCarsList) {
      if (car.getID() == id) {
        return true;
      }
    }
    return false;
  }

  private void addCarToFile(UpcomingCar car) {
    try (FileWriter writer = new FileWriter(FILE_PATH, true);
        BufferedWriter writer2 = new BufferedWriter(writer);
        PrintWriter out = new PrintWriter(writer2)) {
      out.println("Make: " + car.getMake() + " | " +
          "Model: " + car.getModel() + " | " +
          "Year: " + car.getYear() + " | " +
          "Color: " + car.getColor() + " | " +
          "Arrival Date: " + car.getArrivalDate() + " | " +
          "Price: " + car.getPrice() + " | " +
          "ID: " + car.getID());
    } catch (IOException e) {
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  public void removeUpcomingCar() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Removing a vehicle");
    System.out.println("-----------------------");

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      System.out.println("Upccomming Car List:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    System.out.println("---------------------------");

    System.out.println("Enter the ID of the vehicle you would like to remove: ");
    String vehicleID = scanner.nextLine();

    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
    try (FileWriter writer = new FileWriter(FILE_PATH);
        BufferedWriter bw = new BufferedWriter(writer)) {
      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
    }
    return;
  }

  @Override
  public int size() {
    return upcomingCarsList.size();
  }

  @Override
  public boolean isEmpty() {
    return upcomingCarsList.isEmpty();
  }

  @Override
  public UpcomingCar get(int index) {
    return upcomingCarsList.get(index);
  }

  @Override
  public UpcomingCar remove(int index) {
    return upcomingCarsList.remove(index);
  }

  @Override
  public void add(UpcomingCar car) {
    upcomingCarsList.add(car);
  }

  @Override
  public void add(int index, UpcomingCar car) {
    upcomingCarsList.add(car);
  }

  @Override
  public boolean remove(UpcomingCar car) {
    return upcomingCarsList.remove(car);
  }

  @Override
  public void display() {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      System.out.println("Upcomming Vehicle List:");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    System.out.println("---------------------------");
    return;
  }
}
