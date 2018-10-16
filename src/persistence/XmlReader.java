package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import gameworld.*;


/**
 * XML Reader which reads an XML save file.
 * @author hoongkevi
 *
 */

public class XmlReader {
  /**
   * reads an XML file from a given directory.
   * @param args
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    String filePath = "/home/hoongkevi/test.xml";
    File xml = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;
    try {
       db = dbFactory.newDocumentBuilder();
       Document doc = db.parse(xml);
       doc.getDocumentElement().normalize();


       NodeList nodelist = doc.getElementsByTagName("Game");
       List<GameWorld> elemList = new ArrayList<GameWorld>();
       for(int i = 0; i< nodelist.getLength(); i++) {
         elemList.add(getGameWorld(nodelist.item(i),doc));
       }

    }catch(ParserConfigurationException e) {
      System.out.println("ParserConfigurationException error");
    }catch(SAXException e) {
      System.out.println("SAX Exception error");
    }catch(IOException e) {
      System.out.println("File input/output error");
    }


  }

  /**
   * @param node
   * @param doc
   * @return GameWorld with the state of the game
   */
  public static GameWorld getGameWorld(Node node,Document doc) {
    Player player = new Player(getPlayerLocation(node, doc));
    Location[][] locations = new Location[0][0];
    Set<Quest> quests = new HashSet<Quest>();
    GameWorld game = new GameWorld(locations,player.getCurrentLocation());
    return game;
  }

  /**
   * @param node
   * @param doc
   * @param player
   * @return inventory.
   */
  public static List<Item> getInventory(Node node,Document doc,Player player){
    NodeList nodelist = doc.getElementsByTagName("Item");
    List<Item> itemList = new ArrayList<Item>();
    for(int i = 0; i<nodelist.getLength(); i++) {
      Item item = getItem(nodelist.item(i),doc);
      itemList.add(item);
      player.pickupItem(item);
    }
    return itemList;
  }

  /**
   * @param node
   * @param doc
   * @return item created by reading xml
   */
  public static Item getItem(Node node, Document doc) {
    String type = doc.getDocumentElement().getElementsByTagName("Player").item(1).getAttributes().getNamedItem("type").getNodeValue();
    String name = doc.getDocumentElement().getElementsByTagName("Player").item(1).getAttributes().getNamedItem("name").getNodeValue();
    String description = doc.getDocumentElement().getElementsByTagName("Player").item(1).getAttributes().getNamedItem("description").getNodeValue();
    String imagepath = doc.getDocumentElement().getElementsByTagName("Player").item(1).getAttributes().getNamedItem("filepath").getNodeValue();
    if(type.equals("Treasure")) {
      Treasure treasure = new Treasure(name,description,imagepath);
      return treasure;
    }
    else if(type.equals("Key")) {
      Key key = new Key();
      return key;
    }
    return null;
  }



  /**
   * @param node
   * @param doc
   * @return new container
   */
  public static StationaryContainer getContainer(Node node,Document doc) {
    if(node.getNodeType() == Node.ELEMENT_NODE) {
      Element elem = (Element) node;
      String name = elem.getAttribute("name");
      String description = elem.getAttribute("description");
      StationaryContainer loadContainer = new StationaryContainer(name,description, null);
      return loadContainer;
    }
    return null;
  }

  /**
   * @param node
   * @param doc
   * @return new quest
   */
  public static List<Quest> getQuest(Node node,Document doc) {
    NodeList questList = doc.getDocumentElement().getElementsByTagName("quest");
    List<Quest> quests = new ArrayList<Quest>();
    List<Treasure> requirements = new ArrayList<Treasure>();
    for(int i =0; i<questList.getLength();i++) {
      String name = questList.item(i).getAttributes().getNamedItem("name").getNodeValue();
      String description = questList.item(i).getAttributes().getNamedItem("description").getNodeValue();
      String imagepath = questList.item(i).getAttributes().getNamedItem("imagepath").getNodeValue();
      String complete = questList.item(i).getAttributes().getNamedItem("complete").getNodeValue();
      requirements = getQuestReqs(node,doc);
      quests.add(new Quest(name, description, imagepath,requirements));
    }
    return quests;

  }

  /**
   * @param node
   * @param doc
   * @return quest requirements
   */
  public static List<Treasure> getQuestReqs(Node node, Document doc){
    NodeList QuestItems = doc.getDocumentElement().getElementsByTagName("QuestItems");
    List<Treasure> requirements = new ArrayList<Treasure>();
    for(int i = 0; i<QuestItems.getLength(); i++) {
     String name = QuestItems.item(i).getAttributes().getNamedItem("name").getNodeValue();
     String description = QuestItems.item(i).getAttributes().getNamedItem("description").getNodeValue();
     String imagepath = QuestItems.item(i).getAttributes().getNamedItem("imagePath").getNodeValue();
     Treasure treasure = new Treasure(name,description,imagepath);
     requirements.add(treasure);
    }
    return requirements;
  }

