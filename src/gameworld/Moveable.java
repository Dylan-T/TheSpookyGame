package gameworld;

/**
 * Interface represents items that can be picked up.
 * @author thomsodyla1
 *
 */
public interface Moveable extends Item {

  /**
   * Pick's up this item giving it to the given player.
   * @param p Player that is picking up the item.
   */
  void pickUp(Player p);

}
