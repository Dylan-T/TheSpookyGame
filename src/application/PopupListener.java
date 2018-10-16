package application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @author Armaan Chandra
 * this classes is a helper class that is used to create the drop down
 * menu when there is a right click on the drawing pane.
 *
 */
public class PopupListener extends MouseAdapter{

  private JPopupMenu popup;

  /**
   * constructor that creates the popuplistener.
   * @param j called from the GUI when creating mouselistener
   */
  public PopupListener(JPopupMenu j) {
    this.popup = j;
  }

  /**
   * overriding the methods in the mouseadpater.
   * when the mouse is clicked.
   * @param e is the mouse click
   */
  public void mousePressed(MouseEvent e) {
    maybeShowPopup(e, popup);
  }

  /**
   * overriding the methods in the mouseadpater.
   * when the mouse is released.
   * @param e is the mouse click
   */
  public void mouseReleased(MouseEvent e) {
    maybeShowPopup(e, popup);
  }

  /**
   * shows the pop up menu at the locaiton of the mouse clicks
   * @param e this is the mouse click
   * @param popup this is the menu
   */
  public void maybeShowPopup(MouseEvent e, JPopupMenu popup) {
    if(e.isPopupTrigger()) {
      popup.show(e.getComponent(), e.getX(), e.getY());
    }
  }
}
