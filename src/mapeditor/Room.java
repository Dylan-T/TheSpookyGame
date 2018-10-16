package mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class for creating rooms.
 *
 * @author dunninnath
 *
 */
public class Room implements MouseListener {
  // Pointer to objects
  private Room room;
  private MapEditor mapEditor;
  private int roomWidth;
  private int roomHeight;
  private int roomWidthBorder;
  private int roomHeightBorder;

  // Fields
  private String currentObject = "floor";
  private int screenWidth;
  private int screenHeight;
  private Dimension screenSize;
  private TilePiece[][] currentRoom;

  private JPanel drawingPane;
  private int clickedIndexX;
  private int clickedIndexY;

  /**
   * Creates the new room creator GUI.
   *
   * @param mapEditor
   *          - Map Editor that called this object
   */
  public void roomEditorSizeGui(MapEditor mapEditor) {
    // Reference to this room
    this.room = this;
    this.mapEditor = mapEditor;

    // Main Frame
    JFrame frame = new JFrame("New Room");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(5, 0));
    frame.setSize(250, 250);
    frame.setLocationRelativeTo(null);

    // Creating the label
    JLabel label = new JLabel("New Room", SwingConstants.CENTER);
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

    // Create the done button
    JButton create = new JButton("Done");
    create.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Error Checks
        if (t1.getText() == null || t1.getText().equals("") || t2.getText() == null
            || t2.getText().equals("")) {
          MapUtilGui.invalidArgumentError();
          return;
        }
        // Size of the room
        roomHeight = Integer.parseInt(t1.getText());
        roomWidth = Integer.parseInt(t2.getText());

        // Safety Check
        if (roomWidth <= 0 || roomHeight <= 0) {
          MapUtilGui.invalidArgumentError();
        }
        if (roomWidth != roomHeight) {
          MapUtilGui.invalidRoomInputError();
        }

        // Assigning values to create the room
        roomWidthBorder = roomWidth + 2;
        roomHeightBorder = roomHeight + 2;

