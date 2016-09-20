package View;

import Interfaces.DBAccess;
import Model.Bike;
import Model.BikeUser;
import Model.DBAccessImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  @FXML
  private Label userNameLabel, memberLevelLabel, activeLoanLabel, numberOfLoanedBikesLabel;

    private DBAccess dbaccess;

  private String errorTitle = "Fel i huvidfönster";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbaccess = new DBAccessImpl();
      BikeUser bikeUser = null;
      populateUserTextInGUI(Main.getSpider().getLoginView().getCurrentUser());


    }

  private void populateUserTextInGUI(BikeUser bikeUser) {
    userNameLabel.setText(bikeUser.getUserName());
    memberLevelLabel.setText(""+bikeUser.getMemberLevel());
    activeLoanLabel.setText("000");
    numberOfLoanedBikesLabel.setText("111");
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
          ErrorView.showError(errorTitle, "fel vid Öppnining av admindata..", "starta om denna session..", e);
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

  public void showChangeUserView(ActionEvent actionEvent) {
      try {
          Main m = new Main();
          FXMLLoader changeTryLoader = m.getChangeUserView1();
          Parent changeTryRoot = changeTryLoader.load();
          Scene changeTryScean = new Scene(changeTryRoot);
          Main.getPrimaryStage().setScene(changeTryScean);
      }catch (Exception e){
          e.printStackTrace();
      }
  /**  try {
      System.out.println("change user click");
      Main m = new Main();
      FXMLLoader ChangeUserViewLoader =m.getChangeUserViewLoader();
      Parent ChangeUserViewRoot = (Parent) ChangeUserViewLoader.load();
      Scene ChangeUserViewScean = new Scene(ChangeUserViewRoot);
      Main.getPrimaryStage().setScene(ChangeUserViewScean);
    } catch (IOException e) {
      e.printStackTrace();
      ErrorView.showError(errorTitle, "fel vid inläsning av data..","Kontrollera er data.." ,  e);
    }*/
  }

}
