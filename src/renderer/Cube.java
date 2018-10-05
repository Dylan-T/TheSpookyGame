package renderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Niran
 *
 */
public class Cube {
  
  int x;
  int y;
  int size;
  int Xgap;
  int Ygap;
  
  ArrayList<Point> square1Points;
  ArrayList<Point> square2Points;
  
  /**
   * @param x
   * @param y
   * @param size
   * @param Xgap 
   * @param Ygap 
   * @param gap
   */
  public Cube(int x, int y, int size, int Xgap, int Ygap) {

    this.x = x;
    this.y = y;
    this.size = size;
    this.Xgap = Xgap;
    this.Ygap = Ygap;
    square1Points = getSquare1Points();
    square2Points = getCube2Points();

  }

  /**
   * @return arrayList
   * @returns the list of points that make up the first square
   */
  public ArrayList<Point> getSquare1Points() {
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(x, y));
    points.add(new Point(x + size, y));
    points.add(new Point(x + size, y + size));
    points.add(new Point(x, y + size));
    return points;
  }

  /**
   * @return the list of points that make up the second square that is shifted
   */
  public ArrayList<Point> getCube2Points() {
    int newX = x + Xgap;
    int newY = y + Ygap;
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(newX, newY));
    points.add(new Point(newX + size, newY));
    points.add(new Point(newX + size, newY + size));
    points.add(new Point(newX, newY + size));
    return points;
  }
  
  
  

}
