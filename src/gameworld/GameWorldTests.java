package gameworld;

import org.junit.Test;

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
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    assertTrue(l.addItem(0, 0, t));
  }
  
  /**
   * Test location.addItem for a invalid case.
   */
  @Test
  public void testLocationAddItemInvalid_01() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    assertFalse(l.addItem(-1, -1, t));
  }
  
  /**
   * Test location.addItem for a invalid case.
   */
  @Test
  public void testLocationAddItemInvalid_02() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    assertFalse(l.addItem(3, 3, t));
  }
  
  
  
  
  /**
   * Test location.containsItem for true case.
   */
  @Test
  public void testLocationContainsTrue() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    l.addItem(0, 0, t);
    assertTrue(l.containsItem(t));
  }
  
  /**
   * Test location.containsItem for false case.
   */
  @Test
  public void testLocationContainsFalse_01() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    assertFalse(l.containsItem(t));
  }
  
  /**
   * Test location.containsItem for false case.
   */
  @Test
  public void testLocationContainsFalse_02() {
    Location l = new Location(2,2);
    Treasure t1 = new Treasure("Test1", "A test treasure item", 5);
    Treasure t2 = new Treasure("Test2", "A different test item", 5);
    l.addItem(0, 0, t2);
    assertFalse(l.containsItem(t1));
  }
  
  
  
  /**
   * Test location.removeItem for valid case.
   */
  @Test
  public void testLocationRemoveItemValid() {
    Location l = new Location(2,2);
    Treasure t = new Treasure("Test", "A test treasure item", 5);
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
    Treasure t = new Treasure("Test", "A test treasure item", 5);
    assertFalse(l.removeItem(t));
    assertFalse(l.containsItem(t));
  }
  
  
  /**
   * Test location.removeItem for invalid case.
   */
  @Test
  public void testLocationRemoveItemInvalid_02() {
    Location l = new Location(2,2);
    Treasure t1 = new Treasure("Test1", "A test treasure item", 5);
    Treasure t2 = new Treasure("Test2", "A different test item", 5);
    l.addItem(0, 0, t2);
    assertFalse(l.removeItem(t1));
  }
  
  


//Item Tests
}
