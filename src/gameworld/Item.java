package gameworld;


/**
 * Represents an item in this game.
 * 
 * @author thomsodyla1
 *
 */
public interface Item {

  //  String name;
  //  String description;


  /**
   * Returns a description of this item.
   * 
   * @return Description of the item.
   */
  public String inspect();
  //  {
  //    return toString();
  //  }

  public String toString();
  //  {
  //    return name + ": " + description;
  //  }
}
