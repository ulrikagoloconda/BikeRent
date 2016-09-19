package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.DBAccessImpl;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-19
 */
public class DeleteBikeViewController implements Initializable{
    @FXML
    private GridPane gridDelBike;

    private DBAccess dbAccess = new DBAccessImpl();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void initDeleteView() {

        ArrayList<Bike> allBikes = dbAccess.getAllBikes();
        if (allBikes.size() > 10){
            List<Bike> shortList = allBikes.subList(0, 9);
            populateDeleteGrid(shortList);
        } else {
            populateDeleteGrid(allBikes);
        }

    }

    public void populateDeleteGrid(List<Bike> bikeList) {
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("CykelID");
        columnNames.add("Modell");
        columnNames.add("Årsmodell");
        columnNames.add("Storlek");
        columnNames.add("Färg");
        columnNames.add("Cykeltyp");
        ArrayList<String> values = new ArrayList<>();
        int j = 0;
        for (Bike b : bikeList) {
            values.add("" + b.getBikeID());
            values.add(b.getBrandName());
            values.add(""+b.getModelYear());
            values.add(""+b.getSize());
            values.add( b.getColor());
            values.add(b.getType());

            for (int i = 0; i < 6; i++)
                if (j == 0) {
                    System.out.println(gridDelBike);
                    gridDelBike.add(new Label(columnNames.get(i)), i, j);
                } else {
                    gridDelBike.add(new Label(values.get(i)),i,j);
                }
            j++;
        }
    }

    public void markRow(Event event) {
        event.getTarget()
    }
}
