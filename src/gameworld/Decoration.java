package gameworld;

/**
 * Decorative item that is purely cosmetic.
 *
 * @author Dylan
 *
 */
public class Decoration extends Item {

  /**
   * Creates a new decoration item.
   *
   * @param name
   *          of the decoration item
   * @param description
   *          of the decoration item
   * @param imagePath
   *          to the image the decoration will be drawn as
   */
  public Decoration(String name, String description, String imagePath) {
    super(name, description, imagePath);
  }

  @Override
  public boolean canPickup() {
    return false;
  }

  @Override
  public boolean use(Player p) {
    return false;
  }

}
