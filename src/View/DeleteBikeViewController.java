package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.DBAccessImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-19
 */
public class DeleteBikeViewController implements Initializable{
    @FXML
    private GridPane gridDelBike;
    @FXML
    private Label deleteLabel;
    @FXML
    private Button deleteBikeBtn, forwardBtn, backToMain;
    @FXML
    private AnchorPane deletePane;
    private DBAccess dbAccess;
    private Map<Node, Integer> idMap;
    private static ArrayList<Bike> allBikes;
    private Bike selected = null;
    private int bikeIdDel;
    private int lastIndex;
    private loginVewController loginVew;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.getSpider().setDeleteView(this);
        idMap = new HashMap<>();
        dbAccess = new DBAccessImpl();
        System.out.println("Körs inte init ? " + dbAccess);
        deleteBikeBtn.setVisible(false);
        backToMain.setVisible(true);
        initDeleteView();
    }


    public void initDeleteView() {
        System.out.println(dbAccess);
        allBikes = dbAccess.getAllBikes();
        if (allBikes.size() > 10){
            List<Bike> shortList = allBikes.subList(0, 9);
            populateDeleteGrid(shortList);
        } else {
            populateDeleteGrid(allBikes);
            forwardBtn.setDisable(false);

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
            values.clear();
            values.add("" + b.getBikeID());
            values.add(b.getBrandName());
            values.add(""+b.getModelYear());
            values.add(""+b.getSize());
            values.add( b.getColor());
            values.add(b.getType());

            for (int i = 0; i < 6; i++) {
                if (j == 0) {
                    gridDelBike.add(new Label(columnNames.get(i)), i, j);
                } else {
                    Label k = new Label(values.get(i));
                    k.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Label l = (Label) event.getSource();
                            bikeIdDel = idMap.get(l);

                            for (Bike b : allBikes) {
                                if (b.getBikeID() == bikeIdDel) {
                                    selected = b;
                                    break;
                                }
                            }
                            String s = selected.getBikeID() + " " + selected.getBrandName() + " " + selected.getModelYear() +
                                    " " + selected.getSize() + " " + selected.getColor() + " " + selected.getType();
                            deleteLabel.setText(s);
                            deleteBikeBtn.setVisible(true);
                        }
                    });

                    idMap.put(k, b.getBikeID());
                    gridDelBike.add(k, i, j);
                    lastIndex= idMap.get(k);
                }
            }
            j++;
        }
    }


    public void deleteBike(Event event) {
      boolean b =  dbAccess.deleteBike(bikeIdDel);
        if(b){
            deletePane.setVisible(false);

        }
    }

    public void nextView(ActionEvent actionEvent) {
        int i = 0;
        for(Bike b : allBikes){
            if (b.getBikeID()==lastIndex){
                 i = allBikes.indexOf(b);
            }
        }
        if(i>allBikes.size()) {
            System.out.println(i + 10 + " längden " + allBikes.size());
            if (allBikes.size() > i + 10) {
                List<Bike> smallList = allBikes.subList(i + 1, i + 10);
                populateDeleteGrid(smallList);
            } else {
                List<Bike> smallList = allBikes.subList(i + 1, allBikes.size() - 1);
                populateDeleteGrid(smallList);
            }
        }else {
            forwardBtn.setDisable(false);
        }
    }

    public void showMainView(ActionEvent actionEvent) {
       Main.getSpider().getLoginView().showMainGui();
    }

    public void setLoginVew(loginVewController loginVew){
        System.out.println("Här sätts loginVew objektet ");
        this.loginVew = loginVew;
    }
}
