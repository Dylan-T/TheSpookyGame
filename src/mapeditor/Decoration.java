package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Decoration items in the room.
 *
 * @author dunninnath
 *
 */
public class Decoration extends Item {

  /**
   * Constructor to construct the decoration object.
   *
   * @param isPickupable
   *          - true if the item is able to be picked up
   */
  public Decoration(boolean isPickupable) {
    super(isPickupable);
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (i == 0) {
          if (j == 0) {
            g.setColor(Color.RED);
          } else if (j == 1) {
            g.setColor(Color.GREEN);
          } else {
            g.setColor(Color.BLUE);
          }
        } else if (i == 1) {
          if (j == 0) {
            g.setColor(Color.GREEN);
          } else if (j == 1) {
            g.setColor(Color.BLUE);
          } else {
            g.setColor(Color.RED);
          }
        } else {
          if (j == 0) {
            g.setColor(Color.BLUE);
          } else if (j == 1) {
            g.setColor(Color.RED);
          } else {
            g.setColor(Color.GREEN);
          }
        }
        g.fillOval(x + width / 6 + (j * width / 4), y + height / 6 + (i * height / 4), width / 6,
            height / 6);
      }
    }

  }

  @Override
  public String toString() {
    return "decoration";
  }

}
