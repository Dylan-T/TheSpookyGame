package renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
   * sets up the graphics for the items and exits to be drawn from
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
    
    //System.out.println(graphics);
    
    g.drawRect(50, 50, 50, 50);
    
    Item[][] grid = room.getGrid();
    int x;
    
    if(dir == Direction.NORTH) {
      for(int i = 0; i< grid.length; i++) {
        for(int j = 0; j< grid[0].length; j++) {
          
        }
      }
    }
    
    if(dir == Direction.SOUTH) {
      for(int i = grid.length-1; i>= 0; i++) {
        for(int j = grid[0].length-1; j>=0; j++) {
          
        }
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
