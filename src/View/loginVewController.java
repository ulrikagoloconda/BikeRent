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
import java.util.ResourceBundle;

public class loginVewController implements Initializable{
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button adminBtn;
    private JDBCConnection jdbcConnection;
    private DBAccess dbAccess = new DBAccessImpl();
    public BikeUser currentUser;

    public loginVewController(){
        ;
    }

    public void logInClick(Event event) {
        String userName = userNameText.getText();
        String password = passwordText.getText();
        currentUser = dbAccess.logIn(userName,password);
        if(!currentUser.getUserName().equals("Admin")){
            adminBtn.setVisible(false);
        }
    }



    public void showMainGui(ActionEvent actionEvent) {
        try {
            Main m = new Main();
            FXMLLoader newUserLoader =m.getNewUserLoader();
            Parent newUserRoot = (Parent) newUserLoader.load();
            Scene newUserScean = new Scene(newUserRoot);
            Main.getPrimaryStage().setScene(newUserScean);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
