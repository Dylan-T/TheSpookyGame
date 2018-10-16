package application;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import gameworld.GameWorld;

import java.awt.*;

/**
 * @author Armaan Chandra.
 * this class is used to create a title screen for the game.
 * this is for asthetics giving more game style functionality
 * to the program.
 *
 */
public class TitleScreen {

  private JFrame titleFrame;
  private static final int width = 1221;
  private static final int height = 682;

  /**
   *constrcutor that will just call the initialize to make the title screen.
   */
  public TitleScreen() {
    initialize();
  }

  /**
   *this is responsible for creating the title screen. which is just 3 buttons
   *with functionality.
   */
  public void initialize() {

    titleFrame = new JFrame();
    titleFrame.setTitle("The Spooky Game");
    titleFrame.setBounds(100, 100,width, height);
    titleFrame.setVisible(true);
    titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleFrame.getContentPane().setLayout(null);

    JPanel game = new JPanel();
    game.setBounds(0, 0, width , height);
    titleFrame.getContentPane().add(game);

    game.setLayout(null);

    JPanel buttons = new JPanel();
    buttons.setMaximumSize(new Dimension(250, 350));
    buttons.setBounds(width/2-125, height/2-125, 250, 250);
    buttons.setOpaque(false);
    game.add(buttons);
    buttons.setLayout(new GridLayout(3, 1));

    JButton start = new JButton("Start");
    start.setForeground(new Color(255, 0, 0));
    start.setFont(new Font("Chiller", Font.BOLD | Font.ITALIC, 55));
    start.setPreferredSize(new Dimension(150, 100));
    start.setOpaque(false);
    start.setContentAreaFilled(false);
    start.setBorderPainted(false);
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        titleFrame.setVisible(false);
        GUI gui = new GUI();
        gui.getFrame().setVisible(true);
      }});
    buttons.add(start);

    JButton load = new JButton("Load");
    load.setForeground(new Color(255, 0, 0));
    load.setFont(new Font("Chiller", Font.BOLD, 55));
    load.setPreferredSize(new Dimension(150, 100));
    load.setOpaque(false);
    load.setContentAreaFilled(false);
    load.setBorderPainted(false);
    load.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.out.println("call the load method from here");
      }});
    buttons.add(load);

    JButton quit = new JButton("Quit");
    quit.setForeground(new Color(255, 0, 0));
    quit.setFont(new Font("Chiller", Font.BOLD, 55));
    quit.setPreferredSize(new Dimension(150, 100));
    quit.setOpaque(false);
    quit.setContentAreaFilled(false);
    quit.setBorderPainted(false);
    quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        System.exit(0);
      }});
    buttons.add(quit);


    BackgroundImage bg = new BackgroundImage(new ImageIcon("background.png").getImage());
    game.add(bg);

  }


  /**
   *this is used by the GUI class to call the title screen from the main.
   * @return the JFrame allowing access from GUI class.
   */
  public JFrame getTitleFrame() {
    return this.titleFrame;
  }

}
