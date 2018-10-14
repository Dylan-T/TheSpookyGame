package gameworld;

import java.util.List;

/**
 * A quest item that must be used to win the game.
 * @author Dylan
 *
 */
public class Quest implements Usable {
  
  private boolean complete;
  private List<Treasure> requirements;
  
  /**
   * Attempts to complete the quest item, checking you have all the required treasures.
   */
  public boolean use(Player p) {
    for(Treasure t: requirements) {
      if(!p.inventory.contains(t)) {
        return false;
      }
    }
    complete = true;
    return true;
  }
  
  /**
   * Check if the quest is complete
   * @return if the quest has been complete
   */
  public boolean isComplete() {
    return complete;
  }
}
