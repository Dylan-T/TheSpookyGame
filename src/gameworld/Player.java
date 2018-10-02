package gameworld;

import java.util.ArrayList;

/**
 * This is the player and has methods for actions the player may make.
 * 
 * @author thomsodyla1
 *
 */
public class Player {
  ArrayList<Item> inventory = new ArrayList<Item>();
  int score = 0;// Maybe
  Location currentLoc;
  Location.Direction facing;

  /**
   * Moves the player in the given direction.
   * 
   * @param dir
   *          direction the player should move.
   */
  public void move(String dir) {
    switch (dir) {
      case "FORWARD":
        break;
      case "LEFT":
        break;
      case "RIGHT":
        break;
      case "BACK":
        break;
      default:
        break;
    }
  }

  /**
   * The player add's item i to their inventory and it is removed from the current location.
   * @param i Item that is to be picked up.
   * @return whether the item was successfully picked up.
   */
  public boolean pickupItem(Item i) {
    if (currentLoc.removeItem(i)) {
      inventory.add(i);
      return true;
    }
    return false;
  }

  /**
   * Use the given item from your inventory.
   * 
   * @param i
   *          Item that is to be used
   * @return whether the item was successfully used
   */
  public boolean useItem(Item i) {
    if (!inventory.contains(i)) {
      return false;
    }
    return true;
  }

}
