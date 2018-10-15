package gameworld;

/**
 * @author Dylan
 *
 */
/**
 * Class for passages between locations.
 * 
 * @author thomsodyla1
 *
 */
public class Passage {
  private Location loc1;
  private Location loc2;
  private boolean locked;

  /**
   * Create a new passage between l1 and l2.
   * @param l1 one location the passage links
   * @param l2 another location the passage links
   */
  public Passage(Location l1, Location l2) {
    loc1 = l1;
    loc2 = l2;
    locked = false;
  }
  
  /**
   * Get the location on the other side of the passage.
   * @param from Location you're coming from
   * @return the location on the other side of the passage
   */
  public Location getOtherLocation(Location from) {
    if (from == loc1) {
      return loc2;
    } else if (from == loc2) {
      return loc1;
    }
    return null;
  }

  /**
   * Unlock this passage.
   * @return Whether the door successfully unlocked.
   */
  public boolean unlock() {
    if (!locked) { //Can't unlock an already unlocked door
      return false;
    }
    locked = false;
    return true;
  }
  
  /**
   * Lock this passage.
   * @return whether the passage successfully locked.
   */
  public boolean lock() {
    if (locked) { //Can't lock an already locked door
      return false;
    }
    locked = true;
    return true;
  }
  
  //Getters
  
  /**
   * Check whether the passage is locked.
   * @return if the passage is locked
   */
  public boolean isLocked() {
    return locked;
  }
  
  /**
   * @return one of two locations this passage connects
   */
  public Location getLoc1() {
    return loc1;
  }
  
  /**
   * @return one of two locations this passage connects
   */
  public Location getLoc2() {
    return loc2;
  }
}
