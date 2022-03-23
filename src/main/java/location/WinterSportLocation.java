package location;

public class WinterSportLocation extends AbstractLocation {

  private int typeId;
    
  WinterSportLocation(final int typeId, final String name) {
    super();
    setName(name);
    this.typeId = typeId;
  }

  public int getType() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  @Override
  public String toString() {
    return "WinterSportLocation [typeId=" + typeId + ", name= " + name + "]";
  }
  
  

}
