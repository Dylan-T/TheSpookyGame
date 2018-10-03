package gameworld;

/**
 * A movable item needed to progress in the game.
 * It may provide a bonus to the players score, currency or allow certain actions.
 * @author thomsodyla1
 *
 */
public class Treasure implements Movable {


  String name;
  String description;
  int value;

  /**
   * @param name of the item
   * @param description of the item
   * @param value of the item to be added to players score
   */
  public Treasure(String name, String  description, int value) {
    this.value = value;
    this.name = name;
    this.description = description;
  }

  @Override
  public String inspect() {
    return name + ": " + description;
  }

}
