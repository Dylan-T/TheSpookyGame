package gameworld;

/**
 * This is a single location in the game.
 * 
 * @author thomsodyla1
 *
 */
public class Location {
  Item[][] grid = new Item[4][4]; // 2D array of the positions the objects of the room are held
  Passage[] exits = new Passage[4]; // 0 - North; 1 - East; 2 - South; 3 - West

  enum Direction {
    NORTH, EAST, SOUTH, WEST;
  }
  
  /**
   * Removes the item from this location.
   * 
   * @param i The item to be removed from this room.
   * @return true if the item was found and removed.
   */
  public boolean removeItem(Item i) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col].equals(i)) {
          grid[row][col] = null;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if this location contains the given item.
   * @param i item to check for
   * @return whether it contains the item
   */
  public boolean containsItem(Item i) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col].equals(i)) {
          return true;
        }
      }
    }
    return false;
  }

}
