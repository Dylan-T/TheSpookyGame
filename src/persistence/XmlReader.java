package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    String filePath = "/home/hoongkevi/Desktop/game";
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
         elemList.add(getGameWorld(nodelist.item(i)));
       }
       NodeList playerNodeList = doc.getElementsByTagName("Player");
       List<Player> playerList = new ArrayList<Player>();
       for(int i =0; i< playerNodeList.getLength(); i++) {
         playerList.add(getPlayer(playerNodeList.item(i)));
       }
       NodeList PassageNodeList = doc.getElementsByTagName("Passage");
       List<Passage> PassageList = new ArrayList<Passage>();
       for(int i = 0; i< PassageNodeList.getLength(); i++) {
         PassageList.add(getPassage(PassageNodeList.item(i)));
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
   * @return GameWorld with the state of the game
   */
  public static GameWorld getGameWorld(Node node) {
    GameWorld game = new GameWorld(null,null);
    if(node.getNodeType() == Node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }

    return null;
  }

  /**
   * @param node
   * @return new container
   */
  public static StationaryContainer getContainer(Node node) {
    if(node.getNodeType() == node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
  }

  /**
   * @param node
   * @return new quest
   */
  public static Quest getQuest(Node node) {
    if(node.getNodeType() == node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
  }

  /**
   * @param node
   * @return new Location
   */
  public static Location getLocation(Node node) {
    if(node.getNodeType() == node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
  }

  /**
   * @param node
   * @return new treasure
   */
  public static Treasure getTreasure(Node node) {
    if(node.getNodeType() == node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
  }

  /**
   * @param node
   * @return player elements
   */
  public static Player getPlayer(Node node) {
    Player player = new Player(null);
    if(node.getNodeType() == Node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
  }

  /**
   * @param node
   * @return Passage elements
   */
  public static Passage getPassage(Node node) {
    Passage passage = new Passage(null, null);
    if(node.getNodeType() == node.ELEMENT_NODE) {
      Element elem = (Element) node;
    }
    return null;
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
