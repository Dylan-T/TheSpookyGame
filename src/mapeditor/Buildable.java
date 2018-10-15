package mapeditor;

import java.awt.Graphics;

/**
 * Interface for buildable objects in the room
 * @author Nathan
 *
 */
public interface Buildable {

  /**
   * Draw method for displaying on GUI.
   * @param g 
   * @param x
   * @param y
   */
  public void draw(Graphics g, int x, int y);
}
