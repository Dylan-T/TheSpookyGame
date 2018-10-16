package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A key is an item required to unlock a door or a quest item.
 * @author dunninnath
 *
 */
public class Key extends Item {

  /**
   * Constructor for the key object.
   * @param isPickupable - True if it can be picked up
   */
  public Key(boolean isPickupable) {
    super(isPickupable);
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(Color.BLACK);
    g.drawLine(x + width / 2, y + height / 4, (int) (x + (0.75 * width)), y + height / 2);
    g.drawLine((int) (x + (0.75 * width)), y + height / 2, x + width / 2,
        (int) (y + (0.75 * height)));
    g.drawLine(x + width / 2, (int) (y + (0.75 * height)), x + width / 4, y + height / 2);
    g.drawLine(x + width / 4, y + height / 2, x + width / 2, y + height / 4);
  }

  @Override
  public String toString() {
    return "key";
  }

}
