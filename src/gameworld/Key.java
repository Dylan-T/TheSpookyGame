package gameworld;

import java.awt.Image;

/**
 * A key that can be used to unlock Passages.
 * @author Dylan
 *
 */
public class Key implements Movable {
  Passage unlocks;
  String description;
  
  /**
   * Creates a key that will unlock passage p.
   * @param p --- The Passage that this key unlocks
   */
  public Key(Passage p) {
    unlocks = p;
    description = "A key that unlocks a passage";
  }
  
  @Override
  public String inspect() {
    return description;
  }

  @Override
  public Image getImage() {
    return null;
  }

}
