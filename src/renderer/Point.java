package renderer;

/**
 * @author Niran
 * Point class used for the vertices of the Cubes 
 */
public class Point {
  
  private int x;
  private int y;

  /**
   * @param x
   * @param y
   */
  public Point(int x, int y) {
    
    this.x = x;
    this.y = y;
  }
  
  /**
   * @return x
   */
  public int getX() {
    return x;
   
  }
  
  /**
   * @return y
   */
  public int getY() {
    return y;
   
  }
  
}
