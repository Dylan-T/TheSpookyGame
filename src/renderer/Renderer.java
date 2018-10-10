package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
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
import javafx.scene.shape.Line;

/**
 * @author Niran
 *
 */
public class Renderer {
  
  /**
   * width of the canvas
   */
  public static final int CANVASWIDTH = 1800;
  /**
   * height of the canvas
   */
  public static final int CANVASHEIGHT = 951;
  
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
        cube = ImageIO.read(new File("Portal.png"));
    } catch (IOException e) {
    }
    //System.out.println(cube);
    
    
    if(dir == Direction.NORTH) {
      //x = 50* (1-scale);
      GameWorld game = GameWorld.testGameWorld2();
      Item[][] grids = game.getCurrentRoom().getGrid();
      
      int xgap = 2;
      int ygap = 2;
      
      int yMiddle = CANVASHEIGHT/2;
      int xMiddle = CANVASWIDTH/2;
      
      
      
      g.drawRect(xMiddle - 100, yMiddle - 50, 200, 100);
      
      //the background picture
      g.drawImage(grids[0][1].getImage().getScaledInstance(Math.round(200), Math.round(100), Image.SCALE_DEFAULT), xMiddle-100, yMiddle-50, null);
      g.drawRect(0, 0, CANVASWIDTH, CANVASHEIGHT);
      
      g.drawLine(xMiddle - 100, yMiddle - 50, 0, 0);
      g.drawLine(xMiddle + 100, yMiddle - 50, CANVASWIDTH, 0);
      g.drawLine(xMiddle - 100, yMiddle + 50, 0, CANVASHEIGHT);
      g.drawLine(xMiddle + 100, yMiddle + 50, CANVASWIDTH, CANVASHEIGHT);
      g.setColor(Color.GRAY);
      Polygon p = new Polygon();
      p.addPoint(xMiddle-100, yMiddle+50);
      p.addPoint(xMiddle+100, yMiddle+50);
      p.addPoint(CANVASWIDTH, CANVASHEIGHT);
      p.addPoint(0, CANVASHEIGHT);
      g.fillPolygon(p);
      g.setColor(Color.BLACK);
      
      int widthScale = CANVASWIDTH/200;
      int heightScale = CANVASHEIGHT/100;
      
      int tempY = 0;
      int tempX = 0;
      
      for(int i = yMiddle-50; i<yMiddle + 50; i+=1) {
        tempY += 1*heightScale;
        g.drawLine(xMiddle + 100, i, CANVASWIDTH, tempY);
      }
      
      tempY = 0;
      
      for(int i = yMiddle-50; i<yMiddle + 50; i+=1) {
        tempY += 1*heightScale;
        g.drawLine(xMiddle - 100, i, 0, tempY);
      }
      
      for(int i = xMiddle-100; i<xMiddle + 100; i+=1) {
        tempX += 1*widthScale;
        g.drawLine(i, yMiddle + 50, tempX, CANVASHEIGHT);
      }
      
      tempX = 0;
      
      for(int i = xMiddle-100; i<xMiddle + 100; i+=1) {
        tempX += 1*widthScale;
        g.drawLine(i, yMiddle - 50, tempX, 0);
      }
      
     /* g.drawRect(xMiddle + 90, yMiddle + 40, 10, 10);
      g.drawRect(CANVASWIDTH - 10*widthScale, CANVASHEIGHT - 10*heightScale, 10*widthScale, 10*heightScale);*/
      this.fillCube(new Cube(xMiddle + 90, yMiddle + 40, 10, CANVASWIDTH - 10*widthScale, CANVASHEIGHT - 10*heightScale, 10*widthScale), g);
      this.fillCube(new Cube(xMiddle - 100, yMiddle + 40, 10, 0 + 10*widthScale, CANVASHEIGHT - 10*heightScale, 10*widthScale), g);
      
      
      g.drawImage(cube.getScaledInstance(40, 45, Image.SCALE_DEFAULT), xMiddle-19, yMiddle-10, null); // the portal
      
      
      
      for(int i = 0; i < grids.length; i++) {
        for(int j = 0; j < grids[0].length; j++) {
          if(grids[i][j] != null) {
            
          
          Image current = grids[i][j].getImage();
          //g.drawImage(current.getScaledInstance(Math.round(20*scale), Math.round(20*scale), Image.SCALE_DEFAULT), x, y, null);
          x += 50*scale;
          scale += 0.1;
          }
        }
        x = 10;
        y += 50;
      }
      
      // the meme
      g.drawImage(grids[0][0].getImage().getScaledInstance(Math.round(133*scale), Math.round(75*scale), Image.SCALE_DEFAULT), xMiddle+804, yMiddle, null);
      
      double a = 0;
      int oldWidth = 200;
      int tempWidth = 200;
      int gapy = 30;
      int yIncrement = 30;
      int xStart = xMiddle - 100;
      int yStart = yMiddle + 50;
      int width2 = 10;
      int height2 = 40;
      int Xscaler = CANVASWIDTH/200;
      double Yscaler = Math.sqrt((xStart*xStart) + Math.abs(((yStart - CANVASHEIGHT)^2)))/(CANVASHEIGHT-(yStart));

      int i = xStart;
      double shapeW = 10;
      double shapeH = 40;
      
      while (yStart < CANVASHEIGHT) {

        while (i < (xStart + tempWidth)) {
          g.setColor(Color.ORANGE);
          g.drawRect(i, yStart, (int) shapeW, (int) shapeH);
          i = (int) (i + shapeW);
          //System.out.println(tempWidth/oldWidth);
          
        }
        
        double c = Yscaler*gapy;
        
        int b = gapy;
            
        a = Math.sqrt(c*c - b*b);
        oldWidth = tempWidth;
        //System.out.println(oldWidth);
        tempWidth = (int) (tempWidth + (2*a));
        shapeW += width2*(tempWidth/oldWidth);
        shapeH += height2*(yIncrement/gapy);
        //System.out.println(tempWidth);
        xStart = (int) (xStart - a);
        //System.out.println(xStart);
        i = xStart;
        yStart += gapy;
        yIncrement += 30;
      }
      
      //g.drawRect(xMiddle-10, yMiddle+10, 20, 40);
      /*for(int i = 0; i< 20; i++) {

        g.setColor(colors.get(colorNumber));
        colorNumber++;
     
        for(int j = 0; j< 20; j++) {
          //Image current = grid[i][j].getImage();
          //cube.getScaledInstance(Math.round(cube.getWidth(null)*scale), Math.round(cube.getHeight(null)*scale), Image.SCALE_DEFAULT);
          //g.drawImage(cube, x, y, null); 
          Cube temp = new Cube(x, y, Math.round(width*scale), Math.round(scale), Math.round(scale), scale);
          fillCube(temp, g);
          
          //g.setColor(Color.BLUE);
          //g.fillRect(x, y, Math.round(width*scale), Math.round(height*scale));
          //g.fill3DRect(x, y, Math.round(width*scale), Math.round(height*scale), true);
          
          //x += 50*scale;
          
          x = x + temp.Xgap;
          y = y + temp.Ygap;
          
          
        }
        scale += 0.2;
        //x = 10;
        //y += 50;
        
      }*/
      
    }
    
    
    
    //for()
    
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
   * fills the cube 
   */
  public void fillCube(Cube c, Graphics g){
    
    g.setColor(Color.GRAY);
    
    ArrayList<Point> square1 = c.square1Points;
    ArrayList<Point> square2 = c.square2Points;
    
    g.fillRect(square1.get(0).getX(), square1.get(0).getY(), c.size, c.size);
    
    
    
    Polygon rect1 = new Polygon();
    
    rect1.addPoint(square1.get(0).getX(), square1.get(0).getY());
    rect1.addPoint(square1.get(1).getX(), square1.get(1).getY());
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
   * @param c
   * @param g
   */
  public void fillCubeR(Cube c, Graphics g){
    Rectangle rec1 = c.rec1;
    Rectangle rec2 = c.rec2;
    
    g.drawLine(rec1.x, rec1.y, rec2.x, rec2.y);
  }
  
  public String toString() {
    return null;
  }
}
