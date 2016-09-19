package View;

import Interfaces.DBAccess;
import Model.BikeUser;
import Model.DBAccessImpl;
import Model.JDBCConnection;
import helpers.EmailValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class changeUserVewController implements Initializable{

  @FXML
  private TextField userNameText;
  @FXML
  private TextField fNameText;
  @FXML
  private TextField lNameText;
  @FXML
  private TextField mailText;
  @FXML
  private TextField phoneText;
  @FXML
  private TextField passwordText;
  @FXML
  private TextField passwordCheckerText;

  @FXML
  private Label uniqeTextIdLabel;
    @FXML
    private JDBCConnection jdbcConnection;
    private DBAccess dbAccess = new DBAccessImpl();
    public BikeUser currentUser ;

    public changeUserVewController(){
        ;
    }




  public void showLoginGui() {
        try {
            Main m = new Main();
            FXMLLoader loginViewLoader =m.getNewUserLoader();
          System.out.println("fel fönster laddas i denna version..");
            Parent loginViewRoot = (Parent) loginViewLoader.load();
            Scene loginViewScean = new Scene(loginViewRoot);
            Main.getPrimaryStage().setScene(loginViewScean);
         // populateText();

        } catch (IOException e) {
            e.printStackTrace();
            ErrorView.showError("Huvudfönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
        }

    }


  private void populateText() {

    userNameText.setText("uNamn");
    fNameText.setText("f-Name");
    lNameText.setText("l-Name");
    mailText.setText("e-mail");
    phoneText.setText("123456789");
    passwordText.setText("12");
    passwordCheckerText.setText("12");

  }


  @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

  public void newUserClick(ActionEvent actionEvent) {
    System.out.println("clicked on newUserClick");
    String userName = userNameText.getText();
    String fName = fNameText.getText();
    String lName = lNameText.getText();
    String email = mailText.getText();
    String phoneString = phoneText.getText();
    String password = passwordText.getText();
    String passwordChecker = passwordCheckerText.getText();
    phoneString.replace("-","");
    phoneString.replace("+","");


    try {
      if (userName.length()<5) {
        System.out.println("username to short");
      }else if (!dbAccess.isUserAvalible(userName)) {
        System.out.println("username not free");
      } else if(password.length()<1){
        System.out.println("phone is to short!");
      } else if (!password.equals(passwordChecker)) {
        System.out.println("passw not same");
      } else if (!EmailValidator.validate(email)) {
        System.out.println("email not ok format!");
      } else if(phoneString.length()<2){
        System.out.println("phone is to short!");
      }else if(fName.length()<1){
        System.out.println("phone is to short!");
      }else if(lName.length()<1){
        System.out.println("phone is to short!");
      }else if(phoneString.length()<2){
        System.out.println("phone is to short!");
      }else {
        int phone = Integer.parseInt(phoneString);
        System.out.println("we can now add some info");
        //insert_new_user(in_fname varchar(50),in_lname varchar(50),in_memberlevel varchar(50),in_email varchar(50),in_phone varchar(50),in_username varchar(50), in_passw varchar(50)) RETURNS smallint(6)
        int in_memberlevel = 1;
        boolean isAddUserOK = dbAccess.InsertNewUser(fName, lName, in_memberlevel, email, phone, userName, password);
        if (!isAddUserOK) {
          ErrorView.showError("Inloggningsfel", "fel vid inloggning", "Kontrollera era uppgifter", new IOException(" :-( kunde inte lägga till användare"));
        }
        if (isAddUserOK) {
          boolean d = DialogView.showSimpleInfo("Ny användare upplaggd", "Lyckades", "Ny användare är nu upplagd");
          try {
            Main m = new Main();
            FXMLLoader loginLoader =m.getLoginViewLoader();
            Parent loginRoot = (Parent) loginLoader.load();
            Scene loginScean = new Scene(loginRoot);
            Main.getPrimaryStage().setScene(loginScean);

          } catch (IOException e) {
            e.printStackTrace();
            ErrorView.showError("Lägg till användare-fönster - fel", "fel vid inläsning av data..","Kontrollera er data.." ,  e);
          }

        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      ErrorView.showError("fel vid add", "fel vid lägg till användare","Kontrollera era uppgifter" ,  e);
    }

//      ErrorView.showError("Inloggningsfel", "fel vid inloggning","Kontrollera era uppgifter" ,  e);
  }



  public void checkUserName(KeyEvent keyEvent) {
    System.out.println("entering get username");
    String userName = userNameText.getText();
    if (userName.length()<=5){
      uniqeTextIdLabel.setText("Måste vara längre än 5 tecken");
      uniqeTextIdLabel.setTextFill(Color.RED);
    }else {
      //userName
 /*     try {
        if (!dbAccess.isUserAvalible(userName)){
          uniqeTextIdLabel.setText("Ej unikt..");
          uniqeTextIdLabel.setTextFill(Color.RED);
        }
        else{
          System.out.println("Ok username");
          uniqeTextIdLabel.setText("Användarnamn OK..");
          uniqeTextIdLabel.setTextFill(Color.GREEN);
        }
      } catch (SQLException e) {
        processException(e);
        uniqeTextIdLabel.setText("Ej unikt..");
        uniqeTextIdLabel.setTextFill(Color.RED);
        ErrorView.showError("Inloggningsfel", "fel vid inloggning","Kontrollera era uppgifter" ,  e);
      }
*/
    }

  }
}
