package View;

import Interfaces.DBAccess;
import Model.BikeUser;
import Model.DBAccessImpl;
import Model.JDBCConnection;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Model.DBUtil.processException;

public class loginVewController implements Initializable{
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    private JDBCConnection jdbcConnection;
    private DBAccess dbAccess = new DBAccessImpl();
    private BikeUser currentUser ;
    private MainVewController mainVew;
    private AddBikeController addView;
    private DeleteBikeViewController deleteBikeView;
    private loginVewController currentLoginViewContrlloer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Main.getSpider().setLoginView(this);
    }

    public void logInClick(Event event) {
        String userName = userNameText.getText();
        String password = passwordText.getText();
        System.out.println("logInClick");

      try {
        currentUser = dbAccess.logIn(userName,password);
        System.out.println("after dbAccess.logIn(userName,password)");
        System.out.println(currentUser.getEmail());
        if (currentUser !=null){
                  showMainGui();
        }
      } catch (SQLException e) {
        processException(e);
        ErrorView.showError("Inloggningsfel", "fel vid inloggning","Kontrollera era uppgifter" ,  e);
      }
    }

  public void showMainGui() {
        try {

            FXMLLoader MainViewLoader = Main.getSpider().getMain().getMainViewLoader();
            Parent MainViewRoot = (Parent) MainViewLoader.load();
            Scene MainViewScean = new Scene(MainViewRoot);
            Main.getSpider().getMain().getPrimaryStage().setScene(MainViewScean);


        } catch (IOException e) {
            e.printStackTrace();
            ErrorView.showError("Huvudfönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
        }
    }

  public void newUserClick(ActionEvent actionEvent) {
    System.out.println("clicked on newUserClick");
    try {

      FXMLLoader newUserLoader =Main.getSpider().getMain().getNewUserLoader();
      Parent newUserRoot = (Parent) newUserLoader.load();
      Scene newUserScean = new Scene(newUserRoot);
      Main.getSpider().getMain().getPrimaryStage().setScene(newUserScean);

    } catch (IOException e) {
      e.printStackTrace();
      ErrorView.showError("Lägg till användare-fönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
    }
  }

  public BikeUser getCurrentUser() {
    return currentUser;

  }

    public loginVewController getInstanceOfLoginVewController(){
        return currentLoginViewContrlloer;
    }

  public void setCurrentUser(BikeUser bikeUser) {
    System.out.println("in setcurentUser!!" + currentUser.getfName());
   currentUser = bikeUser;
    System.out.println("updated bikeuser!!" + currentUser.getfName());
  }
}
