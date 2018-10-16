package mapeditor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

/**
 * Test Class for Map Editor.
 * @author dunninnath
 *
 */
public class MapEditorTests {

  /**
   * Tests if the program can run with no errors.
   */
  @Test
  public void testRunnable() {
    MapEditor m = new MapEditor();
  }

  /**
   * Tests to see if items can be created with errors.
   */
  @Test
  public void testItemsToString() {
    Door d = new Door();
    Floor j = new Floor();
    Key k = new Key(true);
    assertTrue(k.checkPickupable());
    Location loc = new Location(5, 5, new TilePiece[5][5]);
    Quest q = new Quest(false);
    assertFalse(q.checkPickupable());
    Treasure t = new Treasure(true);
    assertTrue(t.checkPickupable());
    Wall w = new Wall();
    Decoration dec = new Decoration(false);
    assertFalse(dec.checkPickupable());
    assertTrue(d.toString().equals("door"));
    assertTrue(j.toString().equals("floor"));
    assertTrue(k.toString().equals("key"));
    assertTrue(q.toString().equals("quest"));
    assertTrue(t.toString().equals("treasure"));
    assertTrue(w.toString().equals("wall"));
    assertTrue(dec.toString().equals("decoration"));
  }

  /**
   * Tests to see if a room can be created with no errors.
   */
  @Test
  public void testRoom() {
    MapEditor m = new MapEditor();
    Room r = new Room();
    r.roomEditorSizeGui(m);
    r.roomEditor();
    r.objectSelector();
  }

  /**
   * Testing the popups work with no errors
   */
  @Test
  public void testPopups() {
    MapUtilGui.generatingXml();
    MapUtilGui.invalidArgumentError();
    MapUtilGui.invalidLocationError();
    MapUtilGui.invalidRoomInputError();
    MapUtilGui.noInstanceError();
    MapUtilGui.unfinishedMethodError();
  }
}
