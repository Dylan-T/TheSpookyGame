package persistence;

import gameworld.GameWorld;
import gameworld.Item;
import gameworld.Location;
import gameworld.Quest;
import gameworld.Treasure;

import java.io.File;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 * XmlSaver is the saver class for the persistence package,
 * the main method takes in a GameWorld parameter and copies all the
 * information in the game world using XML.
 * @author hoongkevi
 *
 */
public class XmlSaver {
  private static final String filePath = "/home/hoongkevi/test.xml";

  /**
   * This is the main method of MakeXml which takes in a game and a filepath and then reads all
   * the game data to an Xml file and saves it into filepath.
   * @param game the gameworld data we are saving.
   * @param filepath the filepath where we should save to.
   * @throws ParserConfigurationException indicates a configuration error.
   * @throws TransformerException indicates a Transformer error.
   */
  public static void makeXml(GameWorld game, String filepath)
      throws ParserConfigurationException, TransformerException {
    try {
      DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = df.newDocumentBuilder();
      Document document = db.newDocument();

      Element gamefile = document.createElement("Game");
      document.appendChild(gamefile);
      Attr width = document.createAttribute("width");
      Attr height = document.createAttribute("height");
      width.setValue(game.getWorldMap().length + "");
      height.setValue(game.getWorldMap()[0].length + "");
      gamefile.setAttributeNode(width);
      gamefile.setAttributeNode(height);
      Element player = makePlayer(game.getPlayer(), document);
      gamefile.appendChild(player);

      Element quests = document.createElement("Quests");
      if (game.getQuests() != null) {
        for (Quest q : game.getQuests()) {
          quests.appendChild(makeQuest(q, document));
        }
      }

      Element locations = document.createElement("Locations");
      Location[][] worldmap = game.getWorldMap();
      for (int i = 0; i < worldmap.length; i++) {
        for (int j = 0; j < worldmap[0].length; j++) {
          locations.appendChild(makeLocation(worldmap[i][j], document));
        }
      }

      gamefile.appendChild(player);
      gamefile.appendChild(quests);
      gamefile.appendChild(locations);

      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      DOMSource ds = new DOMSource(document);
      StreamResult sr = new StreamResult(new File(filepath));
      transformer.transform(ds, sr);
      System.out.println("XML File Created");
    } catch (ParserConfigurationException e) {
      System.out.print("parser configuration exception");
    } catch (TransformerException e) {
      System.out.println("Transformer exception");
    }

  }

  /**
   * This method takes in room data and creates elements and respective attributes
   * and then adds them to the document.
   *
   * @param width
   *          width of the room
   * @param height
   *          height of the room
   * @param x
   *          position of room
   * @param y
   *          position of room
   * @param name
   *          of room
   * @param doc
   *          the document where we are adding the room element
   * @return the new Element for room.
   */
  public static Element makeRoom(int width, int height, int x, int y, String name, Document doc) {
    Attr width1 = doc.createAttribute("width");
    Attr height1 = doc.createAttribute("height");
    Attr x1 = doc.createAttribute("x");
    width1.setValue("0");
    height1.setValue("0");
    x1.setValue("0");
    Attr y1 = doc.createAttribute("y");
    y1.setValue("0");
    Element room1 = doc.createElement(name);
    room1.setAttributeNode(width1);
    room1.setAttributeNode(height1);
    room1.setAttributeNode(x1);
    room1.setAttributeNode(y1);

    return room1;
  }

  /**
   * creates a new element which stores data from the item passed as a parameter.
   * @param item Treasure that we are converting to Xml
   * @param doc the xml Document we are writing to
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
   * Creates a XML Element to store StationaryContainers.
   * @param container the container we are converting to xml
   * @param doc the document we are writing to
   * @return new container element
   */
  public static Element makeStationaryContainer(gameworld.StationaryContainer container,
      Document doc) {
    Element stationaryContainer = doc.createElement("StationaryContainer");
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    name.setValue(container.getName());
    description.setValue(container.getDescription());
    stationaryContainer.setAttributeNode(name);
    stationaryContainer.setAttributeNode(description);
    Element contents = makeInventory(container.getContents(), doc);
    stationaryContainer.appendChild(contents);

    return stationaryContainer;
  }

