package mapeditor;

import java.awt.Graphics;

/**
 * A tile piece on the board.
 *
 * @author Nathan
 *
 */
public interface TilePiece {

  /**
   * Returns string description of the piece.
   *
   * @return Returns string description of the piece
   */
  public String toString();

  /**
   * Draws each piece on the board.
   *
   * @param g - Graphics
   * @param x - coordinate of the object
   * @param y - coordinate of the object
   * @param height - of the object
   * @param width - of the object
   */
  public void draw(Graphics g, int x, int y, int height, int width);

}
