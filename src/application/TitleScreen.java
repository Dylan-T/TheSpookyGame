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
 * @author user1
 *
 */
public class TitleScreen {
  
  private static JFrame titleFrame;
  private static final int width = 1280;
  private static final int height = 800;
  
  /**
   * @param args
   */
 /* public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TitleScreen window = new TitleScreen();
          window.titleFrame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }*/
  
  
  /**
   * 
   */
  public TitleScreen() {
    initialize();
  }
  
  /**
   * 
   */
  public void initialize() {
    
    
    titleFrame = new JFrame();
    titleFrame.setTitle("Start Up Screen");
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
    
    
    BackgroundImage bg = new BackgroundImage(new ImageIcon("notlegal.png").getImage());
    game.add(bg);
    
    
    
  }  
  
  
  /**
   * @return the JFrame allowing access from GUI class.
   */
  public JFrame getTitleFrame() {
    return this.titleFrame;
  }

}
