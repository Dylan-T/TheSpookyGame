package mapeditor;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Nathan
 *
 */
public class WallPiece implements TilePiece {
  //Field for its color - white
  Color color = new Color(220,220,220);
  Color highlight = new Color(10,200,30);
  boolean isHighlighted = false;
  
  
  @Override public String toString() {
    return "wall";
  }
  
  @Override
  public void draw(Graphics g, int x, int y, int height, int width) {
    g.setColor(isHighlighted ? highlight : color);
    g.fillRect(x, y, width, height);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, width, height);
  }


  @Override
  public void setHightlight() {
    if(isHighlighted) {
      isHighlighted = false;
    } else {
      isHighlighted = true;
    }
  }
}
