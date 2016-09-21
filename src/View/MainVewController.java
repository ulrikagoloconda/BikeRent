package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.DBAccessImpl;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private ImageView imageView1, imageView2, imageView3;
    @FXML
    private Label messageLabel;
    @FXML
    private Button executeLoanBtn, netBtn;
    @FXML
    private ComboBox<String> combobox;

    private DBAccess dbaccess;
    private Map<Node, Integer> idMap;
    private int selectedFromGrid;
    private ArrayList<Bike> availableBikesCopy;
    private ArrayList<Bike> availableBikes;
    private List<Bike> currentListInView;
    Map<String,Integer> searchMap;
    private  Bike selectedBikeSearch;

    private String errorTitle = "Fel i huvidfönster";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.getSpider().setMainView(this);
        dbaccess = new DBAccessImpl();
        idMap = new HashMap<>();
        executeLoanBtn.setVisible(false);
        netBtn.setVisible(false);
        combobox.setEditable(true);
    }

    public void searchAvailableBikes(ActionEvent actionEvent) {
        availableBikes = dbaccess.selectAvailableBikes();
        availableBikesCopy = availableBikes;
        System.out.println(availableBikes.size());
        if (availableBikes.size() > 3) {
            currentListInView = availableBikes.subList(0, 3);
            populateGridPane(currentListInView);
        } else {
            populateGridPane(availableBikes);
        }
    }

    public void showAdminView(ActionEvent actionEvent) {

        try {

            FXMLLoader newUserLoader = Main.getSpider().getMain().getAdminLoader();
            Parent adminRoot = (Parent) newUserLoader.load();
            Scene adminScene = new Scene(adminRoot);
            Main.getSpider().getMain().getPrimaryStage().setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
            ErrorView.showError(errorTitle, "fel vid Öppnining av admindata..", "starta om denna session..", e);
        }

    }

    public boolean populateGridPane(List<Bike> bikeArray) {
        gridPane.getChildren().clear();
        System.out.println(availableBikes.size() + " Storleken på långlistan i pop");
        if (availableBikes.size() <= 3) {
            netBtn.setVisible(false);
        } else {
            netBtn.setVisible(true);
        }
        String[] topList = {"Bild", "Årsmodell", "Färg", "Cykeltyp", "Modell", "Ledig?"};
        ArrayList<String> values = new ArrayList<>();

        gridPane.gridLinesVisibleProperty().setValue(true);
        for (int i = 0; i < 6; i++) {
            gridPane.add(new Label(topList[i]), i, 0);
        }
        int j = 1;
        for (Bike b : bikeArray) {
            values.clear();
            values.add("" + b.getModelYear());
            values.add(b.getColor());
            values.add(b.getType());
            values.add(b.getBrandName());
            values.add("" + b.isAvailable());
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    Image image = SwingFXUtils.toFXImage(b.getBufferedImage(), null);
                    ImageView iv = new ImageView();
                  iv.setFitHeight(65);
                    iv.setFitWidth(95);

                    iv.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            Node n = (Node) event.getSource();
                            onClickActions(n);
                        }
                    });
                    idMap.put(iv, b.getBikeID());
                    iv.setImage(image);
                    gridPane.add(iv, i, j);
                } else {
                    Label k = new Label();
                    k.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            Node n = (Node) event.getSource();
                            onClickActions(n);
                        }
                    });
                    k.setText(values.get(i - 1));
                    Font f = new Font(16);
                    k.setFont(f);
                    idMap.put(k, b.getBikeID());
                    gridPane.add(k, i, j);

                }
            }
            j++;
            if (j > 3) {
                return false;
            }
        }
        return true;
    }
    public boolean populateGridPane(Bike bike) {
        gridPane.getChildren().clear();
        String[] topList = {"Bild", "Årsmodell", "Färg", "Cykeltyp", "Modell", "Ledig?"};
        ArrayList<String> values = new ArrayList<>();
       // gridPane.gridLinesVisibleProperty().setValue(true);
        for (int i = 0; i < 6; i++) {
            gridPane.add(new Label(topList[i]), i, 0);
        }

            values.add("" + bike.getModelYear());
            values.add(bike.getColor());
            values.add(bike.getType());
            values.add(bike.getBrandName());
            values.add("" + bike.isAvailable());
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    Image image = SwingFXUtils.toFXImage(bike.getBufferedImage(), null);
                    ImageView iv = new ImageView();
                    iv.setFitHeight(65);
                    iv.setFitWidth(95);
                    iv.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            Node n = (Node) event.getSource();
                            onClickActions(n);
                        }
                    });
                    idMap.put(iv, bike.getBikeID());
                    iv.setImage(image);
                    gridPane.add(iv, i, 1);
                } else {
                    Label k = new Label();
                    k.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            Node n = (Node) event.getSource();
                            onClickActions(n);
                        }
                    });
                    k.setText(values.get(i - 1));
                    Font f = new Font(16);
                    k.setFont(f);
                    idMap.put(k, bike.getBikeID());
                    gridPane.add(k, i, 1);

                }
        }
        return true;
    }

    public void onClickActions(Node n) {
        if (availableBikes == null) {
            executeLoanBtn.setVisible(true);
            selectedFromGrid = selectedBikeSearch.getBikeID();
        } else {
            selectedFromGrid = idMap.get(n);
            String available = "";
            for (Bike b : availableBikes) {
                if (b.isAvailable()) {
                    available = "Ja";
                } else {
                    available = "Nej";
                }
                if (b.getBikeID() == selectedFromGrid) {

                    String s = "Årsmodell: " + b.getModelYear() + " Färg: " + b.getColor() + " Cykeltyp: " +
                            b.getType() + " Ledig? " + available;
                    messageLabel.setText(s);
                    executeLoanBtn.setVisible(true);
                }
            }

        }
    }

    public void showChangeUserView(ActionEvent actionEvent) {
        try {

            FXMLLoader changeTryLoader = Main.getSpider().getMain().getChangeUserTry();
            Parent changeTryRoot = changeTryLoader.load();
            Scene changeTryScean = new Scene(changeTryRoot);
            Main.getSpider().getMain().getPrimaryStage().setScene(changeTryScean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextBikesOnList(ActionEvent actionEvent) {
        gridPane.getChildren().clear();
        currentListInView.clear();
        if (availableBikes.size() >= 3) {
            currentListInView = availableBikes.subList(0, 3);
        } else {
            currentListInView = availableBikes.subList(0, availableBikes.size() - 1);
        }
        populateGridPane(currentListInView);
    }

    public void executeBikeLoan(ActionEvent actionEvent) {
        String message = dbaccess.executeBikeLoan(selectedFromGrid,Main.getSpider().getLoginView().getCurrentUser().getUserID());
        messageLabel.setText(message);
    }


    public void popuateComboBox(Event event) {
    searchMap = dbaccess.getSearchValue(combobox.getEditor().getText());
        System.out.println(combobox.getEditor().getText());
        int count = 0;
        combobox.getItems().clear();
        for (Map.Entry<String,Integer> entry : searchMap.entrySet()) {
            if(count>10){
                break;
            }
            combobox.getItems().add(entry.getKey());
            count++;
        }
    }

    public void setSearchResult(ActionEvent actionEvent) {

       String selected = combobox.getSelectionModel().getSelectedItem().toString();
        int bikeID = searchMap.get(selected);
        selectedBikeSearch = dbaccess.getBikeByID(bikeID);
        System.out.println(selectedBikeSearch.getBrandName());
        System.out.println(selectedBikeSearch.getBikeID());
        populateGridPane(selectedBikeSearch);

    }
}

