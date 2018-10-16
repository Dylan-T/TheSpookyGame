package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author dunninnath
 *
 */
public class Treasure extends Object {

  /**
   * @param color
   * @param isPickupable
   */
  public Treasure(boolean isPickupable) {
    super(isPickupable);
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(new Color(255,223,0));
    g.fillRect(x+width/4, y+height/4, width/2, height/2);
  }

  @Override
  public String toString() {
    return "treasure";
  }

}
