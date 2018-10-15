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
  private String name;
  private String description;
  private Image image;
  
  /**
   * Creates a new decoration item.
   * @param name of the item
   * @param description of the item
   * @param imagePath path to the image that represents this item.
   */
  public Decoration(String name, String description, String imagePath) {
    this.name = name;
    this.description = description;
    
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      System.out.println("Image read failed");
    }
  }
  
  @Override
  public String inspect() {
    return name + ": " + description;
  }
  
  public String toString() {
    return name;
  }

  //Getters
  
  @Override
  public Image getImage() {
    return image;
  }
  
  /**
   * @return the name of this decoration
   */
  public String getName() {
    return name;
  }
  
  /**
   * @return the description of this decoration
   */
  public String getDescription() {
    return description;
  }

}
