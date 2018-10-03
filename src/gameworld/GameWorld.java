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
   */
  public void movePlayer(Direction dir) {
    player.move(dir);
  }
}
