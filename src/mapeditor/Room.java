package mapeditor;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * New Room Object
 * @author Nathan
 *
 */
public class Room implements Buildable {
  
  //Fields
  private final int width;
  private final int height;
  private int startX;
  private int startY;
  
  /**
   * Room constructor
   * @param width
   * @param height
   */
  public Room(int width, int height) {
    this.width = width;
    this.height = height;
  }
  
  /**
   * Width getter
   * @return width
   */
  public int getWidth() {
    return this.width;
  }
  
  
  /**
   * Height getter
   * @return height
   */
  public int getHeight() {
    return this.height;
  }
  
  /**
   * StartX getting
   * @return starting x coordinate of the room
   */
  public int getStartX() {
    return startX;
    
  }
  
  /**
   * Sets the start coordinates of x
   * @param x
   */
  public void setStartX(int x) {
    this.startX = x;
  }
  
  /**
   * Gets the starting Y coordinate of the room
   * @return startY
   */
  public int getStartY() {
    return startY;
    
  }
  
  /**
   * Sets the start coordinates of y
   * @param y
   */
  public void setStartY(int y) {
    this.startY = y;
  }
  
  public void draw(Graphics g, int x, int y) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));
    g2.drawRect(x, y, this.width, this.height);
  }
  
  @Override
  public String toString() {
    return "room";
  }

}
