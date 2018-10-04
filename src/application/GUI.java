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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import gameworld.GameWorld;
import renderer.Renderer;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;

/**
 * @author Armaan
 * this is the GUI class that creates the interface which will be used by multiple 
 * other classes. 
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
  
  GameWorld game;

  private static JFrame frame;
  private JTextField textField;
  private JComponent drawing;
  private TitledBorder title;
  private JTextArea textWindow;
  private Renderer rWindow;
  
  private int height;
  private int width;
  private int newheight;
  private int newwidth;

  /**
   * Launch the application.
   * @param args 
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GUI window = new GUI();
          window.frame.setVisible(true);
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
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   * this is mainly used to draw everything.
   */
  
  private void initialize() {
    frame = new JFrame();
    frame.setTitle("SwenProject");
    frame.setBounds(100, 100, 900, 750); // original sizing of the window
    windowExit(); // CODE THAT DOUBLE CHECKS YOU REALLY WANT TO EXIT THE PROGRAM

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

    // --------------HERE IS THE DISPLAY BOX--------------
    
    //this is also formatting all the other boxes around it so that is able to resize when 

    JPanel displayPanel = new JPanel();
    JPanel canvas = new JPanel();

    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
              .addComponent(canvas, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
              .addPreferredGap(ComponentPlacement.RELATED)
              .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
            .addComponent(displayPanel, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
          .addContainerGap())
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
            .addComponent(canvas, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
          .addPreferredGap(ComponentPlacement.RELATED)
          .addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
    );
    //canvas.setBackground(Color.BLACK);

    drawing = new JComponent() {
      protected void paintComponent(Graphics g) {
        System.out.println("you are now in the drawing pane");
        redraw(g);
      }

      private void redraw(Graphics g) {
        rWindow.redraw(game.getCurrentRoom(), GameWorld.Direction.NORTH);
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

    // this is making the button east boarder
    buttonPanel.setLayout(new BorderLayout(0, 0));
    frame.getContentPane().setLayout(groupLayout);
    //buttonPanel.setBackground(Color.PINK);

    // ---------this is making the display a boarder
    displayPanel.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        // here you should call a method that does a function when the mouse is clicked
        System.out.println("you are clicking the display pane");
      }
    });
    //displayPanel.setBackground(Color.ORANGE);
    displayPanel.setLayout(new BorderLayout(0, 0));

    // -------------HERE IS THE MENU BAR--------------
    
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
    // i have no idea what buttons should be used here
    JButton newGameButton = new JButton("New Game");
    mnGame.add(newGameButton);
    pressNewGame(newGameButton);

    // --------THIS IS WHERE I CREATE THE NAVIGATION BUTTTONS---------------

    JButton west = new JButton("\u2190");
    west.setPreferredSize(new Dimension(75, 150));
    west.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        // here you would call a method that does the move function
        // i think dylan wanted the method to take in a directions which are enums
        System.out.println("Hello this should be west");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.WEST);
        }
      }
    });

    JButton east = new JButton("\u2192");
    east.setPreferredSize(new Dimension(75, 150));
    east.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be east");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.EAST);
        }
      }
    });

    JButton north = new JButton("\u2191");
    north.setPreferredSize(new Dimension(75, 150));
    north.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be north");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.NORTH);
        }
        
      }
    });

    JButton south = new JButton("\u2193");
    south.setPreferredSize(new Dimension(75, 150));
    south.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("Hello this should be south");
        if (game != null) {
          game.movePlayer(GameWorld.Direction.SOUTH); //This is the code to move the player
        }
      }
    });

    JButton fillerL = new JButton(" ");
    // fillerL.setBackground(Color.white);
    // fillerL.setBounds(x, y, newwidth, height);
    fillerL.setVisible(false);
    JButton fillerR = new JButton(" ");
    // fillerR.setBackground(Color.white);
    fillerR.setVisible(false);

    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.LINE_AXIS));

    //Border edge = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    //displayPanel.setBorder(edge);

    west.setMargin(new Insets(0, 0, 0, 0));
    east.setMargin(new Insets(0, 0, 0, 0));
    north.setMargin(new Insets(0, 0, 0, 0));
    south.setMargin(new Insets(0, 0, 0, 0));

    JPanel navigation = new JPanel();
    navigation.setMaximumSize(new Dimension(100, 150));
    navigation.setLayout(new GridLayout(2, 3));
    navigation.add(fillerL);
    navigation.add(north);
    navigation.add(fillerR);
    navigation.add(west);
    navigation.add(south);
    navigation.add(east);

    displayPanel.add(navigation);
    
   //-----THIS IS ADDING THE TEXT AREA--------
    
   //THE TEXT AREA IS USED FOR DISPLAYING OUTPUT BACK TO THE USER
   //TO DO THIS IN THE OTHER CLASSES WOULD EXTEND THIS GUI GIVING THEM ACCESS.
    
    JPanel textArea = new JPanel();
    displayPanel.add(textArea, BorderLayout.EAST);
    
    textWindow = new JTextArea(10, 0);
    textWindow.setLineWrap(true);
    textWindow.setWrapStyleWord(true); //making the line up nice
    textWindow.setEditable(false); //cannot be edited
    
    JScrollPane scoll = new JScrollPane(textWindow);
    DefaultCaret caret = (DefaultCaret) textWindow.getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    
    GroupLayout gl_textArea = new GroupLayout(textArea);
    gl_textArea.setHorizontalGroup(
      gl_textArea.createParallelGroup(Alignment.TRAILING)
        .addGroup(gl_textArea.createSequentialGroup()
          .addContainerGap(185, Short.MAX_VALUE)
          .addComponent(scoll, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
    );
    gl_textArea.setVerticalGroup(
      gl_textArea.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_textArea.createSequentialGroup()
          .addComponent(scoll, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
          .addGap(0))
    );
    textArea.setLayout(gl_textArea);
    
    
  //-----CREATING BORDERS FOR THE COMPONENTS-------  
    //textArea.setBackground(Color.BLUE);
    Border redline = BorderFactory.createLineBorder(Color.RED);
    title = BorderFactory.createTitledBorder(redline, "Items");
    title.setTitleJustification(TitledBorder.ABOVE_TOP);
    buttonPanel.setBorder(title);
    
    Border lowerBevel = BorderFactory.createRaisedBevelBorder();
    canvas.setBorder(lowerBevel);
    

  //-------here is where we split a text field with the display field.
  }

  /**
   * this is for exiting, it will ask whether you really want to exit.
   */
  static public void windowExit() {
    frame.addWindowListener(new WindowAdapter() { // create a new window listener
      public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?",
            "Exit Program Message Box", JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
          frame.dispose();
        }
        // need something that just removes the box
      }
    });
  }

  /**
   * Helper method that does the actions of the saveButton.
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
   * @param newGame
   */
  static public void pressNewGame(JButton newGame) {
    newGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("You need to new Game code here"); // cleanly end the program.
      }
    });
  }
}
