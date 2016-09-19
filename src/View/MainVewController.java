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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

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
    @FXML
    private ImageView imageView1,imageView2,imageView3;

    private DBAccess dbaccess;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbaccess = new DBAccessImpl();

    }

    public void searchAvailableBikes(ActionEvent actionEvent) {
        ArrayList<Bike> availableBikes = dbaccess.selectAvailableBikes();
        System.out.println(availableBikes.size());
        if (availableBikes.size() > 3) {
            ArrayList<Bike> smallList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                smallList.add(availableBikes.get(i));
            }
            populateGridPane(smallList);
        }else {
            populateGridPane(availableBikes);
        }
    }

    public void showAdminView(ActionEvent actionEvent) {

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

    public boolean populateGridPane(ArrayList<Bike> bikeArray) {
        String[] topList = {"Bild", "Årsmodell", "Färg", "Cykeltyp", "Modell", "Ledig?"};
        ArrayList<String> values = new ArrayList<>();

        gridPane.gridLinesVisibleProperty().setValue(true);
        for (int i = 0; i < 6; i++) {
            gridPane.add(new Label(topList[i]), i, 0);
        }
        int j = 1;
        System.out.println("längd på listan " + bikeArray.size());
        for (Bike b : bikeArray) {
            values.add(""+ b.getModelYear());
            values.add(b.getColor());
            values.add(b.getType());
            values.add(b.getBrandName());
            values.add(""+b.isAvailable());
            for (int i = 0; i < 6; i++) {
                if (i == 0) {

                   Image im = new Image("file:///"+b.getImagePath());
                  //  Image im = new Image("file:///"+ "C:\\Users\\Rickard\\IdeaProjects\\github\\BikeRent\\src\\Image\\rosaCykel.jpg");
                    System.out.println(b.getImagePath());
                    imageView1.setImage(im);
                    gridPane.add(imageView1, i, j);
                }else {
                    gridPane.add(new Label(values.get(i-1)), i,j);
                }
            }
            j++;
            if(j>3)
            {
                System.out.println(" är det här det bryts " + j);
                return false;
            }
            System.out.println(j + " hur många gåner körs detta ");
        }

        return true;
    }
}
