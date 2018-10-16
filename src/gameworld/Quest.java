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
   * Creates a quest item. This is an item that requires the player to collect the
   * requirements before it can be complete.
   * 
   * @param name
   *          Name of the item
   * @param description
   *          Description of the item
   * @param imagePath
   *          File path to the image representation of this item
   * @param requirements
   *          The treasures required to complete this quest
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
   *          the player attempting to complete this quest
   * @return true if the player has complete the quest.
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
   * Check if the quest is complete.
   * 
   * @return if the quest has been complete
   */
  public boolean isComplete() {
    return complete;
  }

  /**
   * Gets the treasures required to complete this quest.
   * 
   * @return the Treasures required to complete this quest.
   */
  public Collection<Treasure> getRequirements() {
    return requirements;
  }
}
