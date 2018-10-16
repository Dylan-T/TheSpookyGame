package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Floor Piece in the room.
 *
 * @author Nathan
 *
 */
public class Floor implements TilePiece {
  // Field for its color - white
  private Color color;
  private Item object;

  /**
   * Constructor for the floor object.
   */
  public Floor() {
    color = new Color(255, 255, 255);
  }

  @Override
  public String toString() {
    return "floor";
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    if (object == null) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
    } else {
      g.setColor(color);
      g.fillRect(x, y, width, height);
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
      object.draw(g, x, y, height, width);
    }
  }

  /**
   * Sets the tile to have an object.
   *
   * @param object - Object to set the current tile to
   */
  public void setObject(Item object) {
    this.object = object;
  }

}
