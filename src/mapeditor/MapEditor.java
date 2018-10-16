package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



/**
 * The map editor - creating the map of the game.
 *
 * @author dunninnath
 *
 */

public class MapEditor implements MouseListener {
  // Fields
  // Pointer to this object
  MapEditor mapEditor;
  JPanel drawingPane;

  // Map Piece
  private int mapX;
  private int mapY;
  private int screenWidth;
  private int screenHeight;
  private Dimension screenSize;
  private Location[][] map;
  // private TilePiece[][] map;

  private int clickedIndexX;
  private int clickedIndexY;

  /**
   * Creates a new map editor with the sizes.
   */
  public MapEditor() {
    mapEditor = this;
    mapEditor.mapEditorSizeGui();
  }

  /**
   * Map Constructor Size GUI.
   */
  public void mapEditorSizeGui() {
    // GUI shows use of composite designs
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
        if (t1.getText() == null || t1.getText().equals("") || t2.getText() == null
            || t2.getText().equals("")) {
          MapUtilGui.invalidArgumentError();
          return;
        }
        // Size of the map
        mapY = Integer.parseInt(t1.getText());
        mapX = Integer.parseInt(t2.getText());

        if (mapX < 0 || mapY < 0) {
          MapUtilGui.invalidArgumentError();
        } else {
          mapEditor.mapEditorGui();
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
   * Map editor GUI.
   */
  @SuppressWarnings("serial")
  public void mapEditorGui() {
    map = new Location[mapY][mapX];
    // TODO savedMap = new TilePiece[mapY][mapX];

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

    this.drawingPane.addMouseListener(this);


    JMenuItem m1 = new JMenuItem("New");
    m1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mapEditor.mapEditorSizeGui();
      }
    });

    JMenuItem m2 = new JMenuItem("Save");
    m2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MapUtilGui.generatingXml();
      }
    });

    JMenuItem m3 = new JMenuItem("Load");
    m3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        MapUtilGui.unfinishedMethodError();
      }
    });

    // Creating the menu bar
    JMenuBar mb = new JMenuBar();
    JMenu mn = new JMenu("File");
    // Adding the components
    mb.add(mn); // Adding "File" to menu bar
    mn.add(m1); // Adding "New" to File
    mn.addSeparator();
    mn.add(m2); // Adding "Save" to File
    mn.addSeparator();
    mn.add(m3); // Adding "Load" to File

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

          // Setting the clicked location
          clickedIndexX = j;
          clickedIndexY = i;

          // Create a new room
          if (map[i][j] == null) {
            Room room = new Room();
            room.roomEditorSizeGui(mapEditor);
          } else {
            MapUtilGui.unfinishedMethodError();
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
   * Sets saves the room as a location.
   *
   * @param width - of the room
   * @param height - of the room
   * @param room - 2D Array of TilePiece
   */
  public void setLocation(int width, int height, TilePiece[][] room) {
    map[clickedIndexY][clickedIndexX] = new Location(width, height, room);
    drawingPane.repaint();
  }

  /**
   * Redraw method.
   *
   * @param g - Graphics
   */
  public void redraw(Graphics g) {
    screenWidth = (int) screenSize.getWidth();
    screenHeight = (int) screenSize.getHeight();
    // Iterate through the array and draw on the screen
    for (int i = 0; i < mapY; i++) {
      for (int j = 0; j < mapX; j++) {
        if (map[i][j] == null) {
          g.setColor(new Color(225, 225, 225));
        } else {
          g.setColor(Color.WHITE);
        }
        // Draw the squares
        g.fillRect(j * (screenWidth / mapX), i * (screenHeight / mapY), screenWidth / mapX,
            screenHeight / mapY);
        g.setColor(Color.BLACK);
        g.drawRect(j * (screenWidth / mapX), i * (screenHeight / mapY), screenWidth / mapX,
            screenHeight / mapY);
      }
    }
  }

  /**
   * Main.
   *
   * @param args - Arguments for main
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MapEditor();
      }
    });
  }
}
