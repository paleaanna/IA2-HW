package sport;

public class Sport implements ISport {

  private String name;
  
  Sport(final String name) {
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
    if(name != null && obj instanceof Sport) {
      final Sport sportValue = (Sport)obj;
      return name.equals(sportValue.name);
    }
    
    return super.equals(obj);
  }
  
  @Override
  public int hashCode() {
    return 31 + name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Sport [name=" + name + "]";
  }
  
  
}
