package gameworld;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An immovable container.
 * @author Dylan
 */
public class StationaryContainer extends Item{
  private List<Item> contents;
  
  
  /**
   * @param name
   * @param description
   * @param imagePath
   */
  public StationaryContainer(String name, String description, String imagePath) {
    super(name, description, imagePath);
    contents = new ArrayList<Item>();
  }
  
  /**
   * @param i item to be added to the container
   * @return if the item was successfully added.
   */
  public boolean addItem(Item i) {
    if (i == null) {
      return false;
    }
    contents.add(i);
    return true;
  }
  
  @Override
  public String inspect() {
    return "";
  }

  @Override
  public boolean canPickup() {
    return false;
  }

  @Override
  public boolean use(Player p) {
    return false;
  }
  
  /**
   * @return the contents of this container
   */
  public Collection<Item> getContents(){
    return contents;
  }

}
