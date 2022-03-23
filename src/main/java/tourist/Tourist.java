package tourist;

import java.util.HashMap;
import java.util.Map;

import location.AbstractLocation;
import sport.ISport;

public class Tourist {

  private final Map<ISport, Integer> sports = new HashMap<>();
  private AbstractLocation currentLocation;
  
  public Tourist() {
    // TODO Auto-generated constructor stub
  }
  
  public void addSport(ISport sport, final int roleId) throws Exception {
    if(sports.containsKey(sport)) {
      throw new Exception("Sport is already registered");
    } else {
      sports.put(sport, roleId);
    }
  }

  public AbstractLocation getCurrentLocation() {
    return currentLocation;
  }

  public void setCurrentLocation(AbstractLocation currentLocation) {
    this.currentLocation = currentLocation;
  }

  public Map<ISport, Integer> getSports() {
    return sports;
  }

  @Override
  public String toString() {
    return "Tourist [sports=" + sports + ", currentLocation=" + currentLocation + "]";
  }

  
}
