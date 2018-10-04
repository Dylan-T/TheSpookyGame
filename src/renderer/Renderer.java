package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

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
    
    List<Color> colors = new ArrayList<Color>();
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
    colors.add(Color.YELLOW);
    
    //System.out.println(graphics);
    
    //g.drawRect(50, 50, 50, 50);
    
    float scale = 1;
    int x = 10;
    int y = 10;
    int width = 20;
    int height = 20;
    int colorNumber = 0;
    
    Item[][] grid = room.getGrid();
    System.out.println("length " + grid.length);
    
    if(dir == Direction.NORTH) {
      //x = 50* (1-scale);
      for(int i = 0; i< 20; i++) {

        g.setColor(colors.get(colorNumber));
        colorNumber++;
     
        for(int j = 0; j< 20; j++) {
          //Image current = grid[i][j].getImage();
          //g.drawImage(current, x, y, Math.round(current.getWidth(null)*scale), Math.round(current.getHeight(null)*scale), null); 
          g.fillRect(x, y, Math.round(width*scale), Math.round(height*scale));
          x += 50*scale;
          
          
        }
        scale += 0.1;
        x = 10;
        y += 50;
        
      }
    }
    
    if(dir == Direction.SOUTH) {
      for(int i = grid.length-1; i>= 0; i++) {
        for(int j = grid[0].length-1; j>=0; j++) {
          
          //Image current = grid[i][j].getImage();
          //g.drawImage(current, x, y, Math.round(current.getWidth(null)*scale), Math.round(current.getHeight(null)*scale), null); 
          g.fillRect(x, y, Math.round(width*scale), Math.round(height*scale));
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
}
