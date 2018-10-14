package gameworld;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A movable item needed to progress in the game.
 * It may provide a bonus to the players score, currency or allow certain actions.
 * @author thomsodyla1
 *
 */
public class Treasure implements Movable {

  private Image image;
  private String name;
  private String description;

  /**
   * @param name of the item
   * @param description of the item
   * @param imagePath where the image to represent this item is
   */
  public Treasure(String name, String  description, String imagePath) {
    this.name = name;
    this.description = description;
    try {
      image = ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      System.out.println("Image read fail");
    }
  }

  @Override
  public String inspect() {
    return name + ": " + description;
  }

  @Override
  public Image getImage() {
    return image;
  }

}
