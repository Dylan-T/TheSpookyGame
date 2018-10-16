package gameworld;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents an item in this game.
 *
 * @author thomsodyla1
 *
 */
public abstract class Item {
  protected String name;
  protected String description;
  protected Image image;

  /**
   * Creates an Item and initializes its fields.
   * @param name --- the name of the item
   * @param description --- A short description of this item
   * @param imagePath --- The file path to an image to represent this item
   */
  public Item(String name, String description, String imagePath) {
    this.name = name;
    this.description = description;

    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      System.out.println("Image read failed");
    }
    this.image = image;
  }

  public String toString() {
    return name + ": " + description;
  }

  /**
   * Returns String that will appear upon inspection of this item.
   *
   * @return Some form of description of this items state.
   */
  public String inspect() {
    return name + ": " + description;
  }

  /**
   * Get's the items image representation.
   *
   * @return Item's sprites image.
   */
  public Image getImage() {
    return image;
  }

  /**
   * Get's the items name.
   *
   * @return Item's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Get's the items description.
   *
   * @return Item's description.
   */
  public String getDescription() {
    return description;
  }

  // Abstract methods

  /**
   * Checks if this item can be picked up.
   * @return True if the item can be picked up.
   */
  public abstract boolean canPickup();

  /**
   * The player uses the item returning true if
   * the item had any effect.
   * @param p the player that is using this item
   * @return if the player successfully used the item.
   */
  public abstract boolean use(Player p);
}
