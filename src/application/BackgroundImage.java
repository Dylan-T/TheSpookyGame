package application;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This class creates an backgorund image object that is to be used by the title
 * screen. This takes in a string or an image and is used to put this background
 * image on a panel so it can be shown on a JFrame.
 *
 * @author Armaan Chandra.
 *
 *
 */
@SuppressWarnings("serial")
public class BackgroundImage extends JPanel {

  private Image img;
  private Dimension size;

  /**
   * this is a constuctor for when a background image is trying to be made with
   * just a string.
   *
   * @param img
   *          the image name string
   */
  public BackgroundImage(String img) {
    this(new ImageIcon(img).getImage());
  }

  /**
   * this is the constructor for when an image is made with an image.
   *
   * @param img
   *
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
   * method to actually paint the image onto a JPanel.
   *
   * @param g
   *          graphics of the Jpanel
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, null); // could change this
  }
}
