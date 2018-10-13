package gameworld;

import java.awt.Image;

/**
 * Represents an item in this game.
 * 
 * @author thomsodyla1
 *
 */
public interface Item {

  //  String name;
  //  String description;
  


  /**
   * Returns a description of this item.
   * 
   * @return Description of the item.
   */
  public String inspect();

  public String toString();
  
  /**
   * Get's the items image representation.
   * @return Item's sprites image.
   */
  public Image getImage();
  
  public boolean equals(Object o);
}
