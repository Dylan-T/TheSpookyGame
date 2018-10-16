package gameworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Wrapper class to hold the game world together and give the other packages a simple class to communicate with.
 * This is implementing a facade pattern.
 * @author Dylan
 *
 */
public class GameWorld {
  private Location[][] worldMap;
  private int playerX;
  private int playerY;
  private Player player;
  private Set<Quest> quests;

  /**
   * Enum for directions.
   * @author Dylan
   *
   */
  public enum Direction {
    /**
     * North 0
     */
    NORTH,

    /**
     * East 1
     */
    EAST,

    /**
     * South 2
     */
    SOUTH,

    /**
     * West 3
     */
    WEST;
  }

  /**
   * Create a new world specifying all locations and the starting area.
   * @param locations Games locations
   * @param start location the player starts from
   */
  public GameWorld(Location[][] locations, Location start) {
    worldMap = locations;
    player = new Player(start);
    for(int i = 0; i < worldMap.length; i++) {
      for(int j = 0; j < worldMap[0].length; j++) {
        if(worldMap[i][j].equals(start)) {
          playerX = j;
          playerY = i;
        }
      }
    }
  }


  /**
   * Moves the player in the given direction.
   * @param dir to move in
   * @return True if the player successfully moved
   */
  public boolean movePlayer(Direction dir) {
    int direction = dir.ordinal() + player.getFacing().ordinal();
    if (direction >= 4) {
      direction = direction % 4;
    }
    Boolean exit = player.getCurrentLocation().getExits()[direction];
    if (exit != null && exit != true) {
      //Find new location with out of bounds tests
      switch (direction) {
        case 0:
          if(playerY == 0) {
            return false;
          }
          playerY--;
          break;
        case 1:
          if(playerX == worldMap[0].length-1) {
            return false;
          }
          playerX++;
          break;
        case 2:
          if(playerY == worldMap.length-1) {
            return false;
          }
          playerY++;
          break;
        case 3:
          if(playerX == 0) {
            return false;
          }
          playerX--;
          break;
      }

      Location newLoc = worldMap[playerY][playerX];
      if (newLoc != null) {
        player.move(newLoc, dir);
        System.out.println("We just moved :)");
        return true;
      }
    }

    return false;
  }

  /**
   * Check if the game has been completed
   * @return if the game has been complete
   */
  public boolean isGameComplete() {
    for(Quest q: quests) {
      if (!q.isComplete()) {
        return false;
      }
    }
    return true;
  }

  //Getters

  /**
   * Gets the players current location.
   * @return the players current location
   */
  public Location getCurrentRoom() {
    return worldMap[playerY][playerX];
  }

  /**
   * @return a collection of the games locations
   */
  public Location[][] getWorldMap(){
    return worldMap;
  }

  /**
   * @return a collection of the games quests to be completed
   */
  public Collection<Quest> getQuests(){
    return quests;
  }

  /**
   * @return the games player object
   */
  public Player getPlayer() {
    return player;
  }




  //~~~~ Test methods ~~~~~~~~~~~~

  /**
   * Creates an GameWorld with empty 4 rooms.
   * @return the new GameWorld
   */
  public static GameWorld testGameWorld1() {
    //Make locations
    Location l1 = new Location(2, 2);
    Location l2 = new Location(2, 2);
    Location l3 = new Location(2, 2);
    Location l4 = new Location(2, 2);

    //Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[0][1] = l2;
    locations[1][0] = l3;
    locations[1][1] = l4;
    return new GameWorld(locations, l1);
  }

  /**
   * Creates an GameWorld with empty 4 rooms.
   * @return the new GameWorld
   */
  public static GameWorld testGameWorld2() {
    //Make locations & add item's
    Location l1 = new Location(2, 2);
    l1.addExit(Direction.SOUTH, false);
    l1.addExit(Direction.EAST, false);
    l1.addItem(0, 0, new Decoration("Grave","An ominous looking grave", "assets/boneNecklace.png"));
    l1.addItem(0, 1, new Treasure("Grave","An ominous looking grave", "assets/femur.png"));
    l1.addItem(1, 0, new Decoration("Grave","An ominous looking grave", "assets/scepter.png"));
    l1.addItem(1, 1, new Treasure("Grave","An ominous looking grave", "assets/coffin.png"));

    Location l2 = new Location(2, 2);
    l2.addExit(Direction.WEST, false);
    l2.addExit(Direction.SOUTH, false);
    l2.addItem(0, 1, new Decoration("Crow","A raggedy crow", "assets/evilOrb.png"));

    Location l3 = new Location(2, 2);
    l3.addExit(Direction.NORTH, false);
    l3.addExit(Direction.EAST, false);
    l3.addItem(1, 0, new Treasure("Golden Skull","Ancient golden skull embellished with gems", "assets/chest.png"));

    Location l4 = new Location(2, 2);
    l4.addExit(Direction.WEST, false);
    l4.addExit(Direction.NORTH, false);
    l4.addItem(1, 1, new Treasure("Kanye's Donda chain","A chain imbued with Chicago's energy", "assets/key.png"));


    //Add locations to Collection
    Location[][] locations = new Location[2][2];
    locations[0][0] = l1;
    locations[0][1] = l2;
    locations[1][0] = l3;
    locations[1][1] = l4;
    return new GameWorld(locations, l1);

  }
}
