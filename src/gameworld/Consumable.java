package gameworld;

/**
 * Interface for items that can be consumed.
 * @author thomsodyla1
 *
 */
public interface Consumable extends Item {

  /**
   * Consume this item.
   * @param p player that is to consume item
   *
   */
  public void consume(Player p);
}
