package gameworld;

import java.util.Collection;

/**
 * A quest item that must be used to win the game.
 * 
 * @author Dylan
 *
 */
public class Quest extends Item {
  private boolean complete;
  private Collection<Treasure> requirements;

  /**
   * @param name
   * @param description
   * @param imagePath
   * @param requirements
   */
  public Quest(String name, String description, String imagePath,
      Collection<Treasure> requirements) {
    super(name, description, imagePath);
    this.requirements = requirements;
    complete = false;
  }

  /**
   * Attempts to complete the quest item, checking you have all the required
   * treasures.
   * 
   * @param p
   * @return skdf
   */
  public boolean use(Player p) {
    for (Treasure t : requirements) {
      if (!p.getInventory().contains(t)) {
        return false;
      }
    }
    complete = true;
    return true;
  }

  @Override
  public boolean canPickup() {
    return false;
  }

  /**
   * Check if the quest is complete
   * 
   * @return if the quest has been complete
   */
  public boolean isComplete() {
    return complete;
  }

  /**
   * @return the Treasures required to complete this quest.
   */
  public Collection<Treasure> getRequirements() {
    return requirements;
  }
}