        room.roomEditor();
        frame.setVisible(false);

      }
    });
    create.setVerticalTextPosition(AbstractButton.CENTER);
    create.setHorizontalTextPosition(AbstractButton.CENTER);

    // Create the cancel button
    JButton cancel = new JButton("Cancel");
    cancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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
   * Room Editor GUI.
   */
  public void roomEditor() {
    // Initialise the room size and to floors
    currentRoom = new TilePiece[roomHeightBorder][roomWidthBorder];
    // Fill in the tiles
    for (int i = 0; i < roomHeightBorder; i++) {
      for (int j = 0; j < roomWidthBorder; j++) {
        currentRoom[i][j] = new Wall();
      }
    }

    for (int i = 1; i < roomHeight + 1; i++) {
      for (int j = 1; j < roomWidth + 1; j++) {
        currentRoom[i][j] = new Floor();
      }
    }

    // Creating the main frame
    JFrame frame = new JFrame("Room");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(1000, 1000);
    frame.setLocationRelativeTo(null);

    // Creating the panel
    this.drawingPane = new JPanel() {
      /**
       * Auto-Generated HashCode.
       */
      private static final long serialVersionUID = -2501107278107126916L;

      public void paint(Graphics g) {
        screenSize = this.getSize();
        redraw(g);
      }
    };

    // panel.setBackground(new Color(220, 220, 220));
    this.drawingPane.addMouseListener(this);

    // Creating the menu items
    JMenu mn = new JMenu("File");

    JMenuItem done = new JMenuItem("Done");
    done.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Parse items to set location and save items in the room
        mapEditor.setLocation(roomWidth, roomHeight, currentRoom);
        frame.setVisible(false);
      }
    });
    mn.add(done);

    JMenuItem m4 = new JMenuItem("Floor");
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

    // Creating the menu bar and items
    JMenuBar mb = new JMenuBar();
    JMenu mn1 = new JMenu("Edit");
    JMenu mn11 = new JMenu("Add");

    // Adding the components
    mb.add(mn); // Adding "Done" to menu bar
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

  /**
   * Object selector GUI returns object to create.
   */
  public void objectSelector() {

    // Main Frame
    JFrame frame = new JFrame("New Object");
    frame.setSize(300, 300);
    frame.setLocationRelativeTo(null);

    // Panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 2));

    // Button group for the buttons
    ButtonGroup buttonGroup = new ButtonGroup();

    // Button for adding "Key"
    JRadioButton keyButton = new JRadioButton("Key");
    keyButton.setActionCommand("Key");
    keyButton.setSelected(true);
    buttonGroup.add(keyButton);

    // Button for adding "Treasure"
    JRadioButton treasureButton = new JRadioButton("Treasure");
    treasureButton.setActionCommand("Treasure");
    buttonGroup.add(treasureButton);

    // Button for adding "Quest"
    JRadioButton questButton = new JRadioButton("Quest");
    questButton.setActionCommand("Quest");
    buttonGroup.add(questButton);

    // Button for adding "Decoration"
    JRadioButton decorationButton = new JRadioButton("Decoration");
    decorationButton.setActionCommand("Decoration");
    buttonGroup.add(decorationButton);

    // Button pane and buttons for continue and cancel
    JPanel buttonPane = new JPanel();
    buttonPane.setLayout(new GridLayout(1, 2));
    JButton add = new JButton("Add");
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Floor piece = (Floor) currentRoom[clickedIndexY][clickedIndexX];
        if (keyButton.isSelected()) {
          piece.setObject(new Key(true));
        } else if (treasureButton.isSelected()) {
          piece.setObject(new Treasure(true));
        } else if (questButton.isSelected()) {
          piece.setObject(new Quest(false));
        } else if (decorationButton.isSelected()) {
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

    // Adding Buttons to panel
    panel.add(keyButton);
    panel.add(treasureButton);
    panel.add(questButton);
    panel.add(decorationButton);
    panel.add(buttonPane);

    // Adding panel to the pane
    frame.add(panel);
    frame.setVisible(true);

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // Iterate through the map
    for (int i = 0; i < roomHeightBorder; i++) {
      for (int j = 0; j < roomWidthBorder; j++) {
        // See if the clicked position falls into a box
        if ((e.getX() >= (j * (screenWidth / roomWidthBorder)))
            && (e.getX() < ((j + 1) * (screenWidth / roomWidthBorder)))
            && (e.getY() >= (i * (screenHeight / roomHeightBorder)))
            && (e.getY() < (i + 1) * (screenHeight / roomHeightBorder))) {
          // Setting clicked index
          clickedIndexY = i;
          clickedIndexX = j;
          // Check to see if its in the room zone
          // Check what tool is being used
          if ((i > 0 && i < roomHeight + 1) && (j > 0 && j < roomWidth + 1)) {
            if (currentObject.equals("object")) {
              objectSelector();
            } else if (currentObject.equals("floor")) {
              currentRoom[i][j] = new Floor();
            } else {
              MapUtilGui.invalidLocationError();
            }
          } else if (((i == 0 || i == roomHeight + 1) && (j > 0 && j < roomWidth + 1))
              || ((i > 0 && i < roomHeight + 1) && (j == 0 || j == roomWidth + 1))) {
            if (currentObject.equals("door")) {
              currentRoom[i][j] = new Door();
            } else {
              MapUtilGui.invalidLocationError();
            }
            //Broken logic
          } else if (((i == 0 || i == roomHeight + 1) && (j >= 0 && j <= roomWidth + 1))
              || ((j == 0 || j == roomWidth + 1) && (i >= 0 && i <= roomHeight + 1))) {
            if (currentObject.equals("wall")) {
              currentRoom[i][j] = new Wall();
            } else {
              MapUtilGui.invalidLocationError();
            }
          } else {
            MapUtilGui.invalidLocationError();
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
   * Redraw Method.
   *
   * @param g
   *          - Graphics object
   */
  public void redraw(Graphics g) {
    screenWidth = (int) screenSize.getWidth();
    screenHeight = (int) screenSize.getHeight();
    // Iterate through the array and draw on the screen
    for (int i = 0; i < roomHeightBorder; i++) {
      for (int j = 0; j < roomWidthBorder; j++) {
        currentRoom[i][j].draw(g, j * (screenWidth / roomWidthBorder),
            i * (screenHeight / roomHeightBorder), screenWidth / roomWidthBorder,
            screenHeight / roomHeightBorder);
      }
    }
  }

}
