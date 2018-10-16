package gameworld;

import java.util.ArrayList;
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
  private List<Quest> quests = new ArrayList<Quest>();

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
   * Makes the player pickup the given item if possible. This removes the item
   * from the location and adds it to the players inventory.
   *
   * @param i
   *          the item to be picked up
   * @return return true if the item was successfully picked up
   */
  public boolean pickupItem(Item i) {
    return player.pickupItem(i);
  }

  /**
   * Makes the player drop the given item if possible. This removes the item from
   * the players inventory and adds it to the current location if there is space.
   *
   * @param i
   *          the item to be picked up
   * @return if the item was successfully picked up
   */
  public boolean dropItem(Item i) {
    return player.dropItem(i);
  }

  /**
   * Check if the game has been completed. This is true when all quests have been
   * completed.
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

  /**
   * Adds a quest to this game.
   * @param q the quest to be added to the game.
   */
  public void addQuest(Quest q) {
    quests.add(q);
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
   *
   * @return a collection of the games locations
   */
  public Location[][] getWorldMap() {
    return worldMap;
  }

  /**
   * Gets a List of the games current quests.
   *
   * @return a collection of the games quests to be completed
   */
  public List<Quest> getQuests() {
    return quests;
  }

  /**
   * Gets the player object of this GameWorld.
   *
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
    Location l1 = new Location(12, 12);
    l1.addExit(Direction.SOUTH, false);
    l1.addExit(Direction.EAST, false);
    l1.addItem(4, 0,
        new Decoration("Grave", "neck1", "assets/boneNecklace.png"));
    l1.addItem(8, 1, new Treasure("Grave", "femur", "assets/femur.png"));
    l1.addItem(15, 3, new Decoration("Grave", "scep", "assets/scepter.png"));
    l1.addItem(11, 3, new Treasure("Grave", "femur2", "assets/femur.png"));
    l1.addItem(1, 1, new Treasure("Grave", "neck2", "assets/boneNecklace.png"));

    Location l2 = new Location(12, 12);
    l2.addExit(Direction.WEST, false);
    l2.addExit(Direction.SOUTH, false);
    l2.addItem(10, 1, new Decoration("Crow", "A raggedy crow", "assets/evilOrb.png"));

    Location l3 = new Location(12, 12);
    l3.addExit(Direction.NORTH, false);
    l3.addExit(Direction.EAST, false);
    l3.addItem(10, 3, new Treasure("Golden Skull", "Ancient golden skull embellished with gems",
        "assets/chest.png"));

    Location l4 = new Location(12, 12);
    l4.addExit(Direction.WEST, false);
    l4.addExit(Direction.NORTH, false);
    l4.addItem(8, 2, new Treasure("Kanye's Donda chain", "A chain imbued with Chicago's energy",
        "assets/key.png"));

    // Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[1][0] = l2;
    locations[0][1] = l3;
    locations[1][1] = l4;
    return new GameWorld(locations, l1);
  }

  /**
   * This creates a game world that can be used to demo the game.
   * @return the created game world.
   */
  public static GameWorld finalDemoWorld() {
    Location l1 = new Location(12,12);
    l1.addExit(Direction.SOUTH, false);

    Location l2 = new Location(12,12);
    Treasure t1 = new Treasure("Scepter",
        "An evil scepter with a skull carved in it", "assets/scepter.png");
    l2.addItem(6, 6, t1);
    l2.addExit(Direction.SOUTH, false);

    Location l3 = new Location(12,12);
    StationaryContainer c1 = new StationaryContainer("Coffin",
        "A slightly ajar coffin", "assets/coffin.png");
    Treasure t2 = new Treasure("Femur", "A femur glowing with evil", "assets/femur.png");
    c1.addItem(t2);
    l3.addItem(6, 12, c1);
    l3.addExit(Direction.EAST, false);

    Location l4 = new Location(12,12);
    l4.addExit(Direction.NORTH, false);
    l4.addExit(Direction.EAST, false);
    l4.addExit(Direction.SOUTH, false);
    l4.addExit(Direction.WEST, false);

    Location l5 = new Location(12,12);
    Key k1 = new Key();
    l5.addItem(10, 10, k1);
    l5.addExit(Direction.WEST, false);

    Location l6 = new Location(12,12);
    Treasure t3 = new Treasure("Tooth Necklace",
        "A necklace made from human teeth.","assets/boneNecklace.png");
    l6.addItem(8, 11, t3);
    l6.addExit(Direction.NORTH, false);

    List<Treasure> required = new ArrayList<Treasure>();
    required.add(t1);
    required.add(t2);
    required.add(t3);
    Quest q1 = new Quest("Evil Orb", "An evil orb of souls that can "
        + "only be destroyed by collecting treasures", "", required);
    l1.addItem(12, 6, q1);

    Location[][] locations = new Location[3][4];
    locations[1][0] = l1;
    locations[1][1] = l2;
    locations[0][2] = l3;
    locations[1][2] = l4;
    locations[2][2] = l5;
    locations[1][3] = l6;

    GameWorld game = new GameWorld(locations, l4);
    game.addQuest(q1);
    //GameWorld
    return game;
  }
}
