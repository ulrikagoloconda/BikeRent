package View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private  FXMLLoader loginLoader;
    private  FXMLLoader mainViewLoader;
    private FXMLLoader newUserLoader;
    private FXMLLoader deleteBikeLoader;
    private FXMLLoader  adminLoader;
    private FXMLLoader changeUserViewLoader;
    private FXMLLoader changeTryLoader;
    private  FXMLLoader addBikeLoader;
    private  Scene loginScene;

    private static SpiderView spider;

    @Override
    public void start(Stage primaryStage) throws Exception {
        spider = new SpiderView();
        spider.setMain(this);
        this.primaryStage = primaryStage;
        loginLoader = spider.getMain().getLoginViewLoader();
        Parent root = loginLoader.load();
        this.primaryStage.setTitle("Bike Rent");
        loginScene = new Scene(root, 600, 600);
        this.primaryStage.setScene(loginScene);

        this.primaryStage.show();
    }

    public void showLoginView(){
        try {
            primaryStage.setScene(loginScene);
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
    public FXMLLoader getAdminLoader() {
        if(adminLoader == null) {
            adminLoader = new FXMLLoader(getClass().getResource("../View/adminView.fxml"));
        }
        return adminLoader;
    }

    public FXMLLoader getChangeUserViewLoader() {
    if(changeUserViewLoader==null) {
        changeUserViewLoader = new FXMLLoader(getClass().getResource("../View/changeUserView.fxml"));
    }
        return changeUserViewLoader;
    }

    public FXMLLoader getChangeUserTry() {
        if(changeTryLoader==null) {
            changeTryLoader = new FXMLLoader(getClass().getResource("../View/changeUserTry.fxml"));
        }
        return changeTryLoader;
    }

    public FXMLLoader getAddLoader() {
        if(addBikeLoader == null) {
            addBikeLoader = new FXMLLoader(getClass().getResource("../View/addBikeView.fxml"));
        }
        return addBikeLoader;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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




    public static SpiderView getSpider(){
        return spider;
    }
}
