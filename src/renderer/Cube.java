package renderer;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Creates a cube using two faces.
 * @author Niran
 *
 */
public class Cube {

  int valueX;
  int valueY;
  int size;
  int x2;
  int y2;
  int size2;
  int exGap;
  int eyGap;
  float scale;

  Rectangle rec1;
  Rectangle rec2;

  ArrayList<Point> square1Points;
  ArrayList<Point> square2Points;

  /**
   *.
   * @param x
   *.
   * @param y
   *.
   * @param size
   *.
   * @param x2
   *.
   * @param y2
   *.
   * @param size2
   * .
   */
  public Cube(int x, int y, int size, int x2, int y2, int size2) {

    this.valueX = x;
    this.valueY = y;
    this.size = size;
    this.x2 = x2;
    this.y2 = y2;
    this.size2 = size2;
    square1Points = getSquare1Points();
    square2Points = getCube2Points();



  }

  /**
   * .
   * @param x
   * .
   * @param y
   * .
   */
  public Cube(Rectangle x, Rectangle y) {
    this.rec1 = x;
    this.rec2 = y;

  }

  /**
   * gets the points that make up the first square.
   * @return arrayList
   * @returns the list of points that make up the first square
   */
  public ArrayList<Point> getSquare1Points() {
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(valueX, valueY));
    points.add(new Point(valueX + size, valueY));
    points.add(new Point(valueX + size, valueY + size));
    points.add(new Point(valueX, valueY + size));
    return points;
  }

  /**
   * gets the points that make up the second square.
   * @return the list of points that make up the second square that is shifted
   */
  public ArrayList<Point> getCube2Points() {
    int newX = x2;
    int newY = y2;
    ArrayList<Point> points = new ArrayList<Point>();
    points.add(new Point(newX, newY));
    points.add(new Point(newX + size2, newY));
    points.add(new Point(newX + size2, newY + size2));
    points.add(new Point(newX, newY + size2));
    return points;
  }




}
