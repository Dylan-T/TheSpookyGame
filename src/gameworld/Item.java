package gameworld;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an item in this game.
 * @author thomsodyla1
 *
 */
public abstract class Item {

  String name;
  String description;
  Set<Strategy> type = new HashSet<Strategy>();

  /**
   * Returns a description of this item.
   * @return Description of the item.
   */
  public String inspect() {
    return toString();
  }

  public String toString() {
    return name + ": " + description;
  }

  interface Strategy{
  }

  /**
   * Gives Moveable item behaviour.
   * @author thomsodyla1
   *
   */
  public static class Moveable implements Strategy{}

  /**
   * Gives Container item behaviour.
   * @author thomsodyla1
   */
  public static class Container implements Strategy{}

  /**
   * Gives Consumable item behaviour.
   * @author thomsodyla1
   */
  public static class Consumable implements Strategy{}

  /**
   * Gives Equipable item behaviour.
   * @author thomsodyla1
   */
  public static class Equipable implements Strategy{}
}
