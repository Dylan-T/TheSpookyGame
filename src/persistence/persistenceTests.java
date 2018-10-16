package persistence;

import org.junit.Test;

import gameworld.GameWorld;
import gameworld.Location;
import gameworld.Player;
import gameworld.GameWorld.Direction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author hoongkevi
 *
 */
public class persistenceTests {

  /**
   * tests that the savefile works
   * @throws TransformerException
   * @throws ParserConfigurationException
   */
  @Test
  public void testsave() throws ParserConfigurationException, TransformerException{
    Player p = new Player(new Location(2, 2));
    //Make locations
    Location l1 = new Location(2, 2);
    Location l2 = new Location(2, 2);
    Location l3 = new Location(2, 2);
    Location l4 = new Location(2, 2);




    //Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    locations[1][1] = l4;
    GameWorld game = new GameWorld(locations, l1);
    XmlSaver.makeXml(game);
  }

}
