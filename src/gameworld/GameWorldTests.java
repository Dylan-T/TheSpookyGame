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

  //Game Tests

  /**
   * Test a valid player movement
   */
  @Test
  public void testMovePlayerValid() {
    Location l1 = new Location(2, 2);
    l1.addExit(Direction.SOUTH, false);
    l1.addExit(Direction.EAST, false);

    Location l2 = new Location(2, 2);
    l2.addExit(Direction.WEST, false);
    l2.addExit(Direction.SOUTH, false);

    Location l3 = new Location(2, 2);
    l3.addExit(Direction.NORTH, false);
    l3.addExit(Direction.EAST, false);

    Location l4 = new Location(2, 2);
    l4.addExit(Direction.WEST, false);
    l4.addExit(Direction.NORTH, false);


    //Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    locations[1][1] = l4;
    GameWorld game = new GameWorld(locations,l3);

    game.movePlayer(Direction.NORTH);
    assertEquals(game.getCurrentRoom(), l1);
    assertEquals(game.getPlayer().getFacing(), Direction.NORTH);

    game.movePlayer(Direction.EAST);
    assertEquals(game.getCurrentRoom(), l2);
    assertEquals(game.getPlayer().getFacing(), Direction.EAST);

    game.movePlayer(Direction.SOUTH);
    assertEquals(game.getCurrentRoom(), l1);
    assertEquals(game.getPlayer().getFacing(), Direction.WEST);

    game.movePlayer(Direction.WEST);
    assertEquals(game.getCurrentRoom(), l3);
    assertEquals(game.getPlayer().getFacing(), Direction.SOUTH);



  }

  /**
   * Test a valid player movement when exit  is locked
   */
  @Test
  public void testMovePlayerInvalid_01() {
    Location l1 = new Location(2,2);
    l1.addExit(Direction.NORTH, true);

    Location l2 = new Location(2,2);
    l2.addExit(Direction.SOUTH, true);

    Location[][] locations = new Location[1][2];
    locations[0][1] = l1;
    locations[0][0] = l2;
    GameWorld game = new GameWorld(locations, l1);
    game.movePlayer(Direction.NORTH);
    assertEquals(game.getCurrentRoom(), l1);
  }

  /**
   * Test a valid player movement there is no exit
   */
  @Test
  public void testMovePlayerInvalid_02() {
    Location l1 = new Location(2,2);

    Location l2 = new Location(2,2);
    l2.addExit(Direction.SOUTH, true);

    Location[][] locations = new Location[1][2];
    locations[0][1] = l1;
    locations[0][0] = l2;
    GameWorld game = new GameWorld(locations, l1);
    game.movePlayer(Direction.NORTH);
    assertEquals(game.getCurrentRoom(), l1);
  }

  //Player tests

  /**
   * Tests the player can pickup a valid item.
   */
  @Test
  public void testPickupItemValid() {
    Location l1  = new Location(1,1);
    Treasure t = new Treasure("", "", "assets/coffin.png");
    l1.addItem(t);
    Location[][] locations = new Location[][]{{l1}};
    GameWorld game = new GameWorld(locations, l1);
    assertTrue(game.pickupItem(t));
    assertTrue(game.getPlayer().getInventory().contains(t));
    assertFalse(l1.containsItem(t));
  }


  /**
   * Tests the player can pickup a valid item.
   */
  @Test
  public void testDropItemValid() {
    Location l1  = new Location(1,1);
    Treasure t = new Treasure("", "", "assets/coffin.png");
    Location[][] locations = new Location[][]{{l1}};
    GameWorld game = new GameWorld(locations, l1);
    game.getPlayer().getInventory().add(t);
    assertTrue(game.dropItem(t));
    assertFalse(game.getPlayer().getInventory().contains(t));
    assertTrue(l1.containsItem(t));
  }

}
