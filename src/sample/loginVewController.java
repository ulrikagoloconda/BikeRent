package sample;

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
import java.util.ResourceBundle;

public class LoginVewController implements Initializable{
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordText;
    private JDBCConnection jdbcConnection;

    public LoginVewController(){
        ;
    }

    public void logInClick(Event event) {
        String userName = userNameText.getText();
        String password = passwordText.getText();

      //  try {
          /*  jdbcConnection.connectToDB();
            currentUser = jdbcConnection.loginQ(userName, password);
            System.out.println(currentUser.getfName());
            if (currentUser.getfName() == null) {
                System.out.println("det har troligtvis blivit fell");
            } else {*/
                //loginPane.setVisible(false);



       /* } catch (SQLException e) {

            e.printStackTrace();
        }*/

    }



    public void showMainGui(ActionEvent actionEvent) {
        try {
            FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("mainVew.fxml"));
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
