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
import javafx.scene.control.Button;
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
    @FXML
    private Button adminBtn;
    private JDBCConnection jdbcConnection;
    private DBAccess dbAccess = new DBAccessImpl();
    public BikeUser currentUser ;

    public loginVewController(){
        ;
    }

    public void logInClick(Event event) {
        String userName = userNameText.getText();
        String password = passwordText.getText();
        System.out.println("logInClick");

      try {
        currentUser = dbAccess.logIn(userName,password);
        if(!currentUser.getUserName().equals("Admin")) {
            adminBtn.setVisible(false);
        }
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
            Main m = new Main();
            FXMLLoader MainViewLoader =m.getMainViewLoader();
            Parent MainViewRoot = (Parent) MainViewLoader.load();
            Scene MainViewScean = new Scene(MainViewRoot);
            Main.getPrimaryStage().setScene(MainViewScean);

        } catch (IOException e) {
            e.printStackTrace();
            ErrorView.showError("Huvudfönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public BikeUser getCurrentUser(){
        return currentUser;
    }

  public void newUserClick(ActionEvent actionEvent) {
    System.out.println("clicked on newUserClick");
    try {
      Main m = new Main();
      FXMLLoader newUserLoader =m.getNewUserLoader();
      Parent newUserRoot = (Parent) newUserLoader.load();
      Scene newUserScean = new Scene(newUserRoot);
      Main.getPrimaryStage().setScene(newUserScean);

    } catch (IOException e) {
      e.printStackTrace();
      ErrorView.showError("Lägg till användare-fönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
    }


  }
}
