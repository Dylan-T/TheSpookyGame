package persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import gameworld.*;
import mapeditor.*;
import renderer.*;
import application.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 *
 * Creates an XML file which fits our games elements and description.
 * @author hoongkevi
 *
 */
public class XmlSaver {
  private static final String filePath = "/home/hoongkevi/test.xml";


  /**
   * creates the XML file by taking game attributes and creating elements.
   * @param args main function.
   * @throws ParserConfigurationException parser exception.
   * @throws TransformerException transformer exception.
   */
//  public static void main(String[] args) throws ParserConfigurationException, TransformerException {
//    try {
//      DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
//      DocumentBuilder db = df.newDocumentBuilder();
//      Document document = db.newDocument();
//
//      //creates a game Element
//      Element game = document.createElement("Game");
//      document.appendChild(game);
//
//      //creates a locations Element which is appended to game
//      Element locations = document.createElement("Locations");
//      game.appendChild(locations);
//
//      //creates player element which is appended to game
//      Element player = document.createElement("Player");
//      game.appendChild(player);
//
//
//      //creates an inventory element which is appended to player
//      Element inventory = document.createElement("Inventory");
//      player.appendChild(inventory);
//
//      Element rooms = document.createElement("Rooms");
//      game.appendChild(rooms);
//
//      rooms.appendChild(makeRoom(0, 0, 0, 0, "Siglo_Balcony", document));
//
//
//
//      //creates player attributes and assigns them to player
//      Attr direction = document.createAttribute("Direction");
//      direction.setValue("NORTH");
//      player.setAttributeNode(direction);
//
//      Attr healthAttr = document.createAttribute("score");
//      healthAttr.setValue("0");
//      player.setAttributeNode(healthAttr);
//
//      Attr attr = document.createAttribute("health");
//      attr.setValue("100");
//      player.setAttributeNode(attr);
//
//      Attr attr1 = document.createAttribute("x");
//      attr1.setValue("0");
//      player.setAttributeNode(attr1);
//
//      Attr attr2 = document.createAttribute("y");
//      attr2.setValue("0");
//      player.setAttributeNode(attr2);
//
//
//
//      Element durries = document.createElement("durries");
//      inventory.appendChild(durries);
//
//      //Uses a transformer to stream the file into an XML file using DOMSource.
//      TransformerFactory tf = TransformerFactory.newInstance();
//      Transformer transformer = tf.newTransformer();
//      DOMSource ds = new DOMSource(document);
//      StreamResult sr = new StreamResult(new File(filePath));
//      transformer.transform(ds,sr);
//      System.out.println("XML File Created");
//    } catch (ParserConfigurationException e) {
//      System.out.print("parser configuration exception");
//    } catch (TransformerException e) {
//      System.out.println("Transformer exception");
//    }
//  }

  /**
   * This method takes in room data and creates elements and respective attributes and then adds them to the document.
   * @param width width of the room
   * @param height
   * @param x position of room
   * @param y position of room
   * @param name of room
   * @param doc the document where we are adding the room element
   * @return the new Element for room.
   */
  public static Element makeRoom(int width, int height, int x, int y, String name, Document doc) {
    Element room1 = doc.createElement(name);
    Attr width1 = doc.createAttribute("width");
    Attr height1 = doc.createAttribute("height");
    Attr x1 = doc.createAttribute("x");
    Attr y1 = doc.createAttribute("y");
    width1.setValue("0");
    height1.setValue("0");
    x1.setValue("0");
    y1.setValue("0");
    room1.setAttributeNode(width1);
    room1.setAttributeNode(height1);
    room1.setAttributeNode(x1);
    room1.setAttributeNode(y1);

    return room1;
  }

  /**
   * @param item
   * @param doc
   * @return new treasure element
   */
  public static Element makeTreasure(gameworld.Treasure item, Document doc) {
    Element treasure = doc.createElement("Treasure");
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    name.setValue(item.getName());
    description.setValue(item.getDescription());
    treasure.setAttributeNode(name);
    treasure.setAttributeNode(description);
    return treasure;
  }

  /**
   * @param container
   * @param doc
   * @return new container element
   */
  public static Element makeStationaryContainer(gameworld.StationaryContainer container, Document doc) {
    Element StationaryContainer = doc.createElement("StationaryContainer");
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Element contents = makeInventory(container.getContents(), doc);
    name.setValue(container.getName());
    description.setValue(container.getDescription());
    StationaryContainer.setAttributeNode(name);
    StationaryContainer.setAttributeNode(description);
    StationaryContainer.appendChild(contents);

    return StationaryContainer;
  }

