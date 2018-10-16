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
   * @param name
   *          of the item
   * @param description
   *          of the item
   * @param imagePath
   *          where the image to represent this item is
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
