package renderer;

/**
 * Creates a point class.
 * @author desilvnira
 *
 */
public class Point {

  private int exPoint;
  private int eyPoint;

  /**
   * creates a point for the polygons to use.
   * @param x
   * .
   * @param y
   * .
   */
  public Point(int x, int y) {
    this.exPoint = x;
    this.eyPoint = y;
  }

  /**
   * gets the value.
   * @return x value
   */
  public int getX() {
    return exPoint;
  }

  /**
   * gets the y value.
   * @return y value
   */
  public int getY() {
    return eyPoint;
  }

}
