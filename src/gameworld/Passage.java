package gameworld;

/**
 * Class for passages between locations.
 * 
 * @author thomsodyla1
 *
 */
public class Passage {
  Location loc1;
  Location loc2;
  boolean blocked;

  /**
   * Create a new passage between l1 and l2.
   * @param l1 one location the passage links
   * @param l2 another location the passage links
   */
  public Passage(Location l1, Location l2) {
    loc1 = l1;
    loc2 = l2;
    blocked = false;
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
}
