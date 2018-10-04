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
   * @return True if the player succesfully moved
   */
  public boolean movePlayer(Direction dir) {
    return player.move(dir);
  }
  
  /**
   * Creates an empty GameWorld with 4 rooms.
   * @return the new GameWorld
   */
  static GameWorld testGameWorld() {
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
  
}
