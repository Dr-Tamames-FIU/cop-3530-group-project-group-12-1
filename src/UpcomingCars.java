// Contributer: Lisette Hawkins

public interface UpcomingCars<UC> {

  int size();

  boolean isEmpty();

  UC get(int index);

  UC remove(int index);

  public void add(UC e);

  public void add(int index, UC e);

  public boolean remove(UC e);

  public void display();
}
