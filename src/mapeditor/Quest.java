package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Quest item that appears in the room.
 * @author dunninnath
 *
 */
public class Quest extends Item {

  /**
   * Constructor for the quest object.
   * @param isPickupable - true if the object can be picked up
   */
  public Quest(boolean isPickupable) {
    super(isPickupable);
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(Color.BLUE);
    g.fillOval(x + width / 4, y + (height / 3), width / 2, height / 3);
  }

  @Override
  public String toString() {
    return "quest";
  }

}
