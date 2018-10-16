package persistence;

import gameworld.GameWorld;
import gameworld.Location;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;


/**
 * Tests for save and load in persistence package.
 * @author hoongkevi
 *
 */
public class PersistenceTests {

  /**
   * tests that the savefile works.
   *
   * @throws TransformerException exception if transformer fails to create xml doc
   * @throws ParserConfigurationException configuration error
   */
  @Test
  public void testsave1() throws ParserConfigurationException, TransformerException {
    // Make locations
    Location l1 = new Location(2, 2);
    Location l2 = new Location(2, 2);
    Location l3 = new Location(2, 2);
    // Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    Location l4 = new Location(2, 2);
    locations[1][1] = l4;
    GameWorld game = new GameWorld(locations, l1);
    XmlSaver.makeXml(game, "game.xml");
  }
  
  /**
   * tests that the save works for a fully implemented game
   * @throws ParserConfigurationException
   * @throws TransformerException
   * @throws IOException 
   * @throws SAXException 
   */
  @Test
  public void testsave2() throws ParserConfigurationException, TransformerException, SAXException, IOException {
    GameWorld game = XmlReader.loadXml("game.xml");
    XmlSaver.makeXml(game, "game.xml");
  }

}
