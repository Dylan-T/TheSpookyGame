package mapeditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class that modifies and creates items on the map itself.
 * @author Nathan
 *
 */
public class MapUtil {
  
  /**
   * Creates a new room in the map editor
   */
  public static void createNewRoom() {
    //Main Frame
    JFrame frame = new JFrame("New Room");
    frame.setLayout(new GridLayout(5,0));
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    //Creating the label
    JLabel label = new JLabel("New Room", SwingConstants.CENTER);
    label.setText("Select Room Size");

    //Panel containing height and fields
    JPanel p1 = new JPanel();
    JLabel height = new JLabel();
    height.setText("Height:");
    JTextField t1 = new JTextField(2);
    p1.add(height);
    p1.add(t1);

    //Panel containing width and fields
    JPanel p2 = new JPanel();
    JLabel width = new JLabel();
    width.setText("Width:");
    JTextField t2 = new JTextField(2);
    p2.add(width);
    p2.add(t2);

    JButton create = new JButton("Done");
    create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO: Create a check to ensure that it does not print on null, create a popup?
        int ht = Integer.parseInt(t1.getText());
        int wd = Integer.parseInt(t2.getText());
        System.out.print(ht + "," + wd);
        //TODO: Create the actual room
        frame.setVisible(false);
      }
    });
    create.setVerticalTextPosition(AbstractButton.CENTER);
    create.setHorizontalTextPosition(AbstractButton.CENTER);

    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
      }
    });
    cancel.setVerticalTextPosition(AbstractButton.CENTER);
    cancel.setHorizontalTextPosition(AbstractButton.CENTER);


    frame.add(label);
    frame.add(p1);
    frame.add(p2);
    frame.add(create);
    frame.add(cancel);

    frame.setVisible(true);
  }

}
