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

  @Override
  public Image getImage() {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("entrance2.jpg"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return img;
  }

}
