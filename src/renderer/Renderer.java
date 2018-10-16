package renderer;

import gameworld.GameWorld;
import gameworld.GameWorld.Direction;
import gameworld.Item;
import gameworld.Location;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;



/**
 * class that renders the graphics that the user sees on canvas.
 * @author Niran
 *
 */
public class Renderer {

  Item[][] grid;
  ArrayList<Coord> coords = new ArrayList<Coord>();

  /**
   * width of the canvas.
   */
  public static final int CANVASWIDTH = 1800;
  /**
   * height of the canvas.
   */
  public static final int CANVASHEIGHT = 951;

  Graphics graphics;

  /**
   * sets up the graphics for the items and exits to be drawn from.
   * @param graphics redraws graphics
   */
  public Renderer(Graphics graphics) {
    this.graphics = graphics;
  }

  /**
   * .
   * @param room
   * .
   * @param dir
   * .
   * @param g
   * .
   */
  public void redraw(Location room, GameWorld.Direction dir, Graphics g) {

    // need to figure out a proper scale to keep images from going outside of canvas
    float scale = (float) 1;

    int x = 10;
    int y = 10;
    int width = 20;
    int height = 20;

    grid = room.getGrid();
    System.out.println("length " + grid.length);

    /*
     * This is a draft version of using actual JPEG files Using small cubes to test
     * the scaling of the rendering
     */

    //BufferedImage cube = null;
    BufferedImage scaryBack = null;
    BufferedImage bat = null;
    /*try {
      //cube = ImageIO.read(new File("Portal.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    try {
      scaryBack = ImageIO.read(new File("assets/scaryFace.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      bat = ImageIO.read(new File("assets/Scarybat.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println(cube);

    if (dir == Direction.NORTH) {

      int eyMiddle = CANVASHEIGHT / 2;
      int exMiddle = CANVASWIDTH / 2;

      int exDimension = 150;
      int eyDimension = (int) (exDimension / 1.89);

      g.setColor(Color.LIGHT_GRAY);
      Polygon right = new Polygon();
      right.addPoint(exMiddle + exDimension, eyMiddle - eyDimension);
      right.addPoint(CANVASWIDTH, 0);
      right.addPoint(CANVASWIDTH, CANVASHEIGHT);
      right.addPoint(exMiddle + exDimension, eyMiddle + eyDimension);

      Polygon top = new Polygon();
      top.addPoint(exMiddle - exDimension, eyMiddle - eyDimension);
      top.addPoint(0, 0);
      top.addPoint(CANVASWIDTH, 0);
      top.addPoint(exMiddle + exDimension, eyMiddle - eyDimension);

      Polygon left = new Polygon();
      left.addPoint(exMiddle - exDimension, eyMiddle + eyDimension);
      left.addPoint(0, CANVASHEIGHT);
      left.addPoint(0, 0);
      left.addPoint(exMiddle - exDimension, eyMiddle - eyDimension);

      Polygon bottom = new Polygon();
      bottom.addPoint(exMiddle - exDimension, eyMiddle + eyDimension);
      bottom.addPoint(0, CANVASHEIGHT);
      bottom.addPoint(CANVASWIDTH, CANVASHEIGHT);
      bottom.addPoint(exMiddle + exDimension, eyMiddle + eyDimension);

      g.fillPolygon(right);
      g.fillPolygon(top);
      g.fillPolygon(left);
      g.fillPolygon(bottom);

      g.drawRect(exMiddle - exDimension, eyMiddle - eyDimension, 2 * exDimension, 2 * eyDimension);

      // the background picture
      g.drawImage(scaryBack.getScaledInstance(Math.round(2 * exDimension),
          Math.round(2 * eyDimension), Image.SCALE_DEFAULT), exMiddle - exDimension,
          eyMiddle - eyDimension, null);
      g.drawRect(0, 0, CANVASWIDTH, CANVASHEIGHT);

      g.drawLine(exMiddle - exDimension, eyMiddle - eyDimension, 0, 0);
      g.drawLine(exMiddle + exDimension, eyMiddle - eyDimension, CANVASWIDTH, 0);
      g.drawLine(exMiddle - exDimension, eyMiddle + eyDimension, 0, CANVASHEIGHT);
      g.drawLine(exMiddle + exDimension, eyMiddle + eyDimension, CANVASWIDTH, CANVASHEIGHT);
      g.setColor(Color.GRAY);
      Polygon p = new Polygon();
      p.addPoint(exMiddle - exDimension, eyMiddle + eyDimension);
      p.addPoint(exMiddle + exDimension, eyMiddle + eyDimension);
      p.addPoint(CANVASWIDTH, CANVASHEIGHT);
      p.addPoint(0, CANVASHEIGHT);
      g.fillPolygon(p);
      g.setColor(Color.BLACK);

      int widthScale = CANVASWIDTH / (exDimension * 2);
      int heightScale = CANVASHEIGHT / (eyDimension * 2);

      int tempY = 0;
      int tempX = 0;
      int incrementer = 1;

      for (int i = eyMiddle - eyDimension; i < eyMiddle + eyDimension; i += incrementer) {
        tempY += incrementer * heightScale;
        g.drawLine(exMiddle + exDimension, i, CANVASWIDTH, tempY);
      }

      tempY = 0;

      for (int i = eyMiddle - eyDimension; i < eyMiddle + eyDimension; i += incrementer) {
        tempY += incrementer * heightScale;
        g.drawLine(exMiddle - exDimension, i, 0, tempY);
      }

      for (int i = exMiddle - exDimension; i < exMiddle + exDimension; i += incrementer) {
        tempX += incrementer * widthScale;
        g.drawLine(i, eyMiddle + eyDimension, tempX, CANVASHEIGHT);
      }

      tempX = 0;

      for (int i = exMiddle - exDimension; i < exMiddle + exDimension; i += incrementer) {
        tempX += incrementer * widthScale;
        g.drawLine(i, eyMiddle - eyDimension, tempX, 0);
      }

      g.setColor(Color.BLACK);
      Polygon rightDoor = new Polygon();
      rightDoor.addPoint((exMiddle + exDimension) + ((CANVASWIDTH - (exMiddle + exDimension)) / 2),
          eyMiddle - eyDimension);
      rightDoor.addPoint(CANVASWIDTH, 250);
      rightDoor.addPoint(CANVASWIDTH, CANVASHEIGHT);
      rightDoor.addPoint((exMiddle + exDimension) + ((CANVASWIDTH - (exMiddle + exDimension)) / 2),
          CANVASHEIGHT - ((eyMiddle + eyDimension) / 2) + 50);
      g.fillPolygon(rightDoor);

      g.setColor(Color.ORANGE);
      g.fillOval(CANVASWIDTH - 40, 550, 20, 40);

      g.setColor(Color.BLACK);
      Polygon leftDoor = new Polygon();
      leftDoor.addPoint((exMiddle - exDimension) - (((exMiddle - exDimension)) / 2),
          eyMiddle - eyDimension);
      leftDoor.addPoint(0, 250);
      leftDoor.addPoint(0, CANVASHEIGHT);
      leftDoor.addPoint((exMiddle - exDimension) - (((exMiddle - exDimension)) / 2),
          CANVASHEIGHT - ((eyMiddle + eyDimension) / 2) + 70);
      g.fillPolygon(leftDoor);

      g.setColor(Color.ORANGE);
      g.fillOval(40, 550, 20, 40);

      /*
       * g.drawRect(xMiddle + 90, yMiddle + 40, 10, 10); g.drawRect(CANVASWIDTH -
       * 10*widthScale, CANVASHEIGHT - 10*heightScale, 10*widthScale, 10*heightScale);
       */
      this.fillCube(new Cube(exMiddle + (exDimension - 10), eyMiddle + (eyDimension - 10), 10,
          CANVASWIDTH - 10 * widthScale, CANVASHEIGHT - 10 * heightScale, 10 * widthScale), g);
      this.fillCube(new Cube(exMiddle - exDimension, eyMiddle + (eyDimension - 10), 10,
          0 + 10 * widthScale, CANVASHEIGHT - 10 * heightScale, 10 * widthScale), g);

      // g.drawImage(cube.getScaledInstance(40, 45, Image.SCALE_DEFAULT), xMiddle-19,
      // yMiddle-10, null); // the portal

      double a = 0;
      int oldWidth = 200;
      int tempWidth = 200;
      int gapy = 30;
      int eyIncrement = 30;
      int exStart = exMiddle - exDimension;
      int eyStart = eyMiddle + eyDimension;
      int width2 = 10;
      int height2 = 30;
      double eyScaler = Math.sqrt((exStart * exStart) + Math.abs(((eyStart - CANVASHEIGHT) ^ 2)))
          / (CANVASHEIGHT - (eyStart));

      int i = exStart;
      double shapeW = 10;
      double shapeH = 40;

      int countI = 0;
      int countJ = 0;

      while (eyStart < CANVASHEIGHT && countJ < grid.length) {

        while (i < (exStart + tempWidth) && countI < grid[0].length) {
          // g.setColor(Color.ORANGE);
          // g.drawRect(i, yStart, (int) shapeW, (int) shapeH);

          // set up for images to be drawn on floor

          if (grid[countI][countJ] != null) {
            System.out.println(grid[countI][countJ].getDescription());

            Image img = grid[countI][countJ].getImage().getScaledInstance((int) shapeW,
                (int) shapeH, Image.SCALE_DEFAULT);
            Coord tempCoord = new Coord(i, eyStart, img.getWidth(null), img.getHeight(null));
            coords.add(tempCoord);
            //System.out.println(tempCoord);
            g.drawImage(img, i, eyStart, null);
          }
          i = (int) (i + shapeW);

          // System.out.println(tempWidth/oldWidth);
          countI++;
        }
        countI = 0;

        double c = eyScaler * gapy;

        int b = gapy;

        a = Math.sqrt(c * c - b * b);
        oldWidth = tempWidth;
        // System.out.println(oldWidth);
        tempWidth = (int) (tempWidth + (2 * a));
        shapeW += width2 * (tempWidth / oldWidth);
        shapeH += height2 * (eyIncrement / gapy);
        // System.out.println(tempWidth);
        exStart = (int) (exStart - a);
        // System.out.println(xStart);
        i = exStart;
        eyStart += gapy;
        eyIncrement += 30;
        countJ++;
      }

      a = 0;
      oldWidth = 200;
      tempWidth = 200;
      gapy = 30;
      eyIncrement = 50;
      exStart = exMiddle - exDimension + 20;
      eyStart = eyMiddle - eyDimension - 30;
      width2 = 10;
      height2 = 40;
      eyScaler = Math.sqrt((exStart * exStart) + Math.abs(((eyStart - CANVASHEIGHT) ^ 2)))
          / (CANVASHEIGHT - (eyStart));

      i = exStart;
      shapeW = 30;
      shapeH = 40;

      countI = 0;
      countJ = 0;

      while (eyStart > 0) {

        while (i < (exStart + tempWidth)) {

          g.drawImage(bat.getScaledInstance((int) shapeW, (int) shapeH, Image.SCALE_DEFAULT), i,
              eyStart, null);

          i = (int) (i + shapeW);

          countI++;
        }
        countI = 0;
        //int count
        double c = eyScaler * gapy;

        int b = gapy;

        a = Math.sqrt(c * c - b * b);
        oldWidth = tempWidth;
        // System.out.println(oldWidth);
        tempWidth = (int) (tempWidth + (2 * a));
        shapeW += width2 * (tempWidth / oldWidth);
        shapeH += height2 * (eyIncrement / gapy);
        // System.out.println(tempWidth);
        exStart = (int) (exStart - a);
        // System.out.println(xStart);
        i = exStart;
        eyStart -= gapy;
        eyIncrement -= 30;
        countJ++;
      }
      g.setColor(Color.BLACK);
      Polygon rightTriangle = new Polygon();
      rightTriangle.addPoint(exMiddle + exDimension, eyMiddle - eyDimension);
      rightTriangle.addPoint(CANVASWIDTH, 0);
      rightTriangle.addPoint(CANVASWIDTH - 400, 0);

      Polygon leftTriangle = new Polygon();
      leftTriangle.addPoint(exMiddle - exDimension, eyMiddle - eyDimension);
      leftTriangle.addPoint(0, 0);
      leftTriangle.addPoint(400, 0);

      // g.fillPolygon(rightTriangle);
      // g.fillPolygon(leftTriangle);

      // g.drawRect(xMiddle-10, yMiddle+10, 20, 40);
      /*
       * for(int i = 0; i< 20; i++) {
       *
       * g.setColor(colors.get(colorNumber)); colorNumber++;
       *
       * for(int j = 0; j< 20; j++) { //Image current = grid[i][j].getImage();
       * //cube.getScaledInstance(Math.round(cube.getWidth(null)*scale),
       * Math.round(cube.getHeight(null)*scale), Image.SCALE_DEFAULT);
       * //g.drawImage(cube, x, y, null); Cube temp = new Cube(x, y,
       * Math.round(width*scale), Math.round(scale), Math.round(scale), scale);
       * fillCube(temp, g);
       *
       * //g.setColor(Color.BLUE); //g.fillRect(x, y, Math.round(width*scale),
       * Math.round(height*scale)); //g.fill3DRect(x, y, Math.round(width*scale),
       * Math.round(height*scale), true);
       *
       * //x += 50*scale; private x = x + temp.Xgap; y = y + temp.Ygap;
       *
       *
       * } scale += 0.2; //x = 10; //y += 50;
       *
       * }
       */

    }

