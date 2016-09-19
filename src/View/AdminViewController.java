package View;

import Model.AccessBike;
import Model.Bike;
import Model.BikeUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
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
    private Label urlLabel;

    @FXML
    private TextField brandText,modelYearText,colorText,typeText, sizeText;

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
               urlLabel.setText(selected.getName());
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

        if (newBike.equals(null)) {
            // } else if (newBike.getCreatedBy().equals(loginView.getCurrentUser())) {
            //TODO fixa detta när koden satts ihop
        }else if(true){
                if (brandText.getText().length() > 0) {
                    newBike.setBrandName(brandText.getText());
                }
                if (modelYearText.getText().length() == 4) {
                    String s = modelYearText.getText();
                    for (int i = 0; i < 4; i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            modelYearText.setText("");
                            break;
                        } else {
                            int yearInt = Integer.valueOf(s);

                            newBike.setModelYear(yearInt);
                        }
                    }
                }
                if (colorText.getText().length() > 0) {
                    newBike.setColor(colorText.getText());
                }
                if (typeText.getText().length() > 0) {
                    newBike.setType(typeText.getText());
                }

            if(sizeText.getText().length()<=2){
                String s = sizeText.getText();
                if(Character.isDigit(s.charAt(0)) && Character.isDigit(s.charAt(1))) {
                    int i = Integer.valueOf(s);
                    newBike.setSize(i);
                }
            }
            }


        AccessBike.insertNewBike(newBike);
        brandText.setText("");
        modelYearText.setText("");
        colorText.setText("");
        typeText.setText("");
        sizeText.setText("");
    }
}
