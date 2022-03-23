package location;

public class AccomodationLocation extends AbstractLocation {

  private int capacity;
  private int typeId;
  
  
  AccomodationLocation(final int capacity, final int typeId, final String name) {
    super();
    setName(name);
    this.capacity = capacity;
    this.typeId = typeId;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getType() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public String toString() {
    return "AccomodationLocation [capacity=" + capacity + ", typeId=" + typeId + "]";
  }
  
  

  
}
