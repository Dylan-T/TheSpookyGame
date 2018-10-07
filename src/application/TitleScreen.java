package application;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import java.awt.*;

/**
 * @author user1
 *
 */
public class TitleScreen {
  
  private static JFrame titleFrame;
  
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
    titleFrame.setBounds(100, 100, 500, 550); // original sizing of the window
    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //titleFrame.setUndecorated(true);
    titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleFrame.getContentPane().setLayout(null);
    
    //i need to know the concept in order to make this. Because i need to find a relevant image to make the background.
    //once the background is made add 3 buttons - start, load, quit
    titleFrame.setVisible(true);   
    
  }
  
  
  
  /**
   * @return the JFrame allowing access from GUI class.
   */
  public JFrame getTitleFrame() {
    return this.titleFrame;
  }

}
