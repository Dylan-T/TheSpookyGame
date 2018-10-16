package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author dunninnath
 *
 */
public abstract class Object implements TilePiece {
  private boolean isPickupable;

  /**
   * @param color
   * @param isPickupable
   *
   */
  public Object(boolean isPickupable) {
    this.isPickupable = isPickupable;
  }

  /**
   * Checker to see if item can be picked up.
   * @return true if item is pickupable
   */
  public boolean CheckPickupable() {
    return isPickupable;
  }

  @Override
  public abstract void draw(Graphics g, int x, int y, int height, int width);

  @Override
  public abstract String toString();

}
