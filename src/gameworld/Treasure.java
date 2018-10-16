package gameworld;

/**
 * A movable item needed to progress in the game. It may provide a bonus to the
 * players score, currency or allow certain actions.
 *
 * @author thomsodyla1
 *
 */
public class Treasure extends Item {

  /**
   * Creates a new treasure item specifying it's name, description and image.
   * @param name
   *          The name of the item
   * @param description
   *          The description of the item
   * @param imagePath
   *          File path where the image to represent this item is
   */
  public Treasure(String name, String description, String imagePath) {
    super(name, description, imagePath);
  }

  @Override
  public boolean canPickup() {
    return true;
  }

  @Override
  public boolean use(Player p) {
    return false;
  }

}
