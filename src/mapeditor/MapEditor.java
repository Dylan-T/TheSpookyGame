package mapeditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * The map editor - creating the map of the game
 *
 * @author dunninnath
 *
 */

public class MapEditor implements MouseListener {
  // Fields
  //Pointer to this object
  MapEditor mapEditor;
  
  // Storing mouse locations
  private double xPressed;
  private double yPressed;
  private boolean onScreen = false;

  // Map Piece
  private int mapX;
  private int mapY;
  private TilePiece[][] map;

  /**
   * Creates a new map editor with the sizes
   * 
   */
  public MapEditor() {
    mapEditor = this;
    mapEditor.mapEditorSizeGUI();
  }
  
  /**
   * Map Constructor Size GUI
   */
  public void mapEditorSizeGUI() {
    // Main Frame
    JFrame frame = new JFrame("New Map Editor");
    frame.setLayout(new GridLayout(5, 0));
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    // Creating the label
    JLabel label = new JLabel("New Map Editor", SwingConstants.CENTER);
    label.setText("Select Room Size");

    // Panel containing height and fields
    JPanel p1 = new JPanel();
    JLabel width = new JLabel();
    width.setText("Width:");
    JTextField t1 = new JTextField(2);
    p1.add(width);
    p1.add(t1);

    // Panel containing width and fields
    JPanel p2 = new JPanel();
    JLabel height = new JLabel();
    height.setText("Height:");
    JTextField t2 = new JTextField(2);
    p2.add(height);
    p2.add(t2);

    JButton create = new JButton("Done");
    create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: Create a check to ensure that it does not print on null, create a
        // popup?
        mapY = Integer.parseInt(t1.getText());
        mapX = Integer.parseInt(t2.getText());
        mapEditor.mapEditorGUI();        
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
   * Map editor GUI 
   */
  public void mapEditorGUI() {
    map = new TilePiece[mapX][mapY];
 // Fill in the array for our floor/level
    for (int i = 0; i < mapX; i++) {
      for (int j = 0; j < mapY; j++) {
        map[i][j] = new WallPiece();
      }
    }
    // Creating the main frame
    JFrame frame = new JFrame("Map");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);
    frame.setLocationRelativeTo(null);

    // Creating the panel
    @SuppressWarnings("serial")
    JPanel panel = new JPanel() {

      public void paint(Graphics g) {
        Dimension screenSize = frame.getSize();
        for (int i = 0; i < mapX; i++) {
          for (int j = 0; j < mapY; j++) {
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            map[i][j].draw(g, i * (width / mapX), j * (height / mapY), width / mapX, height / mapY);
          }
        }
      }
    };

    panel.setBackground(new Color(220, 220, 220));
    panel.addMouseListener(this);

    // Creating the menu bar
    JMenuBar mb = new JMenuBar();
    JMenu mn = new JMenu("File");

    JMenuItem m1 = new JMenuItem("New");
    m1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MapUtilGUI.unsavedChangesError();
        MapUtilGUI.createNewRoom();

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

      }
    });

    JMenuItem m4 = new JMenuItem("Room");
    m4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MapUtilGUI.createNewRoom();
      }
    });

    JMenuItem m5 = new JMenuItem("Door");
    m5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: Create new door
      }
    });

    JMenuItem m6 = new JMenuItem("Object");
    m6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO: Create new Object
      }
    });

    // Toolkit Menu Bar
    JMenuBar toolkit = new JMenuBar();
    // TODO: Check whether this is required

    // Adding the components
    mb.add(mn); // Adding "File" to menu bar
    mn.add(m1); // Adding "New" to File
    mn.addSeparator();
    mn.add(m2); // Adding "Save" to File
    mn.addSeparator();
    mn.add(m3); // Adding "Load" to File
    mb.add(mn1); // Adding "Edit" to menu bar
    mn1.add(mn11); // Adding "Add" to Edit
    mn1.addSeparator();
    mn1.add(undo); // Adding "Undo" to Edit
    mn11.add(m4); // Adding "Room" to Add
    mn11.addSeparator();
    mn11.add(m5); // Adding "Door" to Add
    mn11.addSeparator();
    mn11.add(m6); // Adding "Object" to Add

    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, panel);
    frame.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Mouse Clicked at x: " + e.getX() + ", y: " + e.getY());
    for (int i = 0; i < 20; i++) {
      // TODO
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.xPressed = e.getX();
    this.yPressed = e.getY();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO: What to draw or do
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Main class - run for testing purposes
   *
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