  /**
   * @param node
   * @param doc
   * @return new Location
   */
  public static Location getLocation(Node node,Document doc) {
    int x = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("Location").item(0).getAttributes().getNamedItem("height").getNodeValue());
    int y = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("Location").item(1).getAttributes().getNamedItem("height").getNodeValue());
    Item[][] grid = new Item[x][y];
    NodeList gridItemList = doc.getDocumentElement().getElementsByTagName("GridItem");
    for(int i = 0; i<gridItemList.getLength(); i++) {
      String name = gridItemList.item(i).getAttributes().getNamedItem("name").getNodeValue();
      String description = gridItemList.item(i).getAttributes().getNamedItem("description").getNodeValue();
      String type = gridItemList.item(i).getAttributes().getNamedItem("type").getNodeValue();
      int gridItemX = Integer.parseInt(gridItemList.item(i).getAttributes().getNamedItem("x").getNodeValue());
      int gridItemY = Integer.parseInt(gridItemList.item(i).getAttributes().getNamedItem("y").getNodeValue());
      if(type.equals("Treasure")) {
      grid[gridItemX][gridItemY] = new Treasure(name,description,null);
      }else if(type.equals("Key")) {
        grid[gridItemX][gridItemY] = new Key(); // have to implement location and passage methods before I can create a key.
      }
    }
    NodeList ExitList = doc.getDocumentElement().getElementsByTagName("exits");
    Boolean[] exits = new Boolean[ExitList.getLength()];
    for(int i =0; i<ExitList.getLength(); i++) {
      int exit = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("exit").item(i).getAttributes().getNamedItem("exitdirection").getNodeValue());
      exits[exit] = true;
    }
    Location location = new Location(exits, grid);
    return location;
  }

  /**
   * @param node
   * @param doc
   * @return new PlayerLocation
   */
  public static Location getPlayerLocation(Node node,Document doc) {
    int x = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("PlayerLocation").item(0).getAttributes().getNamedItem("height").getNodeValue());
    int y = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("PlayerLocation").item(0).getAttributes().getNamedItem("width").getNodeValue());
    Item[][] grid = new Item[x][y];
    NodeList gridItemList = doc.getDocumentElement().getElementsByTagName("GridItem");
    for(int i = 0; i<gridItemList.getLength(); i++) {
      String name = gridItemList.item(i).getAttributes().getNamedItem("name").getNodeValue();
      String description = gridItemList.item(i).getAttributes().getNamedItem("description").getNodeValue();
      String type = gridItemList.item(i).getAttributes().getNamedItem("type").getNodeValue();
      int gridItemX = Integer.parseInt(gridItemList.item(i).getAttributes().getNamedItem("x").getNodeValue());
      int gridItemY = Integer.parseInt(gridItemList.item(i).getAttributes().getNamedItem("y").getNodeValue());
      if(type.equals("Treasure")) {
      grid[gridItemX][gridItemY] = new Treasure(name,description,null);
      }else if(type.equals("Key")) {
        grid[gridItemX][gridItemY] = null; // have to implement location and passage methods before I can create a key.
      }
    }
    NodeList ExitList = doc.getDocumentElement().getElementsByTagName("exits");
    Boolean[] exits = new Boolean[ExitList.getLength()];
    for(int i =0; i<ExitList.getLength(); i++) {
      int exit = Integer.parseInt(doc.getDocumentElement().getElementsByTagName("exit").item(i).getAttributes().getNamedItem("exitdirection").getNodeValue());
      exits[exit] = true;
    }

    Location playerLocation = new Location(exits, grid);

    return playerLocation;
  }


  /**
   * @param node
   * @param doc
   * @return player elements
   */
  public static Player getPlayer(Node node,Document doc) {
    Player player = new Player(getPlayerLocation(node,doc));
    getInventory(node,doc,player);
    return player;
  }



  private static String getTagValue(String tag, Element element){
    NodeList nodelist = element.getElementsByTagName(tag).item(0).getChildNodes();
    Node node = (Node) nodelist.item(0);
    return tag;

  }

  /**
   * @param game
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws SAXException
   */
  public void loadXml(GameWorld game) throws ParserConfigurationException, SAXException, IOException {
    String filePath = "/home/hoongkevi/Desktop/game";
    File xml = new File(filePath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;
    try {
       db = dbFactory.newDocumentBuilder();
       Document doc = db.parse(xml);
       doc.getDocumentElement().normalize();
    }catch(ParserConfigurationException e) {
      System.out.println("ParserConfigurationException error");
    }catch(SAXException e) {
      System.out.println("SAX Exception error");
    }catch(IOException e) {
      System.out.println("File input/output error");
    }
  }
}
