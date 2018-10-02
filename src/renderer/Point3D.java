package renderer;

/**
 * @author Niran
 * The X, Y, Z co-ordinates
 * 
 */
public class Point3D {
  
  float x;
  float y;
  float z;
  
  /**
   * @param x
   * @param y
   * @param z
   */
  public Point3D(float x, float y, float z) {
    
    this.x = x;
    this.y = y;
    this.z = z;
    
  }
  
  public String toString() {
    return "Co-ordinate X: " + this.x + " Co-ordinate Y: " + this.y + " Co-ordinate Z: " + this.z;
  }

}
