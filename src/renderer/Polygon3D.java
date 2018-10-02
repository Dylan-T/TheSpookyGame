package renderer;

/**
 * @author Niran
 * The array of Point3D objects 
 * Only contains 3 of them as we are working with triangles
 * 
 */
public class Polygon3D {
  
  Point3D[] triangle;
  
  /**
   * Creates new polygon object which is essentially an array of point objects
   */
  public Polygon3D() {
    
    triangle = new Point3D[3];

  }

}