  /**
   * @param quest
   * @param doc
   * @return new quest element
   */
  public static Element makeQuest(gameworld.Quest quest, Document doc) {
    Element questelem = doc.createElement("quest");
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Attr imagepath = doc.createAttribute("imagepath");
    Attr complete = doc.createAttribute("complete");
    Element requirements = makeQuestRequirements(quest.getRequirements(),doc);
    complete.setValue(quest.isComplete() + "");
    name.setValue(quest.getName());
    description.setValue(quest.getDescription());
    imagepath.setValue(quest.getImage().toString());
    questelem.setAttributeNode(name);
    questelem.setAttributeNode(description);
    questelem.setAttributeNode(imagepath);
    questelem.setAttributeNode(complete);
    questelem.appendChild(requirements);
    return questelem;
  }

  /**
   * @param location
   * @param doc
   * @return location element
   */
  public static Element makeLocation(gameworld.Location location, Document doc) {
    Element locationElem = doc.createElement("Location");
    Element grid = doc.createElement("grid");
    for(int i =0; i<location.getGrid().length; i++) {
      for(int j =0; j<location.getGrid()[0].length; j++) {
        if(location.getGrid()[i][j] instanceof Item) {
          Element GridItem = doc.createElement("GridItem");
          Attr name = doc.createAttribute("name");
          Attr description = doc.createAttribute("description");
          Attr x = doc.createAttribute("x");
          Attr y = doc.createAttribute("y");
          x.setValue(i + "");
          y.setValue(j + "");
          name.setValue(location.getGrid()[i][j].getName());
          description.setValue(location.getGrid()[i][j].getDescription());
          GridItem.setAttributeNode(name);
          GridItem.setAttributeNode(description);
          GridItem.setAttributeNode(x);
          GridItem.setAttributeNode(y);
          grid.appendChild(GridItem);
        }
      }
    }
    Element exits = doc.createElement("exits");
    for(int i=0; i<location.getExits().length; i++) {
      if(location.getExits()[i] != null) {
        Element exit = doc.createElement("exit");
        Attr exitLoc = doc.createAttribute("exitdirection");
        exitLoc.setValue(""+ i);
        exit.setAttributeNode(exitLoc);
        exits.appendChild(exit);
      }
    }
    Attr width = doc.createAttribute("width");
    Attr height = doc.createAttribute("height");
    width.setValue(location.getGrid().length + "");
    height.setValue(location.getGrid()[0].length + "");
    locationElem.setAttributeNode(width);
    locationElem.setAttributeNode(height);
    locationElem.appendChild(grid);
    locationElem.appendChild(exits);
    return locationElem;
  }

  /**
   * @param location
   * @param doc
   * @return location of player
   */
  public static Element makePlayerLocation(gameworld.Location location, Document doc) {
    Element locationElem = doc.createElement("PlayerLocation");
    Element grid = doc.createElement("grid");
    for(int i =0; i<location.getGrid().length; i++) {
      for(int j =0; j<location.getGrid()[0].length; j++) {
        if(location.getGrid()[i][j] instanceof Item) {
          Element GridItem = doc.createElement("GridItem");
          Attr name = doc.createAttribute("name");
          Attr description = doc.createAttribute("description");
          Attr x = doc.createAttribute("x");
          Attr y = doc.createAttribute("y");
          x.setValue(i + "");
          y.setValue(j + "");
          name.setValue(location.getGrid()[i][j].getName());
          description.setValue(location.getGrid()[i][j].getDescription());
          GridItem.setAttributeNode(name);
          GridItem.setAttributeNode(description);
          GridItem.setAttributeNode(x);
          GridItem.setAttributeNode(y);
          grid.appendChild(GridItem);
        }
      }
    }
    Element exits = doc.createElement("exits");
    for(int i=0; i<location.getExits().length; i++) {
      if(location.getExits()[i] != null) {
        Element exit = doc.createElement("exit");
        Attr exitLoc = doc.createAttribute("exitdirection");
        exitLoc.setValue(""+ i);
        exit.setAttributeNode(exitLoc);
        exits.appendChild(exit);
      }
    }
    Attr width = doc.createAttribute("width");
    Attr height = doc.createAttribute("height");
    width.setValue(location.getGrid().length + "");
    height.setValue(location.getGrid()[0].length + "");
    locationElem.setAttributeNode(width);
    locationElem.setAttributeNode(height);
    locationElem.appendChild(grid);
    locationElem.appendChild(exits);
    return locationElem;
  }

  /**
   * @param key
   * @param doc
   * @return new key element
   */
  public static Element makeKey(gameworld.Key key, Document doc) {
    Element keyElem = doc.createElement("key");
    Element unlocks = makePassage(key.getUnlocks(), doc);
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Attr image = doc.createAttribute("imagePath");
    name.setValue(key.getName());
    description.setValue(key.getDescription());
    image.setValue(key.getImage().toString());
    keyElem.setAttributeNode(name);
    keyElem.setAttributeNode(description);
    keyElem.setAttributeNode(image);
    keyElem.appendChild(unlocks);
    return keyElem;
  }

