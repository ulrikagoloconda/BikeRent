package View;

import Model.Bike;
import Model.BikeUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-17
 */
public class AdminViewController implements Initializable {
   private  Bike newBike;
    private BikeUser currentUser;
    private loginVewController loginView;

    @FXML
    private TextField brandText,modelYearText,colorText,typeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginView = new loginVewController();
    }

    public void addPicture(ActionEvent actionEvent) {
        if( newBike == null){
            newBike = new Bike();
        }
        ByteArrayInputStream inputStream;
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        if(selected != null) {

            FileInputStream fileInputStream = null;
            byte[] bFile = new byte[(int) selected.length()];
            try {
                fileInputStream = new FileInputStream(selected);
                fileInputStream.read(bFile);
                fileInputStream.close();
                 inputStream = new ByteArrayInputStream(bFile);
                newBike.setImageStream(inputStream);
                newBike.setCreatedBy(loginView.getCurrentUser());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    public void addBike(ActionEvent actionEvent) {
        //TextField brandText, modelYearText, colorText, typeText;
        if(newBike.equals(null)){
            newBike = new Bike();
        }
       if(newBike.getColor().equals(currentUser)) {
           if(brandText.getText().length()>0){
               newBike.setBrandName(brandText.getText());
           }
           if(modelYearText.getText().length()==4){
               String s = modelYearText.getText();
               for(int i = 0; i < 4; i++ ) {
                 if(!Character.isDigit(s.charAt(i))){
                       modelYearText.setText("");
                       break;
                   }else {
                     int yearInt = Integer.valueOf(s);
                     Year y = Year.of(yearInt);
                     newBike.setModelYear(y);
                   }
               }

           }
        }

    }
}