  /**
   * creates and returns an XML element which stores Quest data.
   * @param quest the Quest item which we are converting to Xml Element
   * @param doc the document we are writing to.
   * @return new quest element
   */
  public static Element makeQuest(gameworld.Quest quest, Document doc) {
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Attr complete = doc.createAttribute("complete");
    complete.setValue(quest.isComplete() + "");
    name.setValue(quest.getName());
    description.setValue(quest.getDescription());
    Attr imagepath = doc.createAttribute("imagepath");
    imagepath.setValue(quest.getImage().toString());
    Element questelem = doc.createElement("quest");
    questelem.setAttributeNode(name);
    questelem.setAttributeNode(description);
    questelem.setAttributeNode(imagepath);
    questelem.setAttributeNode(complete);
    Element requirements = makeQuestRequirements(quest.getRequirements(), doc);
    questelem.appendChild(requirements);
    return questelem;
  }

  /**
   * Takes in a gameworld location and creates xml elements for the location, Grid and exits.
   * @param location the gameworld location
   * @param doc document we are writing to
   * @return location element
   */
  public static Element makeLocation(gameworld.Location location, Document doc) {
    Element grid = doc.createElement("grid");
    for (int i = 0; i < location.getGrid().length; i++) {
      for (int j = 0; j < location.getGrid()[0].length; j++) {
        if (location.getGrid()[i][j] instanceof Item) {
          Attr type = doc.createAttribute("type");
          Attr x = doc.createAttribute("x");
          Attr y = doc.createAttribute("y");
          if (location.getGrid()[i][j] instanceof Treasure) {
            type.setValue("treasure");
          } else if (location.getGrid()[i][j] instanceof gameworld.Key) {
            type.setValue("key");
          }

          x.setValue(i + "");
          y.setValue(j + "");
          Attr name = doc.createAttribute("name");
          name.setValue(location.getGrid()[i][j].getName());
          Attr description = doc.createAttribute("description");
          description.setValue(location.getGrid()[i][j].getDescription());
          Element gridItem = doc.createElement("GridItem");
          gridItem.setAttributeNode(name);
          gridItem.setAttributeNode(type);
          gridItem.setAttributeNode(description);
          gridItem.setAttributeNode(x);
          gridItem.setAttributeNode(y);
          grid.appendChild(gridItem);
        }
      }
    }
    Attr gridwidth = doc.createAttribute("gridwidth");
    Attr gridheight = doc.createAttribute("gridheight");
    gridwidth.setValue(location.getGrid().length + "");
    gridheight.setValue(location.getGrid()[0].length + "");
    grid.setAttributeNode(gridwidth);
    grid.setAttributeNode(gridheight);
    Element exits = doc.createElement("exits");
    for (int i = 0; i < location.getExits().length; i++) {
      if (location.getExits()[i] != null) {
        Element exit = doc.createElement("exit");
        Attr exitLoc = doc.createAttribute("exitdirection");
        Attr size = doc.createAttribute("size");
        size.setValue(location.getExits().length + "");
        exitLoc.setValue("" + i);
        exit.setAttributeNode(exitLoc);
        exits.appendChild(exit);
      }
    }
    Attr size = doc.createAttribute("size");
    size.setValue(location.getExits().length + "");
    exits.setAttributeNode(size);
    Attr width = doc.createAttribute("width");
    Attr height = doc.createAttribute("height");
    width.setValue(location.getGrid().length + "");
    height.setValue(location.getGrid()[0].length + "");
    Element locationElem = doc.createElement("Location");
    locationElem.setAttributeNode(width);
    locationElem.setAttributeNode(height);
    locationElem.appendChild(grid);
    locationElem.appendChild(exits);
    return locationElem;
  }

  /**
   * takes in the players currentLocation and returns the element to be saved under player location.
   * @param location players current location
   * @param doc the document we are writing to
   * @return location of player
   */
  public static Element makePlayerLocation(gameworld.Location location, Document doc) {
    Element grid = doc.createElement("grid");
    for (int i = 0; i < location.getGrid().length; i++) {
      for (int j = 0; j < location.getGrid()[0].length; j++) {
        if (location.getGrid()[i][j] instanceof Item) {
          Attr name = doc.createAttribute("name");
          Attr x = doc.createAttribute("x");
          Attr y = doc.createAttribute("y");
          x.setValue(i + "");
          y.setValue(j + "");
          name.setValue(location.getGrid()[i][j].getName());
          Attr description = doc.createAttribute("description");
          description.setValue(location.getGrid()[i][j].getDescription());
          Element gridItem = doc.createElement("GridItem");
          gridItem.setAttributeNode(name);
          gridItem.setAttributeNode(description);
          gridItem.setAttributeNode(x);
          gridItem.setAttributeNode(y);
          grid.appendChild(gridItem);
        }
      }
    }
    Element exits = doc.createElement("exits");
    for (int i = 0; i < location.getExits().length; i++) {
      if (location.getExits()[i] != null) {
        Element exit = doc.createElement("exit");
        Attr exitLoc = doc.createAttribute("exitdirection");
        exitLoc.setValue("" + i);
        exit.setAttributeNode(exitLoc);
        exits.appendChild(exit);
      }
    }
    Attr width = doc.createAttribute("width");
    Attr height = doc.createAttribute("height");
    width.setValue(location.getGrid().length + "");
    height.setValue(location.getGrid()[0].length + "");
    Element locationElem = doc.createElement("PlayerLocation");
    locationElem.setAttributeNode(width);
    locationElem.setAttributeNode(height);
    locationElem.appendChild(grid);
    locationElem.appendChild(exits);
    return locationElem;
  }