  /**
   * @param decoration
   * @param doc
   * @return new decor
   */
  public static Element makeDecoration(gameworld.Decoration decoration, Document doc) {
    Element decoElem = doc.createElement("decoration");
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Attr image = doc.createAttribute("imagePath");
    name.setValue(decoration.getName());
    description.setValue(decoration.getDescription());
    image.setValue(decoration.getImage().toString());
    decoElem.setAttributeNode(name);
    decoElem.setAttributeNode(description);
    decoElem.setAttributeNode(image);
    return decoElem;
  }

  /**
   * @param player
   * @param collection
   * @param doc
   * @return player inventory
   */
  public static Element makeInventory(Collection<Item> collection, Document doc) {
    Element Inventory = doc.createElement("Inventory");
    if(collection != null) {
    for(Item i : collection) {
      Element item = doc.createElement("Item");
      Attr name = doc.createAttribute("name");
      Attr description = doc.createAttribute("description");
      Attr image = doc.createAttribute("imagePath");
      Attr type = doc.createAttribute("type");
      name.setValue(i.getName());
      description.setValue(i.getDescription());
      image.setValue(i.getImage().toString());
      item.setAttributeNode(name);
      item.setAttributeNode(description);
      item.setAttributeNode(image);
      Inventory.appendChild(item);
    }
    }

    return Inventory;
  }

  /**
   * @param collection
   * @param doc
   * @return quest requirements
   */
  public static Element makeQuestRequirements(Collection<Treasure> collection, Document doc) {
    Element requirements = doc.createElement("Requirements");
    for(Item i : collection) {
      Element item = doc.createElement("QuestItem");
      Attr name = doc.createAttribute("name");
      Attr description = doc.createAttribute("description");
      Attr image = doc.createAttribute("imagePath");
      name.setValue(i.getName());
      description.setValue(i.getDescription());
      image.setValue(i.getImage().toString());
      item.setAttributeNode(name);
      item.setAttributeNode(description);
      item.setAttributeNode(image);
      requirements.appendChild(item);
    }

    return requirements;
  }

  /**
   * @param player
   * @param doc
   * @return new player ELement.
   */
  public static Element makePlayer(gameworld.Player player, Document doc) {
    Element Player = doc.createElement("Player");
    Element Inventory = makeInventory(player.getInventory(), doc);
    Player.appendChild(Inventory);
    Element Location = makePlayerLocation(player.getCurrentLocation(), doc);
    Attr facing = doc.createAttribute("facing");
    facing.setValue(player.getFacing().toString());
    Player.appendChild(Location);
    Player.setAttributeNode(facing);
    return Player;
  }

  /**
   * @param passage
   * @param doc
   * @return a new passage element
   */
  public static Element makePassage(gameworld.Passage passage, Document doc) {
    Element Passage = doc.createElement("passage");
    Element loc1 = makeLocation(passage.getLoc1(), doc);
    Element loc2 = makeLocation(passage.getLoc2(), doc);
    Attr blocked = doc.createAttribute("blocked");
    blocked.setValue(passage.isLocked() + "");
    Passage.appendChild(loc1);
    Passage.appendChild(loc2);
    Passage.setAttributeNode(blocked);
    return Passage;

  }

 /**
 * @param game
 * @throws ParserConfigurationException
 * @throws TransformerException
 */
public static void makeXml(GameWorld game) throws ParserConfigurationException, TransformerException {
   try {
   DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
   DocumentBuilder db = df.newDocumentBuilder();
   Document document = db.newDocument();

   Element gamefile = document.createElement("Game");
   document.appendChild(gamefile);

   Element player = makePlayer(game.getPlayer(),document);
   gamefile.appendChild(player);


   Element Quests = document.createElement("Quests");
   if(game.getQuests() != null) {
   for(Quest q: game.getQuests()) {
     Quests.appendChild(makeQuest(q,document));
   }
   }

   Element Locations = document.createElement("Locations");
   for(Location l:game.getLocations()) {
     Locations.appendChild(makeLocation(l,document));
   }

   gamefile.appendChild(player);
   gamefile.appendChild(Quests);
   gamefile.appendChild(Locations);

   TransformerFactory tf = TransformerFactory.newInstance();
   Transformer transformer = tf.newTransformer();
   DOMSource ds = new DOMSource(document);
   StreamResult sr = new StreamResult(new File(filePath));
   transformer.transform(ds,sr);
   System.out.println("XML File Created");
   }catch (ParserConfigurationException e) {
     System.out.print("parser configuration exception");
   }catch (TransformerException e) {
     System.out.println("Transformer exception");
   }



 }




  /**
   * gets the file path.
   * @return filepath
   */
  public static String getFilepath() {
    return filePath;
  }

}
