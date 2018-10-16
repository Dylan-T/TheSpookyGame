package application;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import gameworld.GameWorld;
import gameworld.Item;
import renderer.Renderer;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;

/**
 * @author Armaan this is the GUI class that creates the interface which will be
 *         used by multiple other classes.
 *
 */
public class GUI {
  /*
   * public enum Move { NORTH, SOUTH, EAST, WEST, ZOOM_IN, ZOOM_OUT };
   */

  /**
   * I THINK THAT IT WOULD BE A GGOOD IDEA TO MAKE THIS CLASS AN ABSTRACK CLASS
   * THIS WOULD MEAN THAT DYLANS PACKAGE WOULD HAVE TO EXTEND IT AND IMPLEMENT A
   * MOVE METHOD.
   */

  // GameWorld game = GameWorld.testGameWorld2();
  GameWorld game;
  private Renderer rWindow;

  private static JFrame frame;
  private JTextField textField;
  private JComponent drawing;
  private TitledBorder title;
  private static JPanel canvas;
  private static JPanel displayPanel;
  private static JPanel textBox;
  private int height;
  private int width;
  private int newheight;
  private int newwidth;

  // DRAWING WIDTH = 1604, HEIGHT = 951

  /**
   * Launch the application.
   *
   * @param args
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          // GUI window = new GUI();
          TitleScreen title = new TitleScreen();
          title.getTitleFrame().setVisible(true);
          // window.frame.setVisible(true);

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public GUI() {
    game = GameWorld.testGameWorld2();
    initialize();
  }

  /**
   * Initialize the contents of the frame. this is mainly used to draw everything.
   */

  @SuppressWarnings("serial")
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("SwenProject");
    // frame.setBounds(100, 100, 900, 750); // original sizing of the window
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    // frame.setUndecorated(true);
    windowExit();
    frame.setVisible(true);
    // windowExit();

    width = frame.getBounds().width;
    height = frame.getBounds().height;

    // ------------HERE IS THE BUTTON PANEL------------------
    JPanel buttonPanel = new JPanel();

