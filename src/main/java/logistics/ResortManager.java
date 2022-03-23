package logistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import location.AbstractLocation;
import location.AccomodationLocation;
import location.LocationFactory;
import location.WinterSportLocation;
import sport.ISport;
import sport.Sport;
import sport.SportFactory;
import tourist.Tourist;

public class ResortManager {

  private static ResortManager resortManager;
  private List<Tourist> tourits = new ArrayList<>();
  private Map<Integer, String> sportRoles;
  private Map<Integer,  String> accomodationLocationsTypes;
  private Map<Integer, String> winterSportLocationTypes;
  private final Set<WinterSportLocation> sportLocations = new HashSet<>();
  private final Set<AccomodationLocation> accomodationLocations = new HashSet<>();
  private final Set<Sport> sports = new HashSet<>();

  private ResortManager() {
    /*

    sportRoles = new HashMap<>();
    accomodationLocationsTypes = new HashMap<>();
    winterSportLocationTypes = new HashMap<>();

    sportRoles.put(0, "Monitor");
    sportRoles.put(1, "Beginner");
    sportRoles.put(2, "Coach");
    sportRoles.put(3, "Adnavnced");

    accomodationLocationsTypes.put(0, "Pensiune");
    accomodationLocationsTypes.put(1, "Hotel *");
    accomodationLocationsTypes.put(2, "Hotel **");
    accomodationLocationsTypes.put(3, "Hotel ***");
    accomodationLocationsTypes.put(4, "Hotel ****");
    accomodationLocationsTypes.put(5, "Hotel *****");

    winterSportLocationTypes.put(0, "Partie unde se poate schia");
    winterSportLocationTypes.put(1, "Partie unde nu se poate schia");
    winterSportLocationTypes.put(2, "Pationoar");

    try {
      serializeRolesAndTypes();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     */

    try {
      deserializeRolesAndTypes();
    } catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(winterSportLocationTypes);
    System.out.println(sportRoles);
    System.out.println(accomodationLocationsTypes);

    readData("sport-locations.xml");
    System.out.println(sportLocations);
    System.out.println(tourits);

  }

  public static ResortManager getInstance() {
    if(resortManager == null) {
      synchronized (ResortManager.class) {
        if(resortManager == null) {
          resortManager = new ResortManager();
        }
      }
    }

    return resortManager;

  }

  public void readData(final String fileName) {
    try {
      final File inputFile = new File(fileName);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
      NodeList nList = doc.getElementsByTagName("sport-location");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          final Element eElement = (Element) nNode;
          final int typeId = (Integer.parseInt(eElement
              .getElementsByTagName("typeid")
              .item(0)
              .getTextContent()));
          final String name = eElement
              .getElementsByTagName("name")
              .item(0)
              .getTextContent();
          sportLocations.add(LocationFactory.getWinterSportLocation(typeId, name));
        }
      }

      nList = doc.getElementsByTagName("accomodation-location");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          final Element eElement = (Element) nNode;
          final int typeId = (Integer.parseInt(eElement
              .getElementsByTagName("typeid")
              .item(0)
              .getTextContent()));
          final String name = eElement
              .getElementsByTagName("name")
              .item(0)
              .getTextContent();
          final int capacity = (Integer.parseInt(eElement
              .getElementsByTagName("capacity")
              .item(0)
              .getTextContent()));
          accomodationLocations.add(LocationFactory.getAccomodationLocation(capacity, typeId, name));
        }
      }

      nList = doc.getElementsByTagName("tourist");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Tourist tourist = new Tourist();
          tourits.add(tourist);
          final Element eElement = (Element) nNode;
          String loc = eElement
              .getElementsByTagName("location")
              .item(0)
              .getTextContent();
          AbstractLocation[] touristLoc = new AbstractLocation[1];
          accomodationLocations.forEach(locat -> {
            if(locat.getName().equals(loc)) {
              touristLoc[0] = locat;
            }

          });


          tourist.setCurrentLocation(touristLoc[0]);
          Element sportsElement = (Element)eElement.getElementsByTagName("sports").item(0);
          if(sportsElement != null) {
            NodeList sportItems = sportsElement.getChildNodes();
            int noOfSportItems = sportItems.getLength();
            for(int index = 0; index < noOfSportItems; index++) {
              Node sportItem = sportItems.item(index);

              if(sportItem.getNodeType() == Node.ELEMENT_NODE) {
                final int roleId = (Integer.parseInt(eElement
                    .getElementsByTagName("roleid")
                    .item(0)
                    .getTextContent()));
                final String name = eElement
                    .getElementsByTagName("name")
                    .item(0)
                    .getTextContent();
                final ISport localSport = SportFactory.createSport(name);
                if(!sports.contains(localSport)) {
                  sports.add((Sport)localSport);
                }

                tourist.addSport(localSport, roleId);

              }
            }

          }

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void writeReports(final String fileName) {
    // TODO
  }

  private void deserializeRolesAndTypes() throws IOException, ClassNotFoundException {
    sportRoles                 = (HashMap)deserializeObject("sportroles.ser");
    winterSportLocationTypes   = (HashMap)deserializeObject("sportlocations.ser");
    accomodationLocationsTypes = (HashMap)deserializeObject("accomodationlocations.ser");
  }

  private void serializeRolesAndTypes() throws IOException {
    serializeObject("sportroles.ser", sportRoles);
    serializeObject("sportlocations.ser", winterSportLocationTypes);
    serializeObject("accomodationlocations.ser", accomodationLocationsTypes);
  }

  private void serializeObject(final String filename, final Object obj) throws IOException {
    final FileOutputStream fos = new FileOutputStream(filename);
    final ObjectOutputStream outputStream = new ObjectOutputStream(fos);
    outputStream.writeObject(obj);
    outputStream.close();
    fos.close();
  }

  private Object deserializeObject(final String filename) throws IOException, ClassNotFoundException {
    final FileInputStream fis = new FileInputStream(filename);
    ObjectInputStream inputStream = new ObjectInputStream(fis);
    Object obj = inputStream.readObject();
    inputStream.close();
    fis.close();
    return obj;
  }

  public static void main(String[] args) {
    ResortManager.getInstance();
  }
}
