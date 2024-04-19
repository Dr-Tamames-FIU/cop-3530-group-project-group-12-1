public class CarDatabase {

  // Contributor: Bosco Silva

  private String make;
  private String model;
  private int year;
  private String color;
  private int mileage;
  private int price;
  public int id;

  public CarDatabase(String make, String model, int year, String color, int mileage, int price, int id) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.color = color;
    this.mileage = mileage;
    this.price = price;
    this.id = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getMileage() {
    return mileage;
  }

  public void setMileage() {
    this.mileage = mileage;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice() {
    this.price = price;
  }

  public int getVehicleID() {
    return id;
  }

  public void setVehicleID() {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Make = '" + make + '\'' +
        ", Model = '" + model + '\'' +
        ", Year = " + year +
        ", Color = '" + color + '\'' +
        ", Mileage = '" + mileage + '\'' +
        ", Price = '" + price + '\'' +
        ", ID = '" + id + '\'' + "\n";
  }
}
