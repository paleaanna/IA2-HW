package sport;

public class SportFactory {

  private SportFactory() {
    // not needed
  }
  
  public static ISport createSport(final String name) {
    return new Sport(name);
  }

}
