package mapeditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class that modifies and creates items on the map itself.
 *
 * @author Nathan
 *
 */
public class MapUtilGUI {

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
   * Error for inputting invalid arguments
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
   * Error for unsaved changes.
   */
  public static void unsavedChangesError() {
    // Main Frame
    JFrame frame = new JFrame("Unsaved Changes");
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    JOptionPane optionPane = new JOptionPane("You have unsaved changes.\n" +"Would you like to continue?",
        JOptionPane.QUESTION_MESSAGE,
        JOptionPane.YES_NO_OPTION);



  }

}
