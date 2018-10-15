package gameworld;

import java.util.HashSet;
import java.util.Set;

/**
 * Wrapper class to hold the game world together and give the other packages a simple class to communicate with.
 * This is implementing a facade pattern.
 * @author Dylan
 *
 */
public class GameWorld {
  Set<Location> locations = new HashSet<Location>();
  Player player;
  Set<Quest> quests;
  
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
  public GameWorld(Set<Location> locations, Location start) {
    this.locations = locations;
    player = new Player(start);
  }
  
  
  /**
   * Moves the player in the given direction.
   * @param dir to move in
   * @return True if the player successfully moved
   */
  public boolean movePlayer(Direction dir) {
    return player.move(dir);
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
  
  /**
   * Gets the players current location.
   * @return the players current location
   */
  public Location getCurrentRoom() {
    return player.currentLoc;
  }
  
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

    
    //Make passages
    Passage p1 = new Passage(l1,l2);
    Passage p2 = new Passage(l1,l3);
    Passage p3 = new Passage(l2,l4);
    Passage p4 = new Passage(l3,l4);
    
    //Connect locations using passages
    l1.addPassage(Direction.EAST, p1);
    l2.addPassage(Direction.WEST, p1);
    
    l1.addPassage(Direction.SOUTH, p2);
    l3.addPassage(Direction.NORTH, p2);
    
    l2.addPassage(Direction.SOUTH, p3);
    l4.addPassage(Direction.NORTH, p3);
    
    l3.addPassage(Direction.EAST, p4);
    l4.addPassage(Direction.WEST, p4);
    
    //Add locations to Collection
    Set<Location> locations = new HashSet<Location>();
    locations.add(l1);
    locations.add(l2);
    locations.add(l3);
    locations.add(l4);
    return new GameWorld(locations, l1);
  }
  
  /**
   * Creates an GameWorld with empty 4 rooms.
   * @return the new GameWorld
   */
  public static GameWorld testGameWorld2() {
    //Make locations & add item's
    Location l1 = new Location(2, 2);
    l1.addItem(0, 0, new Decoration("Grave","An ominous looking grave", "assets/boneNecklace.png"));
    l1.addItem(0, 1, new Treasure("Grave","An ominous looking grave", "assets/femur.png"));
    l1.addItem(1, 0, new Decoration("Grave","An ominous looking grave", "assets/scepter.png"));
    l1.addItem(1, 1, new Treasure("Grave","An ominous looking grave", "assets/coffin.png"));
    Location l2 = new Location(2, 2);
    l2.addItem(0, 1, new Decoration("Crow","A raggedy crow", "assets/evilOrb.png"));
    Location l3 = new Location(2, 2);
    l3.addItem(1, 0, new Treasure("Golden Skull","Ancient golden skull embellished with gems", "assets/chest.png"));
    Location l4 = new Location(2, 2);
    l4.addItem(1, 1, new Treasure("Kanye's Donda chain","A chain imbued with Chicago's energy", "assets/key.png"));
    
    //Make passages
    Passage p1 = new Passage(l1,l2);
    Passage p2 = new Passage(l1,l3);
    Passage p3 = new Passage(l2,l4);
    Passage p4 = new Passage(l3,l4);
    
    //Connect locations using passages
    l1.addPassage(Direction.EAST, p1);
    l2.addPassage(Direction.WEST, p1);
    
    l1.addPassage(Direction.SOUTH, p2);
    l3.addPassage(Direction.NORTH, p2);
    
    l2.addPassage(Direction.SOUTH, p3);
    l4.addPassage(Direction.NORTH, p3);
    
    l3.addPassage(Direction.EAST, p4);
    l4.addPassage(Direction.WEST, p4);
    
    //Add locations to Collection
    Set<Location> locations = new HashSet<Location>();
    locations.add(l1);
    locations.add(l2);
    locations.add(l3);
    locations.add(l4);
    return new GameWorld(locations, l1);
    
  }
}
