package application;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author user1
 *
 */
public class BackgroundImage extends JPanel{
  
  private Image img;
  private Dimension size;
  
  /**
   * @param img
   */
  public BackgroundImage(String img) {
    this(new ImageIcon(img).getImage());
  }
  
  /**
   * @param img 
   * @param str
   */
  
  public BackgroundImage(Image img) {
    this.img = img;
    size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
  
  /**
   * @param g
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, null); //could change this 
  }
  
  /**
   * @return the size of the image.
   */
  public Dimension getDimension() {
    return size;
  }
}
