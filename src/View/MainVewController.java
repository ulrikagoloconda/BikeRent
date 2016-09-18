package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.DBAccessImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Goloconda Fahlén
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
   private GridPane gridPane;

    private DBAccess dbaccess;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbaccess = new DBAccessImpl();

    }

    public void searchAvailableBikes(ActionEvent actionEvent) {
      ArrayList<Bike> availableBikes = dbaccess.selectAvailableBikes();
        populateGridPane(availableBikes);
    }

    public void showAdminView(ActionEvent actionEvent)  {

        try {
            Main m = new Main();
            FXMLLoader newUserLoader = m.getAdminLoader();
           Parent adminRoot = (Parent) newUserLoader.load();
            Scene adminScene = new Scene(adminRoot);
            Main.getPrimaryStage().setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void populateGridPane(ArrayList<Bike> bikeArray){
        String [] topList = {"Bild", "Årsmodell", "Färg", "Cykeltyp", "Modell", "Ledig?"};
        for(int i = 1; i <=6; i++) {
            gridPane.add(new Label(topList[i-1]), i, 0);
        }

    }


}
