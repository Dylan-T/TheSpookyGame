package gameworld;

/**
 * @author Dylan
 *
 */
public interface Usable {
  /**
   * Uses this item.
   * @param p --- player that is using the item
   * @return whether the player used the item
   */
  public boolean use(Player p);
}
