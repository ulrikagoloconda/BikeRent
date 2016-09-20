package View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

   // private static Stage primaryStage_;
   // //private static FXMLLoader newUserLoader;
  //private static FXMLLoader loginLoader;
  //private static FXMLLoader root;
  //private static Scene loginScene;
  //private BikeUser currentUser;

    private Stage primaryStage_;
    private  FXMLLoader loginLoader;
    private  FXMLLoader mainViewLoader;
    private FXMLLoader newUserLoader;
    private FXMLLoader deleteBikeLoader;
    private  Scene loginScene;

    private static SpiderView spider;


    @Override
    public void start(Stage primaryStage) throws Exception {
        spider = new SpiderView();
        spider.setMain(this);
        primaryStage_ = primaryStage;
        loginLoader = spider.getMain().getLoginViewLoader();
        Parent root = loginLoader.load();
        primaryStage_.setTitle("Bike Rent");
        loginScene = new Scene(root, 600, 600);
        primaryStage_.setScene(loginScene);

        primaryStage_.show();
    }

    public void showLoginView(){
        try {
            primaryStage_.setScene(loginScene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public FXMLLoader getNewUserLoader() {
        if(newUserLoader == null) {
            newUserLoader = new FXMLLoader(getClass().getResource("../View/newUserView.fxml"));
            return newUserLoader;
        }else {
            return newUserLoader;
        }
    }

    public FXMLLoader getMainViewLoader() {
        if (mainViewLoader == null) {
            FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("../View/mainView.fxml"));
            return mainViewLoader;
        } else {
            return mainViewLoader;
        }
    }

    public FXMLLoader getLoginViewLoader() {
        if(loginLoader==null) {
            loginLoader = new FXMLLoader(getClass().getResource("../View/loginView.fxml"));
            return loginLoader;
        } else {
            return loginLoader;
        }
    }

    public FXMLLoader getDeleteBikeLoader() {
        if(deleteBikeLoader == null) {
            deleteBikeLoader = new FXMLLoader(getClass().getResource("../View/deleteBikeView.fxml"));
            return deleteBikeLoader;
        }else {
            return deleteBikeLoader;
        }
    }

    public Stage getPrimaryStage() {
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

        //System.out.println("Mail ok = " +  SentMail.sendDelRQ("användare1", "anv1mail@gmail.com"));

        launch(args);
    }


    public FXMLLoader getAdminLoader() {
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("../View/adminView.fxml"));
        return adminLoader;
    }



  //public FXMLLoader getChangeUserViewLoader() {
   // System.out.println("in getChangeUserViewLoader");
   // FXMLLoader changeUserViewLoader = new FXMLLoader(getClass().getResource("../View/changeUserView.fxml"));
   // System.out.println("in getChangeUserViewLoader: changeUserView.fxml ");
   // return changeUserViewLoader;
  //}

  public FXMLLoader getChangeUserView1(){
    FXMLLoader changeTryLoader = new FXMLLoader(getClass().getResource("../View/changeUserView1.fxml"));
    return changeTryLoader;
  }

    public FXMLLoader getChangeUserViewLoader() {
        System.out.println("in getChangeUserViewLoader");
        FXMLLoader changeUserViewLoader = new FXMLLoader(getClass().getResource("../View/changeUserView.fxml"));
        System.out.println("in getChangeUserViewLoader: changeUserView.fxml ");
        return changeUserViewLoader;
    }

    public FXMLLoader getChangeUserTry() {
        FXMLLoader changeTryLoader = new FXMLLoader(getClass().getResource("../View/changeUserView1.fxml"));
      //new FXMLLoader(getClass().getResource("../View/changeUserTry.fxml"));
        return changeTryLoader;
    }

    public FXMLLoader getAddLoader() {
        FXMLLoader addBikeLoader = new FXMLLoader(getClass().getResource("../View/addBikeView.fxml"));
        return addBikeLoader;
    }

    public static SpiderView getSpider(){
        return spider;
    }

}
