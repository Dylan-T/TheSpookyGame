package renderer;

/**
 * @author Niran
 *
 */
public class PolygonMesh {
  
  int faces;
  Polygon3D[] mesh;
  
  /**
   * @param faces
   */
  public PolygonMesh(int faces) {
    
    this.faces = faces;
    mesh = new Polygon3D[faces];
    System.out.println("hey");
  }

}
