package location;

public class LocationFactory {

  private LocationFactory() {
    // not needed
  }
  
  public static AccomodationLocation getAccomodationLocation(final int capacity, final int typeId, 
      final String name) {
    return new AccomodationLocation(capacity, typeId, name);
  }
  
  public static WinterSportLocation getWinterSportLocation(final int typeId, final String name) {
    return new WinterSportLocation(typeId, name);
  }

}
