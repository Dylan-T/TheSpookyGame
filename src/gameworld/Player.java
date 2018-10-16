package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gameworld.GameWorld.Direction;

/**
 * This is the player and has methods for actions the player may make.
 *
 * @author thomsodyla1
 *
 */
public class Player {
  private List<Item> inventory;
  private Location currentLoc;
  private GameWorld.Direction facing;

  /**
   * Create a new player only specifying location
   * @param startingLoc location the player starts from
   */
  public Player(Location startingLoc) {
    ArrayList<Item> inventory = new ArrayList<Item>();
    currentLoc = startingLoc;
    facing = GameWorld.Direction.NORTH;
  }

  /**
   * Moves the player in the given direction.
   * @param newLocation players new location
   * @param dir
   *
   * @return true if the player successfully moved in that direction
   */
  public boolean move(Location newLocation, Direction dir) {
    facing = dir;
    currentLoc = newLocation;

    return true;
  }

  /**
   * The player add's item i to their inventory and it is removed from the current location.
   * @param i Item that is to be picked up.
   * @return whether the item was successfully picked up.
   */
  public boolean pickupItem(Item i) {
    if(!i.canPickup()) {
      return false;
    }
    if (currentLoc.removeItem(i)) {
      inventory.add(i);
      return true;
    }
    return false;
  }

  /**
   * Player drops an item from their inventory into the current room
   * @param i The item that is to be dropped.
   * @return if the item was successfully dropped
   */
  public boolean dropItem(Item i) {
    if (inventory.contains(i) && !currentLoc.isFull()) {
      inventory.remove(i);
      currentLoc.addItem(i);
    }
    return false;
  }

  /**
   * Use the given item.
   *
   * @param i
   *          Item that is to be used
   *
   * @return whether the item was successfully used
   */
  public boolean useItem(Item i) {
    return i.use(this);
  }

  /**
   * Unlock a passage in the given direction, using a key from your inventory.
   * @param dir direction of the passage to unlock
   * @return whether a passage was successfully unlocked
   */
  public boolean unlockPassage(GameWorld.Direction dir) {
    Boolean exit = currentLoc.exits[dir.ordinal()];
    if (exit != null && exit == true) { //check the exit exists and is locked
      //Check you have a key for the passage
      for(Item i: inventory) {
        if(i instanceof Key) {
          exit = false;
          inventory.remove(i);
          return true;
        }
      }
    }
    return false;
  }

  //Getters

  /**
   * @return the players inventory
   */
  public Collection<Item> getInventory() {
    return inventory;
  }

  /**
   * @return the players current location.
   */
  public Location getCurrentLocation() {
    return currentLoc;
  }

  /**
   * @return the direction the player is facing
   */
  public GameWorld.Direction getFacing() {
    return facing;
  }

}
