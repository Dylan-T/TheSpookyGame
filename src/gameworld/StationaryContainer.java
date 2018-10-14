package gameworld;

import java.awt.Image;
import java.util.List;

/**
 * An immovable container.
 * @author Dylan
 */
public class StationaryContainer implements Container{
  String name;
  String description;
  List<Item> contents;
  
  
  @Override
  public String inspect() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Image getImage() {
    // TODO Auto-generated method stub
    return null;
  }

}
