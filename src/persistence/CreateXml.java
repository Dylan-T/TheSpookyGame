package persistence;

import java.io.File;

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

import gameworld.*;
import mapeditor.*;
import renderer.*;
import application.*;

/**
 * Creates an XML file which fits our games elements and description.
 * @author hoongkevi
 *
 */
public class CreateXml {
  private static final String filePath = "C:\\Users\\krarv\\Desktop\\game.xml";


  /**
   * creates the XML file by taking game attributes and creating elements.
   * @param args main function.
   * @throws ParserConfigurationException parser exception.
   * @throws TransformerException transformer exception.
   */
  public static void main(String[] args) throws ParserConfigurationException, TransformerException {
    try {
      DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = df.newDocumentBuilder();
      Document document = db.newDocument();

      Element game = document.createElement("Game");
      document.appendChild(game);
      
      Element locations = document.createElement("Locations");
      game.appendChild(locations);

      Element player = document.createElement("Player");
      game.appendChild(player);
      
      

      Element inventory = document.createElement("Inventory");
      player.appendChild(inventory);
      
      Attr direction = document.createAttribute("Direction");
      direction.setValue("NORTH");
      player.setAttributeNode(direction);
      
      Attr healthAttr = document.createAttribute("score");
      healthAttr.setValue("0");
      player.setAttributeNode(healthAttr);

      Attr attr = document.createAttribute("health");
      attr.setValue("100");
      player.setAttributeNode(attr);

      Attr attr1 = document.createAttribute("x");
      attr1.setValue("0");
      player.setAttributeNode(attr1);

      Attr attr2 = document.createAttribute("y");
      attr2.setValue("0");
      player.setAttributeNode(attr2);

      Attr attr3 = document.createAttribute("room");
      attr3.setValue("street");
      player.setAttributeNode(attr3);

      Element durries = document.createElement("durries");
      inventory.appendChild(durries);

      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      DOMSource ds = new DOMSource(document);
      StreamResult sr = new StreamResult(new File(filePath));
      transformer.transform(ds,sr);
      System.out.println("XML File Created");
    } catch (ParserConfigurationException e) {
      System.out.print("parser configuration exception");
    } catch (TransformerException e) {
      System.out.println("Transformer exception");
    }
  }

 /**
 * @param game
 * @throws ParserConfigurationException
 */
//public void makeXml(GameWorld game) throws ParserConfigurationException {
//   DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
//   DocumentBuilder db = df.newDocumentBuilder();
//   Document document = db.newDocument();
//
//   Element gamefile = document.createElement("Game");
//   document.appendChild(gamefile);
//
//   Element player = document.createElement("Player");
//   gamefile.appendChild(player);
//
//   Element inventory = document.createElement("Inventory");
//   player.appendChild(inventory);

//   for(Item i: gameworld.Player.inventory) {
//       Attr temp = document.createAttribute(i.getName());
//       inventory.setAttributeNode(temp);
//
//   }
// }
 
 


  /**
   * gets the file path.
   * @return filepath
   */
  public static String getFilepath() {
    return filePath;
  }

}
