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
   * @param y
   * @param width
   * @param height
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
   */
  public int getY() {
    return y;
  }

  /**
   * @return width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return height
   */
  public int getHeight() {
    return height;
  }

}