    buttonPanel.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        // here you should call a method that does a function when the mouse is clicked
        System.out.println("You are clicking the side panel");
      }
    });

    // --------------CREATING THE DISPLAY PANEL AND CANVAS--------------
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
        System.out.println("you are clicking the display pane");
      }
    });

    displayPanel.setLayout(new BorderLayout(0, 0));
    buttonPanel.setLayout(new BorderLayout(0, 0));
    frame.getContentPane().setLayout(groupLayout);

    // ------------MAKING THE CANVAS A DRAWING COMPONENT----------------
    // within here i should make the popup menu

    drawing = new JComponent() {
      protected void paintComponent(Graphics g) {
        System.out.println("you are now in the drawing pane");
        redraw(g);
      }

      private void redraw(Graphics g) {
        System.out.println("calling dyalns redraw");
        rWindow.redraw(game.getCurrentRoom(), GameWorld.Direction.NORTH, g);
      }
    };

    rWindow = new Renderer(drawing.getGraphics());
    drawing.setPreferredSize(new Dimension(width, height));

    drawing.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        System.out.println("hey you are in the drawing area");
      }
    });

    canvas.setLayout(new GridLayout(0, 1, 0, 0));
    canvas.add(drawing);

    // ------CREATING A DROP DOWN MENU WHEN CLICKING-------
    // in order to differentiate on whether something is a item or not
    // i guess we can up x y values compare them to x y values of the item.
    JPopupMenu popup = new JPopupMenu();
    JMenuItem pickup = new JMenuItem("Pick Up");
    pickup.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("this should call a pickup method");
        // get the item
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        boolean found = false;

        Item[][] i = rWindow.getGrid();
        for(int a = 0; a < i.length; a++) {
          for(int b = 0; b < i[0].length; b++) {
            if(rWindow.isWithin(mouseX, mouseY) == i[b][a]) {
              found = true;
              game.getPlayer().pickupItem(i[b][a]);
            }
          }
        }

        if(found == false) {
          JOptionPane.showMessageDialog(frame, "nothing to pick up");
        }

        drawing.repaint();
        // this should also redraw the inventory pane
      }
    });

    JMenuItem inspect = new JMenuItem("Inspect");
    inspect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("this should call a getDescription(); method");
        drawing.repaint();
      }
    });
    popup.add(pickup);
    popup.add(inspect);

    MouseListener popupListener = new PopupListener(popup);
    drawing.addMouseListener(popupListener);

    // -------------CREATING THE MENU BAR--------------
    // these actions will need some mouse listeners
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

    // --------CREATING THE NAVIGATION BUTTTONS---------------
    JButton west = new JButton("\u2190");
    west.setPreferredSize(new Dimension(60, 147));
    west.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be west");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.WEST);
          drawing.repaint();
        }
      }
    });

    JButton east = new JButton("\u2192");
    east.setPreferredSize(new Dimension(60, 147));
    east.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be east");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.EAST);
          drawing.repaint();
        }
      }
    });

    JButton north = new JButton("\u2191");
    north.setPreferredSize(new Dimension(60, 147));
    north.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be north");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.NORTH);
          drawing.repaint();
        }

      }
    });

    JButton south = new JButton("\u2193");
    south.setPreferredSize(new Dimension(60, 147));
    south.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be south");
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

    // Border edge = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    // displayPanel.setBorder(edge);

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
    JPanel extraButtons = new JPanel();
    extraButtons.setMaximumSize(new Dimension(820, 148));
    extraButtons.setLayout(null);
    // add any extra buttons here
    // extraButtons.setBackground(Color.BLUE);

    JButton drop = new JButton("Drop");
    drop.setPreferredSize(new Dimension(50, 50));
    drop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be droping item");
        drawing.repaint();
        // this should also redraw the inventory but i'm not even sure how things are
        // being added here
      }
    });

    drop.setBounds(3, 9, 100, 60);

    extraButtons.add(drop);
    displayPanel.add(extraButtons);

    // creating the text area that the text area will go in

    displayPanel.add(Box.createRigidArea(new Dimension(2, 0)));

    textBox = new JPanel();
    textBox.setMaximumSize(new Dimension(830, 150));
    textBox.setLayout(null); // position the text area using setBounds
    // textBox.setBackground(Color.GREEN);

    // adding a text area to text box

    displayPanel.add(textBox);

    JTextArea textWindow = new JTextArea();
    // textWindow.setBounds(0, 0, 828, 138);

    textWindow.setLineWrap(true);
    textWindow.setWrapStyleWord(true); // making the line up nice
    textWindow.setEditable(false); // cannot be edited

    JScrollPane scroll = new JScrollPane(textWindow);
    scroll.setBounds(0, 0, 828, 138);

    textBox.add(scroll);

    // -----CREATING BORDERS FOR THE COMPONENTS-------
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

  }

  /**
   * Helper method that does the actions of the saveButton.
   *
   * @param save
   */
  static public void pressSave(JButton save) {
    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("You need to save code here"); // cleanly end the program.
      }
    });
  }

  /**
   * Helper method that does the actions of the loadButton.
   *
   * @param load
   */
  static public void pressLoad(JButton load) {
    load.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("You need to load code here"); // cleanly end the program.
      }
    });
  }

  /**
   * Helper method that does the actions of the newGameButton.
   *
   * @param newGame
   */
  static public void pressNewGame(JButton newGame) {
    newGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        frame.setVisible(false);
        main(null);
      }
    });
  }

  /**
   * @param exit
   *          exits.
   */
  static public void windowExit() {
    frame.addWindowListener(new WindowAdapter() { // create a new window listener
      public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit the program?", "Exit Program Message Box",
            JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
          frame.dispose();
        }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }
    });
  }

  /**
   * @return the frame.
   */
  public JFrame getFrame() {
    return this.frame;
  }
}
