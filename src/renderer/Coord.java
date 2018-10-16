package renderer;

/**
 * @author desilvnira
 *
 */
public class Coord {

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * @param x
   * x value of item
   * @param y
   * y value of item
   * @param width
   * width of item
   * @param height
   * height of item
   */
  public Coord(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * @return x pos
   */
  public int getX() {
    return x;
  }

  /**
   * @return y pos
   * gets the y position
   */
  public int getY() {
    return y;
  }

  /**
   * @return width
   * gets the width of the picture contained within the item
   * 
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return height
   * gets the height
   */
  public int getHeight() {
    return height;
  }

}