  /**
   * Creates new Element and copies decorations data to Xml format.
   * @param decoration the decoration we are saving
   * @param doc the document we are writing to
   * @return new decoration element
   */
  public static Element makeDecoration(gameworld.Decoration decoration, Document doc) {
    Attr name = doc.createAttribute("name");
    Attr description = doc.createAttribute("description");
    Attr image = doc.createAttribute("imagePath");
    name.setValue(decoration.getName());
    description.setValue(decoration.getDescription());
    image.setValue(decoration.getImage().toString());
    Element decoElem = doc.createElement("decoration");
    decoElem.setAttributeNode(name);
    decoElem.setAttributeNode(description);
    decoElem.setAttributeNode(image);
    return decoElem;
  }

  /**
   * Takes in a collection of Items and adds them all as an element inside of Inventory.
   * @param collection the collection of items
   * @param doc document we are writing to
   * @return Element of players inventory
   */
  public static Element makeInventory(Collection<Item> collection, Document doc) {
    Element inventory = doc.createElement("Inventory");
    if (collection != null) {
      for (Item i : collection) {
        Attr name = doc.createAttribute("name");
        Attr description = doc.createAttribute("description");
        Attr type = doc.createAttribute("type");
        if (i instanceof Treasure) {
          type.setValue("treasure");
        } else if (i instanceof gameworld.Key) {
          type.setValue("key");
        }
        name.setValue(i.getName());
        description.setValue(i.getDescription());
        Attr image = doc.createAttribute("imagePath");
        image.setValue(i.getImage().toString());
        Element item = doc.createElement("Item");
        item.setAttributeNode(name);
        item.setAttributeNode(description);
        item.setAttributeNode(image);
        item.setAttributeNode(type);
        inventory.appendChild(item);
      }
    }

    return inventory;
  }

  /**
   * Takes in a collection of treasure objects from a Quest and converts it to an xml Element.
   * @param collection the collection of Treasure requirements
   * @param doc the document we are writing to
   * @return quest requirements element
   */
  public static Element makeQuestRequirements(Collection<Treasure> collection, Document doc) {
    Element requirements = doc.createElement("Requirements");
    for (Item i : collection) {
      Attr name = doc.createAttribute("name");
      Attr description = doc.createAttribute("description");
      Attr type = doc.createAttribute("type");
      type.setValue("treasure");
      name.setValue(i.getName());
      description.setValue(i.getDescription());
      Attr image = doc.createAttribute("imagePath");
      image.setValue(i.getImage().toString());
      Element item = doc.createElement("QuestItem");
      item.setAttributeNode(type);
      item.setAttributeNode(name);
      item.setAttributeNode(description);
      item.setAttributeNode(image);
      requirements.appendChild(item);
    }

    return requirements;
  }

  /**
   * Creates an XML element from a game world player object.
   * @param player the player in the game.
   * @param doc the document we are wriing to
   * @return new player ELement.
   */
  public static Element makePlayer(gameworld.Player player, Document doc) {
    Element inventory = makeInventory(player.getInventory(), doc);
    Element playerelem = doc.createElement("Player");
    playerelem.appendChild(inventory);
    Element location = makePlayerLocation(player.getCurrentLocation(), doc);
    Attr facing = doc.createAttribute("facing");
    facing.setValue(player.getFacing().toString());
    playerelem.appendChild(location);
    playerelem.setAttributeNode(facing);
    return playerelem;
  }

  /**
   * gets the file path.
   *
   * @return filepath
   */
  public static String getFilepath() {
    return filePath;
  }

}
