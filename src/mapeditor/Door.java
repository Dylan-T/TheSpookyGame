package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Door piece on the tile.
 *
 * @author Nathan
 *
 */
public class Door implements TilePiece {
  // Field for its color - white
  Color color = new Color(220, 220, 220);
  boolean isHighlighted = false;

  @Override
  public String toString() {
    return "door";
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(color);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
  }
}