    // for()

    // g.setColor(Color.BLUE);

    if (dir == Direction.SOUTH) {
      for (int i = grid.length - 1; i >= 0; i++) {
        for (int j = grid[0].length - 1; j >= 0; j++) {

          // Image current = grid[i][j].getImage();
          // g.drawImage(current, x, y, Math.round(current.getWidth(null)*scale),
          // Math.round(current.getHeight(null)*scale), null);
          g.fillRect(x, y, Math.round(width * scale), Math.round(height * scale));
          // g.fill3DRect(x, y, Math.round(width*scale), Math.round(height*scale), true);

          x += 50 * scale;
          scale += 0.1;

        }

        x = 10;
        y += 50;
      }
    }

    if (dir == Direction.EAST) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {

        }
      }
    }

    if (dir == Direction.WEST) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {

        }
      }
    }

  }

  /**
   * used to check if an item has been clicked on from the gui.
   * @param x
   * .
   * @param y
   * .
   * @return if it is within
   */
  public Item isWithin(int x, int y) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] != null) {
          // System.out.println("here");
          // for(int yPos = coords.get(count).getY(); yPos < coords.get(count).getY() +
          // coords.get(count).getHeight(); yPos++) {
          // for(int xPos = coords.get(count).getX(); xPos < coords.get(count).getX() +
          // coords.get(count).getWidth(); xPos++) {
          // if(x == xPos && y == yPos) {
          // return grid[j][i];
          // }
          // }
          // }
          if ((coords.get(count).getX() < x
              && x < (coords.get(count).getX() + coords.get(count).getWidth()))) {

            if (coords.get(count).getY() + 60 < y
                && y < (coords.get(count).getY() + 60 + coords.get(count).getHeight())) {
              System.out.println("here");
              return grid[i][j];
            }
          }
          count++;
        }
      }
    }
    return null;
  }

  /**
   * fills the cube.
   * @param c
   * .
   * @param g
   * .
   */
  public void fillCube(Cube c, Graphics g) {

    g.setColor(Color.GRAY);

    ArrayList<Point> square1 = c.square1Points;

    g.fillRect(square1.get(0).getX(), square1.get(0).getY(), c.size, c.size);
    Polygon rect1 = new Polygon();
    rect1.addPoint(square1.get(0).getX(), square1.get(0).getY());
    rect1.addPoint(square1.get(1).getX(), square1.get(1).getY());
    ArrayList<Point> square2 = c.square2Points;
    rect1.addPoint(square2.get(1).getX(), square2.get(1).getY());
    rect1.addPoint(square2.get(0).getX(), square2.get(0).getY());

    Polygon rect2 = new Polygon();

    rect2.addPoint(square1.get(0).getX(), square1.get(0).getY());
    rect2.addPoint(square2.get(0).getX(), square2.get(0).getY());
    rect2.addPoint(square2.get(3).getX(), square2.get(3).getY());
    rect2.addPoint(square1.get(3).getX(), square1.get(3).getY());

    Polygon rect3 = new Polygon();

    rect3.addPoint(square1.get(1).getX(), square1.get(1).getY());
    rect3.addPoint(square2.get(1).getX(), square2.get(1).getY());
    rect3.addPoint(square2.get(2).getX(), square2.get(2).getY());
    rect3.addPoint(square1.get(2).getX(), square1.get(2).getY());

    Polygon rect4 = new Polygon();

    rect4.addPoint(square1.get(3).getX(), square1.get(3).getY());
    rect4.addPoint(square2.get(3).getX(), square2.get(3).getY());
    rect4.addPoint(square2.get(2).getX(), square2.get(2).getY());
    rect4.addPoint(square1.get(2).getX(), square1.get(2).getY());

    g.setColor(Color.BLACK);

    g.fillPolygon(rect1);

    g.fillPolygon(rect2);

    g.fillPolygon(rect3);
    g.setColor(Color.GRAY);
    g.fillPolygon(rect4);

    g.fillRect(square2.get(0).getX(), square2.get(0).getY(), c.size2, c.size2);

  }

  /**
   * gets the item grid.
   * @return grid
   */
  public Item[][] getGrid() {
    return grid;
  }

}
