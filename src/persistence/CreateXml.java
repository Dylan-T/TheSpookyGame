package persistence;

import java.io.File;
import java.util.HashMap;

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

import gameworld.*;

/**
 * Creates an XML file which fits our games elements and description.
 * @author hoongkevi
 *
 */
public class CreateXml {
  private static final String filePath = "/home/hoongkevi/Desktop/game";


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

      Element player = document.createElement("Player");
      game.appendChild(player);

      Element inventory = document.createElement("Inventory");
      player.appendChild(inventory);

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
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

 public void makeXml(Game game) {
   DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
   DocumentBuilder db = df.newDocumentBuilder();
   Document document = db.newDocument();

   Element gamefile = document.createElement("Game");
   document.appendChild(gamefile);

   Element player = document.createElement("Player");
   gamefile.appendChild(player);

   Element inventory = document.createElement("Inventory");
   player.appendChild(inventory);

   for(Item i: gameworld.Player.items) {
       Attr temp = document.createAttribute(i.getName());
       inventory.setAttributeNode(temp);

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
