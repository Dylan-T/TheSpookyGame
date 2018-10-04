package application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @author user1
 *
 */
public class PopupListener extends MouseAdapter{
  
  private JPopupMenu popup;
  
  /**
   * @param j
   */
  public PopupListener(JPopupMenu j) {
    this.popup = j;
  }
  
  public void mousePressed(MouseEvent e) {
    maybeShowPopup(e, popup);
  }
  
  public void mouseReleased(MouseEvent e) {
    maybeShowPopup(e, popup);
  }
  
  /**
   * @param e
   * @param popup 
   */
  public void maybeShowPopup(MouseEvent e, JPopupMenu popup) {
    if(e.isPopupTrigger()) {
      popup.show(e.getComponent(), e.getX(), e.getY());
    }
  }
  
  

}
