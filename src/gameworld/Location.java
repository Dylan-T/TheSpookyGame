package gameworld;

/**
 * This is a single location in the game.
 *
 * @author thomsodyla1
 *
 */
public class Location {
  Item[][] grid; // 2D array of the positions the objects of the room are held
  /**
   * 0 - North; 1 - East; 2 - South; 3 - West
   * true - locked
   * false - unlocked
   * null - no exit
   */
  Boolean[] exits;

  /**
   * Creates a new room specifying the contents of each tile and the passages.
   * @param exits
   * @param tiles The contents of each tile, null if the tiles empty (This also specifies the room size)
   *
   */
  public Location(Boolean[] exits , Item[][] tiles) {
    this.exits = exits;
    grid = tiles;
  }


  /**
   * Creates a new location specifying only the height and width.
   * @param height of the location
   * @param width of the location
   */
  public Location(int height, int width) {
    grid = new Item[height][width];
    exits = new Boolean[4];
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
    if (x > grid[0].length || y > grid.length || x < 0 || y < 0) {
      return false;
    } else {
      grid[x][y] = item;
      return true;
    }
  }

  /**
   * Adds the item to the first available tile in the room.
   * @param i the item to be added to the room
   * @return true if the item was successfully added
   */
  public boolean addItem(Item i) {
    for(int j = 0; j < grid.length; j++) {
      for(int k = 0; k < grid[0].length; k++) {
        if (grid[j][k] == null) {
          grid[j][k] = i;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param dir , the wall to add the passage to.
   * @param locked true if the exit is locked
   * @return true if the passage was successfully added
   */
  public boolean addExit(GameWorld.Direction dir, Boolean locked) {
    if(exits[dir.ordinal()] != null || locked == null) {
      return false;
    }

    exits[dir.ordinal()] = locked;
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
        if(grid[row][col] != null) {
          if (grid[row][col].equals(i)) {
            grid[row][col] = null;
            return true;
          }
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
        if(grid[row][col] != null) {
          if (grid[row][col].equals(item)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  /**
   * Check if there is an empty tile in this location.
   * @return if the locations grid is full
   */
  public boolean isFull() {
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  //Getters

  /**
   * @return the 2D array containing the items
   */
  public Item[][] getGrid() {
    return grid;
  }

  /**
   * @return the array containing the exits
   */
  public Boolean[] getExits() {
    return exits;
  }
}
