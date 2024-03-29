package application;

import gameworld.GameWorld;
import gameworld.Item;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import persistence.XmlReader;
import persistence.XmlSaver;

import renderer.Renderer;

/**
 * this is the GUI class that creates the interface which will be used by
 * multiple other classes.
 *
 * @author Armaan Chandra
 *
 */
public class Gui {

  private static GameWorld game;
  private Renderer renderWindow;

  private static JFrame frame;
  private static JComponent drawing;
  private static TitledBorder title;
  private static JPanel canvas;
  private static JPanel displayPanel;
  private static JPanel textBox;
  private static JPanel buttonPanel;
  private static JPanel extraButtons;
  private static JTextArea textWindow;
  private static JFileChooser fileChooser;
  private int height;
  private int width;
  private static File file = null;



  // DRAWING WIDTH = 1604, HEIGHT = 951

  /**
   * Launch the application.
   *
   * @param args
   *          some command line stuff
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TitleScreen title = new TitleScreen();
          title.getTitleFrame().setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application and the game world to run.
   */
  public Gui() {
    game = GameWorld.testGameWorld2();
    initialize();
  }

  /**
   * Initialize the contents of the frame. This draws the main componets lays them
   * out on the JFrame. This is responsible for creating the main GUI.
   */

  @SuppressWarnings("serial")
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("The Spooky Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    windowExit();
    frame.setVisible(true);

    width = frame.getBounds().width;
    height = frame.getBounds().height;

    // ------------HERE IS THE BUTTON PANEL------------------
    buttonPanel = new JPanel();

    buttonPanel.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        // here you should call a method that does a function when the mouse is clicked
      }
    });

    // --------------FORMATTING THE 3 MAIN PANEL COMPONENTS--------------
    displayPanel = new JPanel();
    canvas = new JPanel();

    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup()
            .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(displayPanel,
                    GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(canvas, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.RELATED).addComponent(buttonPanel,
                        GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
            .addContainerGap()));
    groupLayout
        .setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(
                    groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 135,
                                Short.MAX_VALUE)
                            .addComponent(canvas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135,
                                Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(displayPanel,
                            GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)));

    displayPanel.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        // here you should call a method that does a function when the mouse is clicked
      }
    });

    displayPanel.setLayout(new BorderLayout(0, 0));
    buttonPanel.setLayout(new BorderLayout(0, 0));
    frame.getContentPane().setLayout(groupLayout);

    // ------------MAKING THE CANVAS A DRAWING COMPONENT----------------
    drawing = new JComponent() {
      protected void paintComponent(Graphics g) {
        redraw(g);
      }

      private void redraw(Graphics g) {
        renderWindow.redraw(game.getCurrentRoom(), GameWorld.Direction.NORTH, g);
      }
    };

    renderWindow = new Renderer(drawing.getGraphics());
    drawing.setPreferredSize(new Dimension(width, height));

    drawing.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
      }
    });

    canvas.setLayout(new GridLayout(0, 1, 0, 0));
    canvas.add(drawing);

    // ------CREATING A DROP DOWN MENU WHEN CLICKING-------
    JPopupMenu popup = new JPopupMenu();
    JMenuItem pickup = new JMenuItem("Pick Up");
    pickup.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        Item[][] i = renderWindow.getGrid();
        Item item = renderWindow.isWithin(mouseX, mouseY);

        for (int a = 0; a < i.length; a++) {
          for (int b = 0; b < i[0].length; b++) {
            if (item == i[b][a]) {
              if (game.getPlayer().pickupItem(i[b][a])) {
                drawing.repaint();
              } else {
                // this should print to the text field
                // yo
              }
            }
          }
        }

        // this should also redraw the inventory pane
      }
    });

    JMenuItem inspect = new JMenuItem("Inspect");
    inspect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        drawing.repaint();
      }
    });
    popup.add(pickup);
    popup.add(inspect);

    MouseListener popupListener = new PopupListener(popup);
    drawing.addMouseListener(popupListener);

    // --------CREATING THE NAVIGATION BUTTTONS---------------
    JButton west = new JButton("<");
    west.setPreferredSize(new Dimension(60, 147));
    west.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        if (game != null) {
          game.movePlayer(GameWorld.Direction.WEST);
          drawing.repaint();
        }
      }
    });

    JButton east = new JButton(">");
    east.setPreferredSize(new Dimension(60, 147));
    east.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        if (game != null) {
          game.movePlayer(GameWorld.Direction.EAST);
          drawing.repaint();
        }
      }
    });

    JButton north = new JButton("^");
    north.setPreferredSize(new Dimension(60, 147));
    north.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        if (game != null) {
          game.movePlayer(GameWorld.Direction.NORTH);
          drawing.repaint();
        }

      }
    });

    JButton south = new JButton("v");
    south.setPreferredSize(new Dimension(60, 147));
    south.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        if (game != null) {
          game.movePlayer(GameWorld.Direction.SOUTH); // This is the code to move the player
          drawing.repaint();
        }
      }
    });

    JButton fillerL = new JButton(" ");
    fillerL.setVisible(false);
    JButton fillerR = new JButton(" ");
    fillerR.setVisible(false);
    JButton fillerC = new JButton(" ");
    fillerC.setVisible(false);

    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.LINE_AXIS));

    west.setMargin(new Insets(0, 0, 0, 0));
    east.setMargin(new Insets(0, 0, 0, 0));
    north.setMargin(new Insets(0, 0, 0, 0));
    south.setMargin(new Insets(0, 0, 0, 0));

    JPanel navigation = new JPanel();
    navigation.setMaximumSize(new Dimension(226, 148));
    navigation.setLayout(new GridLayout(2, 3));

    navigation.add(fillerL);
    navigation.add(north);
    navigation.add(fillerR);
    navigation.add(west);
    navigation.add(south);
    navigation.add(east);

    displayPanel.add(navigation);
    displayPanel.add(Box.createRigidArea(new Dimension(7, 0)));

    // creating a button panel
    extraButtons = new JPanel();
    extraButtons.setMaximumSize(new Dimension(820, 148));
    extraButtons.setLayout(null);

    JButton drop = new JButton("Drop");
    drop.setPreferredSize(new Dimension(50, 50));
    drop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        drawing.repaint();
        // this should also redraw the inventory but i'm not even sure how things are
        // being added here
      }
    });

    JButton pickUp = new JButton("Pick Up");
    pickUp.setPreferredSize(new Dimension(50, 50));
    pickUp.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        drawing.repaint();
        // this should also redraw the inventory but i'm not even sure how things are
        // being added here
      }
    });

    JButton inspect1 = new JButton("Inspect");
    inspect1.setPreferredSize(new Dimension(50, 50));
    inspect1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        drawing.repaint();
        // this should also redraw the inventory but i'm not even sure how things are
        // being added here
      }
    });

    drop.setBounds(3, 9, 100, 60);
    pickUp.setBounds(103, 9, 100, 60);
    inspect1.setBounds(203, 9, 100, 60);
    extraButtons.add(drop);
    extraButtons.add(pickUp);
    extraButtons.add(inspect1);
    displayPanel.add(extraButtons);

    // -------CREATING THE TEXT AREA IN THE DISPLAY PANEL FOR USER
    // FEEDBACK---------------
    displayPanel.add(Box.createRigidArea(new Dimension(2, 0)));

    textBox = new JPanel();
    textBox.setMaximumSize(new Dimension(830, 150));
    textBox.setLayout(null); // position the text area using setBounds
    displayPanel.add(textBox);

    textWindow = new JTextArea();
    // textWindow.setBounds(0, 0, 828, 138);

    textWindow.setLineWrap(true);
    textWindow.setWrapStyleWord(true); // making the line up nice
    textWindow.setEditable(false); // cannot be edited

    JScrollPane scroll = new JScrollPane(textWindow);
    scroll.setBounds(0, 0, 828, 138);
    textBox.add(scroll);

    Item[] items = new Item[50];
    int count = 0;
    for (int i = 0; i < game.getCurrentRoom().getGrid().length; i++) {
      for (int j = 0; j < game.getCurrentRoom().getGrid()[0].length; j++) {
        if (game.getCurrentRoom().getGrid()[i][j] != null) {
          textWindow.append(game.getCurrentRoom().getGrid()[i][j].getDescription() + "\n");
          items[count++] = game.getCurrentRoom().getGrid()[i][j];
        }
      }
    }

    // textWindow.append(items.toString());

    // -----CREATING BORDERS FOR THE COMPONENTS AND MENUBAR-------
    createBoarders();
    createMenu();
  }

  /**
   * Creates the MenuBar for the Frame with additional functionality for the user.
   */
  public static void createMenu() {
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu mnFile = new JMenu("File");
    menuBar.add(mnFile);

    JButton buttonSave = new JButton("Save");
    mnFile.add(buttonSave);
    pressSave(buttonSave);

    JButton buttonLoad = new JButton("Load");
    mnFile.add(buttonLoad);
    pressLoad(buttonLoad);

    JMenu mnGame = new JMenu("Game");
    menuBar.add(mnGame);

    JButton newGameButton = new JButton("New Game");
    mnGame.add(newGameButton);
    pressNewGame(newGameButton);
  }

  /**
   * Helper method that does the actions of the saveButton. called when the user
   * clicks the save buttons from the menu bar.
   *
   * @param save
   *          passed from the menu bar.
   */
  private static void pressSave(JButton save) {
    fileChooser = new JFileChooser();

    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {

        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Select input file.");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // run the file chooser and check the user didn't hit cancel
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
          file = fileChooser.getSelectedFile();
          // textEditor.setText(readFile(file));
        }
        if (file != null) {
          try {
            XmlSaver.makeXml(game, file.getAbsolutePath());
          } catch (ParserConfigurationException e) {
            e.printStackTrace();
          } catch (TransformerException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  /**
   * Helper method that does the actions of the loadButton. called when the user
   * calls the load from the menu bar.
   *
   * @param load
   *          passed from the menu bar.
   */
  private static void pressLoad(JButton load) {
    load.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {

        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Select input file.");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // run the file chooser and check the user didn't hit cancel
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
          file = fileChooser.getSelectedFile();
          // textEditor.setText(readFile(file));
        }
        if (file != null) {
          try {
            // GameWorld newGame;
            game = XmlReader.loadXml(file.getAbsolutePath());
            drawing.repaint();
          } catch (ParserConfigurationException e) {
            e.printStackTrace();
          } catch (SAXException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });

  }

  /**
   * Helper method that does the actions of the newGameButton. called when the
   * user calls the new game from the menu bar.
   *
   * @param newGame
   *          passed from the menu bar.
   */
  private static void pressNewGame(JButton newGame) {
    newGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        frame.setVisible(false);
        TitleScreen title = new TitleScreen();
        title.getTitleFrame().setVisible(true);
      }
    });
  }

  /**
   * this is called when the user clicks the exit button on the GUI makes sure
   * that the user is sure they want to exit by having a popup conformation box.
   */
  private static void windowExit() {
    frame.addWindowListener(new WindowAdapter() { // create a new window listener
      public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit the program?", "Exit Program Message Box",
            JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }
    });
  }

  /**
   * Helper method that is used to create borders around the main compents of the
   * GUI. This is just to make the componenets of the GUI clearer and nicer.
   */
  private static void createBoarders() {
    Border redline = BorderFactory.createLineBorder(Color.RED);
    title = BorderFactory.createTitledBorder(redline, "Inventory");
    title.setTitleJustification(TitledBorder.CENTER);
    buttonPanel.setBorder(title);

    Border lowerBevel = BorderFactory.createRaisedBevelBorder();
    canvas.setBorder(lowerBevel);

    Border blackline = BorderFactory.createLineBorder(Color.BLACK);
    TitledBorder t = BorderFactory.createTitledBorder(blackline, "Control Panel");
    t.setTitleJustification(TitledBorder.CENTER);
    extraButtons.setBorder(t);

    BorderFactory.createLineBorder(Color.BLACK);
    TitledBorder t1 = BorderFactory.createTitledBorder(blackline, "Items Avaliable");
    t1.setTitleJustification(TitledBorder.CENTER);
    textWindow.setBorder(t1);

  }

  /**
   * this is used as a getter from the title screen method so that the title
   * screen can make a new GUI.
   *
   * @return the frame.
   */
  public JFrame getFrame() {
    return Gui.frame;
  }
}
