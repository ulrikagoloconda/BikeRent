package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.BikeUser;
import Model.DBAccessImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-17
 */
public class AdminViewController implements Initializable {
    private Bike newBike;
    private BikeUser currentUser;
    //private loginVewController loginView;
    private DBAccess dbAccess = new DBAccessImpl();
    @FXML
    private Label urlLabel;
    @FXML
    private TextField brandText, modelYearText, colorText, typeText, sizeText;
    @FXML
    private GridPane gridDelBike;
    @FXML
    private AnchorPane deletePane,addBikePane;
    @FXML
    private Pane editPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.getSpider().setAdminView(this);
    }

    public void showDeleteView(ActionEvent actionEvent) {
        try {
            Parent deleteRoot = (Parent)Main.getSpider().getMain().getDeleteBikeLoader().load();
            Scene deleteScene = new Scene(deleteRoot);
            Main.getSpider().getMain().getPrimaryStage().setScene(deleteScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddView(ActionEvent actionEvent) {
        try{
            Parent addRoot = (Parent)Main.getSpider().getMain().getAddLoader().load();
            Scene addScean = new Scene(addRoot);
            Main.getSpider().getMain().getPrimaryStage().setScene(addScean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
