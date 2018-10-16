package renderer;

/**
 * class that stores the coordinate of the item image.
 * @author desilvnira
 */
public class Coord {
  private int exCoord;
  private int eyCoord;
  private int width;
  private int height;

  /**
   * x value of item.
   * @param x
   * x value of item.
   * @param y
   * y value of item.
   * @param width
   * width of item.
   * @param height
   * height of item.
   */
  public Coord(int x, int y, int width, int height) {
    this.exCoord = x;
    this.eyCoord = y;
    this.width = width;
    this.height = height;
  }

  /**
   *.
   * @return x pos
   */
  public int getX() {
    return exCoord;
  }

  /**
   * gets the y position.
   * @return y pos
   *
   */
  public int getY() {
    return eyCoord;
  }

  /**
   * gets the width of the picture contained within the item.
   * @return width
   *
   *
   */
  public int getWidth() {
    return width;
  }

  /**
   * gets the height.
   * @return height
   *
   */
  public int getHeight() {
    return height;
  }

}
