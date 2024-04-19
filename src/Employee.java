// Contributer: Artur Yadgarov
import java.util.ArrayList;
import java.util.List;

public class Employee {
  private String name;
  private int id;
  private String brandSpecialty;
  private List<Customer> customers;

  public Employee(String name, int id, String brandSpecialty) {
    this.name = name;
    this.id = id;
    this.brandSpecialty = brandSpecialty;
    this.customers = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getBrandSpecialty() {
    return brandSpecialty;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void addCustomer(String customerName, String phoneNumber) {
    Customer customer = new Customer(customerName, phoneNumber);
    customers.add(customer);
  }

  @Override
  public String toString() {
    return "Employee{" +
        "name='" + name + '\'' +
        ", id=" + id +
        ", brandSpecialty='" + brandSpecialty + '\'' +
        '}';
  }
}
