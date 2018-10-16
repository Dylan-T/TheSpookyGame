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

  // Saving and Loading
  private boolean isSaved = false;
  private TilePiece[][] savedMap;

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
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    // Create the done button
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
        // Size of the map
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

    // Create the cancel button
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
        frame.setVisible(false);
      }
    });
    cancel.setVerticalTextPosition(AbstractButton.CENTER);
    cancel.setHorizontalTextPosition(AbstractButton.CENTER);

    // Adding the components to the frame
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
    map = new TilePiece[mapY][mapX];
    savedMap = new TilePiece[mapY][mapX];
    // Fill in the array for our floor/level
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        map[i][j] = new Wall();
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

    // panel.setBackground(new Color(220, 220, 220));
    this.drawingPane.addMouseListener(this);

    // Creating the menu bar
    JMenuBar mb = new JMenuBar();
    JMenu mn = new JMenu("File");

    JMenuItem m1 = new JMenuItem("New");
    m1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Act on actions pressed
        MapUtilGUI.unsavedChangesError();
        if (isSaved == false) {
          MapUtilGUI.unsavedChangesError();
          // TODO act on buttons (create new GUI if yes)
        } else if (checkChanges() == true) {
          MapUtilGUI.unsavedChangesError();
          // TODO act on buttons (create new GUI if yes)
        } else {
          mapEditor.mapEditorSizeGUI();
        }
        System.out.println("Error making new file");
      }
    });

    JMenuItem m2 = new JMenuItem("Save");
    m2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < mapY; i++) {
          for (int j = 0; j < mapX; j++) {
            savedMap[i][j] = map[i][j];
          }
        }
        isSaved = true;
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

    JMenuItem m6 = new JMenuItem("Wall");
    m6.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentObject = "wall";
      }
    });

    JMenuItem m7 = new JMenuItem("Object");
    m7.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentObject = "object";
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
    mn11.add(m4); // Adding "Room" to Add
    mn11.addSeparator();
    mn11.add(m5); // Adding "Door" to Add
    mn11.addSeparator();
    mn11.add(m6); // Adding "Wall" to Add
    mn11.addSeparator();
    mn11.add(m7); // Adding the "Object" to Add

    frame.getContentPane().add(BorderLayout.NORTH, mb);
    frame.getContentPane().add(BorderLayout.CENTER, drawingPane);
    frame.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // Iterate through the map
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        // See if the clicked position falls into a box
        if ((e.getX() >= (j * (screenWidth / mapX)))
            && (e.getX() < ((j + 1) * (screenWidth / mapX)))
            && (e.getY() >= (i * (screenHeight / mapY)))
            && (e.getY() < (i + 1) * (screenHeight / mapY))) {
          // Set to the index of the map[][]
          clickedIndexX = j;
          clickedIndexY = i;
          // Check what tool is being used
          if (currentObject.equals("floor")) {
            map[i][j] = new Floor();
          }
          if (currentObject.equals("door")) {
            map[i][j] = new Door();
          }
          if (currentObject.equals("object")) {
            if(map[i][j] instanceof Floor) {
              ObjectSelector();
            }
            else {
              //TODO have to be placed in a room/floor popup
            }
          }
          if (currentObject.equals("wall")) {
            map[i][j] = new Wall();
          }
          // Redraw the map
          drawingPane.repaint();
        }
      }
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // Not needed
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // Not needed
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // Not needed
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // Not needed

  }

  /**
   * Object selector GUI returns object to create.
   */
  public void ObjectSelector() {

    //Main Frame
    JFrame frame = new JFrame("New Object");
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null);

    //Panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5,1));

    //Button group for the buttons
    ButtonGroup buttonGroup = new ButtonGroup();

    //Button for adding "Key"
    JRadioButton keyButton = new JRadioButton("Key");
    keyButton.setActionCommand("Key");
    keyButton.setSelected(true);
    buttonGroup.add(keyButton);

    //Button for adding "Treasure"
    JRadioButton treasureButton = new JRadioButton("Treasure");
    treasureButton.setActionCommand("Treasure");
    buttonGroup.add(treasureButton);

    //Button for adding "Quest"
    JRadioButton questButton = new JRadioButton("Quest");
    questButton.setActionCommand("Quest");
    buttonGroup.add(questButton);

    //Button for adding "Decoration"
    JRadioButton decorationButton = new JRadioButton("Decoration");
    decorationButton.setActionCommand("Decoration");
    buttonGroup.add(decorationButton);

    //Button pane and buttons for continue and cancel
    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new GridLayout(1,2));
    JButton add = new JButton("Add");
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Floor piece = (Floor) map[clickedIndexY][clickedIndexX];
        if(keyButton.isSelected()) {
          piece.setObject(new Key(true));
        }
        else if(treasureButton.isSelected()) {
          piece.setObject(new Treasure(true));
        }
        else if(questButton.isSelected()) {
          piece.setObject(new Quest(false));
        }
        else if(decorationButton.isSelected()) {
          piece.setObject(new Decoration(false));
        }
        frame.setVisible(false);
        drawingPane.repaint();
      }
    });
    JButton cancel = new JButton("Cancel");
    cancel.setText("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
      }
    });

    buttonPane.add(add);
    buttonPane.add(cancel);

    //Adding Buttons to panel
    panel.add(keyButton);
    panel.add(treasureButton);
    panel.add(questButton);
    panel.add(decorationButton);
    panel.add(buttonPane);

    //Adding panel to the pane
    frame.add(panel);
    frame.setVisible(true);

  }

  /**
   * @param g
   *
   *
   */
  public void redraw(Graphics g) {
    screenWidth = (int) screenSize.getWidth();
    screenHeight = (int) screenSize.getHeight();
    // Iterate through the array and draw on the screen
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        map[i][j].draw(g, j * (screenWidth / mapX), i * (screenHeight / mapY), screenWidth / mapX,
            screenHeight / mapY);
      }
    }
  }

  /**
   * Checks for any new unsaved changes.
   *
   * @return returns true if new changes
   */
  public boolean checkChanges() {
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        if (savedMap[i][j] == null) {
          return true;
        }
        if (map[i][j] != savedMap[i][j]) {
          return true;
        }
      }
    }
    return false;

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
