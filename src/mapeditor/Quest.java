package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author dunninnath
 *
 */
public class Quest extends Object {

  /**
   * @param color
   * @param isPickupable
   */
  public Quest(boolean isPickupable) {
    super(isPickupable);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(Color.BLUE);
    g.fillOval(x+width/4, y+(height/3), width/2, height/3);
    g.fillOval(x+width/4, y+((2*height)/3), width/2, height/3);
  }

  @Override
  public String toString() {
    return "quest";
  }

}
