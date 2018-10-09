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

  @Override
  public Image getImage() {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("man2.jpg"));
    } catch (IOException e) {
      System.out.println("Image is broken");
    }
    return img;
  }

}
