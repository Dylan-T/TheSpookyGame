package gameworld;

/**
 * An item that can be equipped by the player.
 * @author thomsodyla1
 *
 */
public interface Equipable extends Item {
  /**
   * equip this item.
   * @param p player to equip this item
   */
  void equip(Player p);
}
