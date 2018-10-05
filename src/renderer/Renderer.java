package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import gameworld.GameWorld;
import gameworld.GameWorld.Direction;
import gameworld.Item;
import gameworld.Location;

/**
 * @author Niran
 *
 */
public class Renderer {
  
  /**
   * width of the canvas
   */
  public static final int CANVASWIDTH = 900;
  /**
   * height of the canvas
   */
  public static final int CANVASHEIGHT = 750;
  
  Graphics graphics;
  
  /**
   * @param graphics
   * sets up the graphics for the items and exits to be drawn from. Not being used at the moment
   */
  public Renderer(Graphics graphics) {
    this.graphics = graphics;
  }
  
  /**
   * @param room
   * @param dir
   * @param g 
   */
  public void redraw(Location room, GameWorld.Direction dir, Graphics g) {
    
    /*List<Color> colors = new ArrayList<Color>();
    colors.add(Color.RED);
    colors.add(Color.BLUE);
    colors.add(Color.GREEN);
    colors.add(Color.CYAN);
    colors.add(Color.YELLOW);
    colors.add(Color.RED);
    colors.add(Color.BLUE);
    colors.add(Color.GREEN);
    colors.add(Color.CYAN);
    colors.add(Color.YELLOW);
    colors.add(Color.RED);
    colors.add(Color.BLUE);
    colors.add(Color.GREEN);
    colors.add(Color.CYAN);
    colors.add(Color.YELLOW);
    colors.add(Color.RED);
    colors.add(Color.BLUE);
    colors.add(Color.GREEN);
    colors.add(Color.CYAN);
    colors.add(Color.YELLOW);*/
    
    //System.out.println(graphics);
    
    //g.drawRect(50, 50, 50, 50);
    
    
    float scale = (float) 1; // need to figure out a proper scale to keep images from going outside of canvas 
    int x = 10;
    int y = 10;
    int width = 20;
    int height = 20;
    int colorNumber = 0;
    
    Item[][] grid = room.getGrid();
    System.out.println("length " + grid.length);
    
    
    /*
     * This is a draft version of using actual JPEG files 
     * Using small cubes to test the scaling of the rendering
     */

    BufferedImage cube = null;
    try {
        cube = ImageIO.read(new File("black cubde.jpg"));
    } catch (IOException e) {
    }
    //System.out.println(cube);
    
    
    if(dir == Direction.NORTH) {
      //x = 50* (1-scale);
      for(int i = 0; i< 20; i++) {

       /* g.setColor(colors.get(colorNumber));
        colorNumber++;*/
     
        for(int j = 0; j< 20; j++) {
          //Image current = grid[i][j].getImage();
          //cube.getScaledInstance(Math.round(cube.getWidth(null)*scale), Math.round(cube.getHeight(null)*scale), Image.SCALE_DEFAULT);
          //g.drawImage(cube, x, y, null); 
          fillCube(new Cube(x, y, Math.round(width*scale), Math.round(height*scale)),g);
          //g.setColor(Color.BLUE);
          //g.fillRect(x, y, Math.round(width*scale), Math.round(height*scale));
          //g.fill3DRect(x, y, Math.round(width*scale), Math.round(height*scale), true);
          x += 50*scale;
          
          
        }
        scale += 0.1;
        x = 10;
        y += 50;
        
      }
    }
    //g.setColor(Color.BLUE);
    
    if(dir == Direction.SOUTH) {
      for(int i = grid.length-1; i>= 0; i++) {
        for(int j = grid[0].length-1; j>=0; j++) {
          
          //Image current = grid[i][j].getImage();
          //g.drawImage(current, x, y, Math.round(current.getWidth(null)*scale), Math.round(current.getHeight(null)*scale), null); 
          g.fillRect(x, y, Math.round(width*scale), Math.round(height*scale));
          //g.fill3DRect(x, y, Math.round(width*scale), Math.round(height*scale), true);
          
          x += 50*scale;
          scale += 0.1;
          
        }
        
        x = 10;
        y += 50;
      }
    }
    
    if(dir == Direction.EAST) {
      for(int i = 0; i< grid.length; i++) {
        for(int j = 0; j< grid[0].length; j++) {
          
        }
      }
    }
    
    if(dir == Direction.WEST) {
      for(int i = 0; i< grid.length; i++) {
        for(int j = 0; j< grid[0].length; j++) {
          
        }
      }
    }
    
  } 
  
  /**
   * draws the cube onto the canvas byy connecting vertices
   * @param c 
   * @param g 
   */
  public void drawCube(Cube c, Graphics g){
    
    ArrayList<Point> square1 = c.square1Points;
    ArrayList<Point> square2 = c.square2Points;
    
    // draws the first square
    
    g.drawLine(square1.get(0).getX(), square1.get(0).getY(), square1.get(1).getX(), square1.get(1).getY());
    g.drawLine(square1.get(0).getX(), square1.get(0).getY(), square1.get(3).getX(), square1.get(3).getY());   
    g.drawLine(square1.get(2).getX(), square1.get(2).getY(), square1.get(1).getX(), square1.get(1).getY());
    g.drawLine(square1.get(2).getX(), square1.get(2).getY(), square1.get(3).getX(), square1.get(3).getY());
    
    // draws the second square
    
    g.drawLine(square2.get(0).getX(), square2.get(0).getY(), square2.get(1).getX(), square2.get(1).getY());
    g.drawLine(square2.get(0).getX(), square2.get(0).getY(), square2.get(3).getX(), square2.get(3).getY());    
    g.drawLine(square2.get(2).getX(), square2.get(2).getY(), square2.get(1).getX(), square2.get(1).getY());
    g.drawLine(square2.get(2).getX(), square2.get(2).getY(), square2.get(3).getX(), square2.get(3).getY());
    
    // connect the two squares to form a cube
    
    g.drawLine(square1.get(0).getX(), square1.get(0).getY(), square2.get(0).getX(), square2.get(0).getY());
    g.drawLine(square1.get(1).getX(), square1.get(1).getY(), square2.get(1).getX(), square2.get(1).getY());   
    g.drawLine(square1.get(2).getX(), square1.get(2).getY(), square2.get(2).getX(), square2.get(2).getY());
    g.drawLine(square1.get(3).getX(), square1.get(3).getY(), square2.get(3).getX(), square2.get(3).getY());
    
    
  }
  
  /**
   * @param c
   * @param g
   * @param size 
   */
  public void fillCube(Cube c, Graphics g){
    
    ArrayList<Point> square1 = c.square1Points;
    ArrayList<Point> square2 = c.square2Points;
    
    g.fillRect(square1.get(0).getX(), square1.get(0).getY(), c.size, c.size);
    
    g.fillRect(square2.get(0).getX(), square2.get(0).getY(), c.size, c.size);
    
    Polygon rect1 = new Polygon();
    
    rect1.addPoint(square1.get(0).getX(), square1.get(0).getY());
    rect1.addPoint(square1.get(1).getX(), square1.get(1).getY());
    rect1.addPoint(square2.get(1).getX(), square2.get(1).getY());
    
    Polygon rect2 = new Polygon();
    
    rect2.addPoint(square1.get(0).getX(), square1.get(0).getY());
    rect2.addPoint(square2.get(0).getX(), square2.get(0).getY());
    rect2.addPoint(square2.get(3).getX(), square2.get(3).getY());
    
    g.fillPolygon(rect1);
    g.fillPolygon(rect2);
    
  }
  
  public String toString() {
    return null;
  }
}
