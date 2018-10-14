package mapeditor;

import java.awt.Graphics;

/**
 * A tile piece on the board
 * @author Nathan
 *
 */
public interface TilePiece {

  /**
   * Returns string description of the piece
   * @return Returns string description of the piece
   */
  public String toString();
  
  
  /**
   * Draws each piece on the board
   * @param g
   * @param x
   * @param y
   * @param height
   * @param width
   * @param color
   */
  public void draw(Graphics g, int x, int y, int height, int width);
  
  /**
   * Sets the highlight of this tile when clicked
   */
  public void setHightlight();
}
