package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Piece that represents the wall.
 * @author Nathan
 *
 */
public class Wall implements TilePiece {
  // Field for its color - white
  Color color = new Color(50, 50, 50);

  @Override
  public String toString() {
    return "wall";
  }

  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(color);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
  }

}
