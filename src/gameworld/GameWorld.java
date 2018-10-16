package gameworld;

import java.util.List;

/**
 * Wrapper class to hold the game world together and give the other packages a
 * simple class to communicate with. This is implementing a facade pattern.
 *
 * @author Dylan
 *
 */
public class GameWorld {
  private Location[][] worldMap;
  private int playerX;
  private int playerY;
  private Player player;
  private List<Quest> quests;

  /**
   * Enum for directions.
   *
   * @author Dylan
   *
   */
  public enum Direction {
    /**
     * North 0.
     */
    NORTH,

    /**
     * East 1.
     */
    EAST,

    /**
     * South 2.
     */
    SOUTH,

    /**
     * West 3.
     */
    WEST;
  }

  /**
   * Create a new world specifying all locations and the starting area.
   *
   * @param locations
   *          Games locations
   * @param start
   *          location the player starts from
   */
  public GameWorld(Location[][] locations, Location start) {
    worldMap = locations;
    player = new Player(start);
    for (int x = 0; x < worldMap.length; x++) {
      for (int y = 0; y < worldMap[0].length; y++) {
        if (worldMap[x][y].equals(start)) {
          playerX = x;
          playerY = y;
        }
      }
    }
  }

  /**
   * Moves the player in the given direction.
   *
   * @param dir
   *          to move in
   * @return True if the player successfully moved
   */
  public boolean movePlayer(Direction dir) {
    int direction = dir.ordinal() + player.getFacing().ordinal();
    if (direction >= 4) {
      direction = direction % 4;
    }
    Boolean exit = player.getCurrentLocation().getExits()[direction];
    if (exit != null && exit != true) {
      // Find new location with out of bounds tests
      switch (direction) {
        case 0:
          if (playerY == 0) {
            return false;
          }
          playerY--;
          break;
        case 1:
          if (playerX == worldMap.length - 1) {
            return false;
          }
          playerX++;
          break;
        case 2:
          if (playerY == worldMap[0].length - 1) {
            return false;
          }
          playerY++;
          break;
        case 3:
          if (playerX == 0) {
            return false;
          }
          playerX--;
          break;
        default:
          break;
      }

      Location newLoc = worldMap[playerX][playerY];
      if (newLoc != null) {
        player.move(newLoc, Direction.values()[direction]);
        System.out.println("We just moved :)");
        return true;
      }
    }

    return false;
  }

  /**
   * Unlock a passage in the given direction, using a key from your inventory.
   *
   * @param dir
   *          direction of the passage to unlock
   * @return whether a passage was successfully unlocked
   */
  public boolean unlockPassage(GameWorld.Direction dir) {
    Boolean exit = getCurrentRoom().exits[dir.ordinal()];
    if (exit != null && exit == true) { // check the exit exists and is locked
      // Check you have a key for the passage
      List<Item> inv = player.getInventory();
      for (Item i : inv) {
        if (i instanceof Key) {
          exit = false;
          inv.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Makes the player pickup the given item if possible.
   * This removes the item from the location and adds it to the players inventory.
   * @param i
   *          the item to be picked up
   * @return return true if the item was successfully picked up
   */
  public boolean pickupItem(Item i) {
    return player.pickupItem(i);
  }

  /**
   * Makes the player drop the given item if possible.
   * This removes the item from the players inventory and adds it to
   * the current location if there is space.
   * @param i
   *          the item to be picked up
   * @return if the item was successfully picked up
   */
  public boolean dropItem(Item i) {
    return player.dropItem(i);
  }

  /**
   * Check if the game has been completed. This is true when all quests have been completed.
   *
   * @return if the game has been complete
   */
  public boolean isGameComplete() {
    for (Quest q : quests) {
      if (!q.isComplete()) {
        return false;
      }
    }
    return true;
  }

  // Getters

  /**
   * Gets the players current location.
   *
   * @return the players current location
   */
  public Location getCurrentRoom() {
    return worldMap[playerX][playerY];
  }

  /**
   * Gets the 2D array of locations that is the GameWorld map.
   * @return a collection of the games locations
   */
  public Location[][] getWorldMap() {
    return worldMap;
  }

  /**
   * Gets a List of the games current quests.
   * @return a collection of the games quests to be completed
   */
  public List<Quest> getQuests() {
    return quests;
  }

  /**
   * Gets the player object of this GameWorld.
   * @return the games player object
   */
  public Player getPlayer() {
    return player;
  }

  // ~~~~ Test methods ~~~~~~~~~~~~

  /**
   * Creates an GameWorld with empty 4 rooms.
   *
   * @return the new GameWorld
   */
  public static GameWorld testGameWorld2() {
    // Make locations & add item's
    Location l1 = new Location(2, 2);
    l1.addExit(Direction.SOUTH, false);
    l1.addExit(Direction.EAST, false);
    l1.addItem(0, 0,
        new Decoration("Grave", "An ominous looking grave", "assets/boneNecklace.png"));
    l1.addItem(0, 1, new Treasure("Grave", "An ominous looking grave", "assets/femur.png"));
    l1.addItem(1, 0, new Decoration("Grave", "An ominous looking grave", "assets/scepter.png"));
    l1.addItem(1, 1, new Treasure("Grave", "An ominous looking grave", "assets/coffin.png"));

    Location l2 = new Location(2, 2);
    l2.addExit(Direction.WEST, false);
    l2.addExit(Direction.SOUTH, false);
    l2.addItem(0, 1, new Decoration("Crow", "A raggedy crow", "assets/evilOrb.png"));

    Location l3 = new Location(2, 2);
    l3.addExit(Direction.NORTH, false);
    l3.addExit(Direction.EAST, false);
    l3.addItem(1, 0, new Treasure("Golden Skull", "Ancient golden skull embellished with gems",
        "assets/chest.png"));

    Location l4 = new Location(2, 2);
    l4.addExit(Direction.WEST, false);
    l4.addExit(Direction.NORTH, false);
    l4.addItem(1, 1, new Treasure("Kanye's Donda chain", "A chain imbued with Chicago's energy",
        "assets/key.png"));

    // Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    locations[1][1] = l4;
    return new GameWorld(locations, l1);

  }
}
