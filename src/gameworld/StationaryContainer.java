package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An immovable container.
 *
 * @author Dylan
 */
public class StationaryContainer extends Item {
  private List<Item> contents;

  /**
   * Creates a new container initializing it with no contents.
   * 
   * @param name
   *          name of the container
   * @param description
   *          description of the container
   * @param imagePath
   *          a file path to an image of the container
   */
  public StationaryContainer(String name, String description, String imagePath) {
    super(name, description, imagePath);
    contents = new ArrayList<Item>();
  }

  /**
   * Adds an item to this containers contents.
   * 
   * @param i
   *          item to be added to the container
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
  public boolean canPickup() {
    return false;
  }

  @Override
  public boolean use(Player p) {
    return false;
  }

  /**
   * Gets the Item contents of this container.
   * 
   * @return the contents of this container
   */
  public Collection<Item> getContents() {
    return contents;
  }

}
