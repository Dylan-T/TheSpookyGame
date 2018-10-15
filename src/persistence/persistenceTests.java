package persistence;

import org.junit.Test;

import gameworld.GameWorld;
import gameworld.Location;
import gameworld.Passage;
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
    //Make locations
    Location l1 = new Location(2, 2);
    Location l2 = new Location(2, 2);
    Location l3 = new Location(2, 2);
    Location l4 = new Location(2, 2);


    //Make passages
    Passage p1 = new Passage(l1,l2);
    Passage p2 = new Passage(l1,l3);
    Passage p3 = new Passage(l2,l4);
    Passage p4 = new Passage(l3,l4);

    //Connect locations using passages
    l1.addPassage(Direction.EAST, p1);
    l2.addPassage(Direction.WEST, p1);

    l1.addPassage(Direction.SOUTH, p2);
    l3.addPassage(Direction.NORTH, p2);

    l2.addPassage(Direction.SOUTH, p3);
    l4.addPassage(Direction.NORTH, p3);

    l3.addPassage(Direction.EAST, p4);
    l4.addPassage(Direction.WEST, p4);

    //Add locations to Collection
    Set<Location> locations = new HashSet<Location>();
    locations.add(l1);
    locations.add(l2);
    locations.add(l3);
    locations.add(l4);
    GameWorld game = new GameWorld(locations, l1);
    XmlSaver.makeXml(game);
  }

}
