package gameworld;

/**
 * This is a single location in the game.
 *
 * @author thomsodyla1
 *
 */
public class Location {
  Item[][] grid; // 2D array of the positions the objects of the room are held
  Passage[] exits; // 0 - North; 1 - East; 2 - South; 3 - West

  /**
   * Creates a new room specifying the contents of each tile and the passages.
   * @param p Passages leaving the location, null if there isn't one 0-North;1-East...
   * @param tiles The contents of each tile, null if the tiles empty (This also specifies the room size)
   *
   */
  public Location(Passage[] p , Item[][] tiles) {
    exits = p;
    grid = tiles;
  }


  /**
   * Creates a new location specifying only the height and width.
   * @param height of the location
   * @param width of the location
   */
  public Location(int height, int width) {
    grid = new Item[height][width];
    exits = new Passage[4];
  }
  
  /**
   * @return the 2D array containing the items
   */
  public Item[][] getGrid() {
    return grid;
  }
  
  /**
   * @return the array containing the exits
   */
  public Passage[] getExits() {
    return exits;
  }

  /**
   * This method adds the given item to the specified x & y position.
   * If the coordinate is not within the location it returns false.
   * @param x coordinate to add item to
   * @param y coordinate to add item to
   * @param item to be added
   * @return whether the item was successfully added
   */
  public boolean addItem(int x, int y, Item item) {
    if (x > grid[0].length || y > grid.length) {
      return false;
    } else {
      grid[x][y] = item;
      return true;
    }
  }
  
  /**
   * @param dir , the wall to add the passage to.
   * @param p Passage to add to the location
   * @return true if the passage was successfully added
   */
  public boolean addPassage(GameWorld.Direction dir, Passage p) {
    //if(exits[dir.ordinal()] != null) return false;
    
    exits[dir.ordinal()] = p;
    return true;
    
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
   * @param item to check for
   * @return whether it contains the item
   */
  public boolean containsItem(Item item) {
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col].equals(item)) {
          return true;
        }
      }
    }
    return false;
  }

}
