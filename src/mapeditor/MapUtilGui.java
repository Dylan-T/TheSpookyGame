package mapeditor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class that modifies and creates items on the map itself.
 *
 * @author Nathan
 *
 */
public class MapUtilGui {

  /**
   * Error for performing actions when there are no new instances.
   */
  public static void noInstanceError() {
    // Main Frame
    JFrame frame = new JFrame("Error");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "Please create a new instance and retry", "Error",
        JOptionPane.WARNING_MESSAGE);

  }

  /**
   * Error for inputting invalid arguments.
   */
  public static void invalidArgumentError() {
    // Main Frame
    JFrame frame = new JFrame("Error");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "That is an invalid input", "Error",
        JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Error for dissimmilar room size inputs.
   */
  public static void invalidRoomInputError() {
    // Main Frame
    JFrame frame = new JFrame("Error");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "Rooms can only be square", "Error",
        JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Error for unfinished method.
   */
  public static void unfinishedMethodError() {
    // Main Frame
    JFrame frame = new JFrame("Error");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "This method is not working yet", "Error",
        JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Error for invalid location.
   */
  public static void invalidLocationError() {
    // Main Frame
    JFrame frame = new JFrame("Error");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "Out of bounds", "Error", JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Popup for when XML is generating.
   */
  public static void generatingXml() {
    // Main Frame
    JFrame frame = new JFrame("Generating XML");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane.showMessageDialog(frame, "XML is generating\n Please Wait....", "Generating XML",
        JOptionPane.INFORMATION_MESSAGE);
  }

}
