package gameworld;

import org.junit.Test;

import gameworld.GameWorld.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class for the game worlds actions.
 * @author Dylan
 *
 */
public class GameWorldTests {

//  /**
//   * Creates a test location containing items.
//   * @return a location containing items.
//   */
//  public static Location createTestLocation() {
//    Location loc = new Location(2, 2);
//    loc.addItem(0, 0, new Treasure("Test", "A test treasure item", 5));
//    return loc;
//  }
//
//  public static Item createTestItem() {
//
//  }


  //Location Tests

  /**
   * Test location.addItem for a valid case.
   */
  @Test
  public void testLocationAddItemValid() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    assertTrue(l.addItem(0, 0, t));
  }

  /**
   * Test location.addItem for a invalid case.
   */
  @Test
  public void testLocationAddItemInvalid_01() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    assertFalse(l.addItem(-1, -1, t));
  }

  /**
   * Test location.addItem for a invalid case.
   */
  @Test
  public void testLocationAddItemInvalid_02() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    assertFalse(l.addItem(3, 3, t));
  }




  /**
   * Test location.containsItem for true case.
   */
  @Test
  public void testLocationContainsTrue() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    l.addItem(0, 0, t);
    assertTrue(l.containsItem(t));
  }

  /**
   * Test location.containsItem for false case.
   */
  @Test
  public void testLocationContainsFalse_01() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    assertFalse(l.containsItem(t));
  }

  /**
   * Test location.containsItem for false case.
   */
  @Test
  public void testLocationContainsFalse_02() {
    Location l = new Location(2,2);
    Treasure t1 = new Treasure("Test1", "A test treasure item", "");
    Treasure t2 = new Treasure("Test2", "A different test item", "");
    l.addItem(0, 0, t2);
    assertFalse(l.containsItem(t1));
  }



  /**
   * Test location.removeItem for valid case.
   */
  @Test
  public void testLocationRemoveItemValid() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    l.addItem(0, 0, t);
    assertTrue(l.removeItem(t));
    assertFalse(l.containsItem(t));
  }

  /**
   * Test location.removeItem for invalid case.
   */
  @Test
  public void testLocationRemoveItemInvalid_01() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", "");
    assertFalse(l.removeItem(t));
    assertFalse(l.containsItem(t));
  }


  /**
   * Test location.removeItem for invalid case.
   */
  @Test
  public void testLocationRemoveItemInvalid_02() {
    Location l = new Location(2,2);
    Treasure t1 = new Treasure("Test1", "A test treasure item", "");
    Treasure t2 = new Treasure("Test2", "A different test item", "");
    l.addItem(0, 0, t2);
    assertFalse(l.removeItem(t1));
  }

//  /**
//   * Test location.addPassage for valid case.
//   */
//  @Test
//  public void testLocationAddPassageValid() {
//    Location l1 = new Location(2,2);
//    Location l2 = new Location(2,2);
//    Passage p = new Passage(l1,l2);
//    assertTrue(l1.addPassage(GameWorld.Direction.NORTH, p));
//    assertTrue(l1.getExits()[GameWorld.Direction.NORTH.ordinal()].equals(p));
//  }




//Item Tests
  /**
   * Test Treasure.getImage for valid case.
   */
  @Test
  public void testItemImage() {
    Treasure t = new Treasure("Test", "Test item", "assets/chest.png");
    assertTrue(t.getImage() != null);
  }

  /**
   * Test Treasure.toString behaves as expected.
   */
  @Test
  public void testItemString() {
    Item t = new Treasure("Test", "Test item", "assets/chest.png");
    assertEquals(t.getName(), "Test");
    assertEquals(t.getDescription(), "Test item");
    assertEquals(t.toString(), "Test: Test item");
    assertEquals(t.inspect(), "Test: Test item");
  }

  //Player Tests

  /**
   *
   */
  @Test
  public void testMovePlayer() {
    Location l1 = new Location(2,2);
    l1.addExit(Direction.NORTH, false);

    Location l2 = new Location(2,2);
    l2.addExit(Direction.SOUTH, false);

    Location[][] locations = new Location[2][1];
    locations[1][0] = l1;
    locations[0][0] = l2;
    GameWorld game = new GameWorld(locations, l1);
    game.movePlayer(Direction.NORTH);
    assertEquals(game.getCurrentRoom().toString() + l2.toString(),game.getCurrentRoom(), l2);
  }

}
