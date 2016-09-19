package helpers;

import javafx.scene.layout.Pane;

import java.awt.*;

/**
 * Created by NIK1114 on 2016-09-16.
 */
public class PCRelated {

  public static boolean isThisNiklasPC() {
    System.out.println("userPC: " + (System.getProperty("user.name")).toUpperCase() );
      if ((System.getProperty("user.name")).toUpperCase().contains("NIK")){
        return true;
    }else return false;
  }


  public static String getOSLanguage() {
    return System.getProperty("user.language");
  }
  /**
   * make a PC-beep!
   **/
  public static void Beep() {
    Toolkit.getDefaultToolkit().beep();
  }
  public static void makeHandCurser(Pane inPanel) {
    inPanel.getScene().setCursor(javafx.scene.Cursor.OPEN_HAND);
  }
  public static void makeCloseedHandCurser(Pane inPanel) {
    inPanel.getScene().setCursor(javafx.scene.Cursor.CLOSED_HAND);
  }

  public static void makeWaitCurser(Pane inPanel) {
    inPanel.getScene().setCursor(javafx.scene.Cursor.WAIT);
  }

  public static void makeNormalCurser(Pane inPanel) {
    inPanel.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
  }


}
