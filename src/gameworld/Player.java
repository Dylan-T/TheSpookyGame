package gameworld;

import java.util.ArrayList;

/**
 * This is the player and has methods for actions the player may make.
 * @author thomsodyla1
 *
 */
public class Player {
  ArrayList<Item> inventory = new ArrayList<Item>();
  int score = 0;//Maybe
  Location currentLoc;

  /**
   * Moves the player in the given direction.
   * @param dir direction the player should move.
   */
  public void move(String dir){
  }

  /**
   * Use the given item from your inventory.
   * @param i Item that is to be used
   * @return whether the item was successfully used
   */
  public boolean useItem(Item i) {
    return false;
  }


}
