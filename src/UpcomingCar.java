// Contributer: Lisette Hawkins

public class UpcomingCar {
  private String make;
  private String model;
  private int year;
  private String color;
  private String arrivalDate;
  private int price;
  private int id;

  public UpcomingCar(String make, String model, int year, String color, String arrivalDate, int price, int id) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.color = color;
    this.arrivalDate = arrivalDate;
    this.price = price;
    this.id = id;
  }

  public int getID() {
    return id;
  }

  public String getMake() {
    return make;
}

public String getModel() {
    return model;
}

public int getYear() {
    return year;
}

public String getColor() {
    return color;
}

public String getArrivalDate() {
    return arrivalDate;
}

public int getPrice() {
    return price;
}


  @Override
  public String toString() {
    return "Make: " + make + " | Model: " + model + " | Year: " + year + " | Color: " + color + " | Arrival Date: "
        + arrivalDate + " | Price: " + price + " | ID: " + id;
  }

}