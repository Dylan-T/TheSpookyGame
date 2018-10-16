package mapeditor;

import java.awt.Graphics;

/**
 *  Abstract Item class for objects in the room.
 * @author dunninnath
 *
 */
public abstract class Item implements TilePiece {
  private boolean isPickupable;

  /**
   * Constructor for any item.
   * @param isPickupable
   *
   */
  public Item(boolean isPickupable) {
    this.isPickupable = isPickupable;
  }

  /**
   * Checker to see if item can be picked up.
   *
   * @return true if item is pickupable
   */
  public boolean checkPickupable() {
    return isPickupable;
  }

  @Override
  public abstract void draw(Graphics g, int x, int y, int height, int width);

  @Override
  public abstract String toString();

}
