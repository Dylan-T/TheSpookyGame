package renderer;

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
  
  Graphics2D graphics;
  
  /**
   * @param graphics
   * sets up the graphics for the items and exits to be drawn from
   */
  public Renderer(Graphics2D graphics) {
    this.graphics = graphics;
  }
  
  /**
   * @param room
   * @param dir
   * @param g 
   */
  public void redraw(Location room, GameWorld.Direction dir, Graphics2D g) {
    
    Item[][] grid = room.getGrid();
    int x;
    
    if(dir == Direction.NORTH) {
      for(int i = 0; i< grid.length; i++) {
        for(int j = 0; j< grid[0].length; j++) {
          
        }
      }
    }
    
  }
}
