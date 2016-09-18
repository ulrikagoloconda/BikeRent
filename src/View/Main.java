package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage_;
    private static FXMLLoader newUserLoader;
  private static FXMLLoader loginLoader;
  private static FXMLLoader root;
  private static Scene loginScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage_ = primaryStage;
      loginLoader = new FXMLLoader(getClass().getResource("../View/loginView.fxml"));
      Parent root = loginLoader.load();
        primaryStage_.setTitle("Bike Rent");
      loginScene = new Scene(root, 600, 600);
        primaryStage_.setScene(loginScene);

        primaryStage_.show();
    }

    public  FXMLLoader getNewUserLoader(){
        FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("../View/newUserView.fxml"));
        return newUserLoader;
    }
  public  FXMLLoader getMainViewLoader(){
    FXMLLoader newUserLoader = new FXMLLoader(getClass().getResource("../View/mainView.fxml"));
    return newUserLoader;
  }
  public  FXMLLoader getLoginViewLoader(){
    return loginLoader;
  }

    public static Stage getPrimaryStage(){
        return primaryStage_;
    }

    public static void main(String[] args) {
   /*  System.out.println("just for test: ");
      CMDmeny.print();
      int option;
      option = InputHelper.getIntegerInput( "chose action..");
      */
      //int option;
      //option = InputHelper.getIntegerInput( "chose action..");

    // System.out.println("Mail ok = " +  SentMail.sendDelRQ("användare1", "anv1mail@gmail.com"));

      launch(args);
    }


}
