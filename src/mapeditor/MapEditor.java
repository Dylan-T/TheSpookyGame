package mapeditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * The map editor - creating the map of the game
 *
 * @author dunninnath
 *
 */

public class MapEditor {
  //Fields
  //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


  /**
   * Construct the new map editor GUI
   */

  MapEditor(){
    //Creating the main frame
    JFrame frame = new JFrame("Map");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);
    frame.setLocationRelativeTo(null);

    //Creating the panel
    JPanel panel = new JPanel();
    panel.setBackground(new Color(220,220,220));

    //Creating the menu bar
    JMenuBar mb = new JMenuBar();
    JMenu mn = new JMenu("File");

    JMenuItem m1 = new JMenuItem("New");
    m1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

      }
    });

    JMenuItem m2 = new JMenuItem("Save");
    m2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
      }
    });

    JMenuItem m3 = new JMenuItem("Load");
    m3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
      }
    });

    JMenu mn1 = new JMenu("Edit");
    JMenu mn11 = new JMenu("Add");
    JMenuItem undo = new JMenu("Undo");
    undo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        createNewRoom();
      }
    });

    JMenuItem m4 = new JMenuItem("Room");
    m4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        createNewRoom();
      }
    });

    JMenuItem m5 = new JMenuItem("Door");
    m5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO: Create new door
      }
    });

    JMenuItem m6 = new JMenuItem("Object");
    m6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO: Create new Object
      }
    });

    //Toolkit Menu Bar
    JMenuBar toolkit = new JMenuBar();



    //Adding the components
    mb.add(mn); //Adding "File" to menu bar
    mn.add(m1); //Adding "New" to File
    mn.addSeparator();
    mn.add(m2); //Adding "Save" to File
    mn.addSeparator();
    mn.add(m3); //Adding "Load" to File
    mb.add(mn1);  //Adding "Edit" to menu bar
    mn1.add(mn11);  //Adding "Add" to Edit
    mn1.addSeparator();
    mn1.add(undo);  //Adding "Undo" to Edit
    mn11.add(m4); //Adding "Room" to Add
    mn11.addSeparator();
    mn11.add(m5); //Adding "Door" to Add
    mn11.addSeparator();
    mn11.add(m6); //Adding "Object" to Add


    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, panel);

    frame.setVisible(true);
  }

  /**
   * Creates a new room in the map editor
   */
  public void createNewRoom() {
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

  /**
   * Main class - run for testing purposes
   * @param args
   */
  public static void main(String args[]) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MapEditor();
      }
    });
  }
}
