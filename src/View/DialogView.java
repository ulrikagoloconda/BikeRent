package View;

import javafx.scene.control.Alert;

/**
 * Created by NIK1114 on 2016-09-18.
 */
public class DialogView {

  public static boolean showSimpleInfo(String title, String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);

    alert.showAndWait();
    return true;
  }
}
