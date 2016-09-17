package View;

import Model.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-17
 */
public class AdminViewController implements Initializable {
    Bike newBike;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void addPicture(ActionEvent actionEvent) {
        if( newBike == null){
            newBike = new Bike();
        }
        ByteArrayInputStream inputStream;
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected != null) {

            FileInputStream fileInputStream = null;
            byte[] bFile = new byte[(int) selected.length()];
            try {
                fileInputStream = new FileInputStream(selected);
                fileInputStream.read(bFile);
                fileInputStream.close();
                 inputStream = new ByteArrayInputStream(bFile);
                newBike.setImageStream(inputStream);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
