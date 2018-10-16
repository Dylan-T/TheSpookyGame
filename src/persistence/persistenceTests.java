package persistence;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;

import gameworld.GameWorld;
import gameworld.Location;

/**
 * @author hoongkevi
 *
 */
public class persistenceTests {

  /**
   * tests that the savefile works
   * 
   * @throws TransformerException
   * @throws ParserConfigurationException
   */
  @Test
  public void testsave() throws ParserConfigurationException, TransformerException {
    // Make locations
    Location l1 = new Location(2, 2);
    Location l2 = new Location(2, 2);
    Location l3 = new Location(2, 2);
    Location l4 = new Location(2, 2);

    // Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    locations[1][1] = l4;
    GameWorld game = new GameWorld(locations, l1);
    XmlSaver.makeXml(game, "game.xml");
  }

}
