package location;

public abstract class AbstractLocation implements ILocation {

  protected String name;

  protected AbstractLocation() {

  }

  protected AbstractLocation(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(name != null && obj instanceof AbstractLocation) {
      final AbstractLocation value = (AbstractLocation)obj;
      return name.equals(value.name);
    }
    
    return super.equals(obj);
  }
  
  @Override
  public int hashCode() {
    return 31 + name != null ? name.hashCode() : 0;
  }

}
