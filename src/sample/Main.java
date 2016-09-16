package sample;


import helpers.CMDmeny;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage_;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage_ = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        primaryStage_.setTitle("Hello World");
        primaryStage_.setScene(new Scene(root, 300, 275));
        primaryStage_.show();
    }

    public static Stage getPrimaryStage(){
        return primaryStage_;
    }

    public static void main(String[] args) {
     System.out.println("just for test: ");
      CMDmeny.print();

      //int option;
      //option = InputHelper.getIntegerInput( "chose action..");




      launch(args);
    }


}
