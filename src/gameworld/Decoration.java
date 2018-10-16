package gameworld;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Decorative item that has no use except cosmetically
 * @author Dylan
 *
 */
public class Decoration extends Item {


  /**
   * @param name
   * @param description
   * @param imagePath
   */
  public Decoration(String name, String description, String imagePath) {
    super(name, description, imagePath);
  }

  @Override
  public boolean canPickup() {
    return false;
  }

  @Override
  public boolean use(Player p) {
    return false;
  }

}
