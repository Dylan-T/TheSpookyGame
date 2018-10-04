package gameworld;

/**
 * Decorative item that has no use except cosmetically
 * @author Dylan
 *
 */
public class Decoration implements Item {
  String name;
  String description;
  
  
  /**
   * Creates a new decoration item.
   * @param name of the item
   * @param description of the item
   */
  public Decoration(String name, String description) {
    this.name = name;
    this.description = description;
  }
  
  @Override
  public String inspect() {
    return name + ": " + description;
  }
  
  public String toString() {
    return name;
  }

}
