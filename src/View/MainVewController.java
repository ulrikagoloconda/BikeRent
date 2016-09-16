package View;

import Model.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author MGoloconda Fahlén
 * @version 1.0
 * @since 2016-09-15
 */
public class MainVewController implements Initializable {
    @FXML
    private TableColumn columCykel;
    @FXML
    private TableView<Bike> tableBikeView;
    @FXML
    private TableColumn<Bike, String> year, status, color, type, model, available;
   @FXML
    private TableColumn<Bike, ImageView> image;

    public void populateColumns(){
        columCykel.getColumns().add(new Label("Test"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateColumns();

    }
}
