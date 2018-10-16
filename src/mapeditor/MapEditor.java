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
  // Pointer to this object
  MapEditor mapEditor;
  JPanel drawingPane;

  // Storing mouse locations
  private int clickedIndexX;
  private int clickedIndexY;
  private String currentObject = "wall";

  // Map Piece
  private int mapX;
  private int mapY;
  private int screenWidth;
  private int screenHeight;
  private Dimension screenSize;
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
    label.setText("Select Map Size");

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

    //Create the done button
    JButton create = new JButton("Done");
    create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Error Checks
        if (t1.getText().equals(null) || t1.getText().equals("") || t2.getText().equals(null)
            || t2.getText().equals("")) {
          MapUtilGUI.invalidArgumentError();
          return;
        }
        //Size of the map
        mapY = Integer.parseInt(t1.getText());
        mapX = Integer.parseInt(t2.getText());

        if (mapX < 0 || mapY < 0) {
          MapUtilGUI.invalidArgumentError();
        } else {
          mapEditor.mapEditorGUI();
          frame.setVisible(false);
        }
      }
    });
    create.setVerticalTextPosition(AbstractButton.CENTER);
    create.setHorizontalTextPosition(AbstractButton.CENTER);

    //Create the cancel button
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
      }
    });
    cancel.setVerticalTextPosition(AbstractButton.CENTER);
    cancel.setHorizontalTextPosition(AbstractButton.CENTER);

    //Adding the components to the frame
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
  @SuppressWarnings("serial")
  public void mapEditorGUI() {
    map = new TilePiece[mapX][mapY];
    // Fill in the array for our floor/level
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        map[i][j] = new WallPiece();
      }
    }


    // Creating the main frame
    JFrame frame = new JFrame("Map");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);
    frame.setLocationRelativeTo(null);

    // Creating the panel
    this.drawingPane = new JPanel() {
      public void paint(Graphics g) {
        screenSize = this.getSize();
        redraw(g);
      }
    };

    //panel.setBackground(new Color(220, 220, 220));
    this.drawingPane.addMouseListener(this);

    // Creating the menu bar
    JMenuBar mb = new JMenuBar();
    JMenu mn = new JMenu("File");

    JMenuItem m1 = new JMenuItem("New");
    m1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MapUtilGUI.unsavedChangesError();
        //TODO Act on actions pressed
        mapEditorSizeGUI();

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

    JMenuItem m4 = new JMenuItem("Room");
    m4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentObject = "floor";
      }
    });

    JMenuItem m5 = new JMenuItem("Door");
    m5.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentObject = "door";
      }
    });

    JMenuItem m6 = new JMenuItem("Object");
    m6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentObject = "pickupable";
      }
    });

    // Adding the components
    mb.add(mn); // Adding "File" to menu bar
    mn.add(m1); // Adding "New" to File
    mn.addSeparator();
    mn.add(m2); // Adding "Save" to File
    mn.addSeparator();
    mn.add(m3); // Adding "Load" to File
    mb.add(mn1); // Adding "Edit" to menu bar
    mn1.add(mn11); // Adding "Add" to Edit
    //mn1.addSeparator();
    mn11.add(m4); // Adding "Room" to Add
    mn11.addSeparator();
    mn11.add(m5); // Adding "Door" to Add
    mn11.addSeparator();
    mn11.add(m6); // Adding "Object" to Add

    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, drawingPane);
    frame.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse Clicked at x: " + e.getX() + ", y: " + e.getY());
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        if((e.getX() > (j * (screenWidth/mapX))) && (e.getX() < ((j+1) * (screenWidth/mapX)))
            && (e.getY() > (i * (screenHeight/mapY))) && (e.getY() < (i+1) * (screenHeight/mapY))) {
          clickedIndexX = j;
          clickedIndexY = i;
          if(currentObject.equals("floor")) {
            map[i][j] = new Floor();
          }
          if(currentObject.equals("door")) {
            map[i][j] = new Door();
          }
          if(currentObject.equals("pickupable")) {
            map[i][j] = new Pickupable();
          }
          if(currentObject.equals("wall")) {
            map[i][j] = new WallPiece();
          }
          drawingPane.repaint();
          System.out.println(clickedIndexX);
          System.out.println(clickedIndexY);
          //TODO: Not working on the last index of each x and y
        }
      }
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    //Not needed
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //Not needed
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //Not needed
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //Not needed

  }

  /**
   * @param g
   *
   *
   */
  public void redraw(Graphics g) {
    screenWidth = (int) screenSize.getWidth();
    screenHeight = (int) screenSize.getHeight();
    System.out.println("draw");
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        map[i][j].draw(g, j * (screenWidth / mapX), i * (screenHeight / mapY), screenWidth / mapX, screenHeight / mapY);
        System.out.println("drawing row: " + i + " col: " + j +" element: " + map[i][j].toString());
      }
    }
  }

  /**
   * Main
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
