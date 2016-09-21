package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

/**
 * Created by NIK1114 on 2016-09-18.
 */
public class StatView {

  public static void showStatView(String labelID1, Integer stat0To100ID1, String labelID2, Integer stat0To100ID2, String labelID3, Integer stat0To100ID3) {

    if ( (stat0To100ID1 + stat0To100ID2 + stat0To100ID3) != 100){
      ErrorView.showError("fel" , "fel i diagram-indata", "summan blev inte 100", new Exception("summan blev inte 100 : " + stat0To100ID1 +"+" + stat0To100ID2 +"+" + stat0To100ID3));
    }
    PieChart pieChart = new PieChart();
    pieChart.setData(getChartData(labelID1, stat0To100ID1, labelID2, stat0To100ID2, labelID3, stat0To100ID3));
    pieChart.setTitle("Title");
    pieChart.setLegendSide(Side.LEFT);
    pieChart.setClockwise(false);
    pieChart.setLabelsVisible(false);

    StackPane root = new StackPane();
    root.getChildren().add(pieChart);
    //här kommer det kluriga..
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
    
  }

  private static ObservableList<PieChart.Data> getChartData(String labelID1, Integer stat0To100ID1, String labelID2, Integer stat0To100ID2, String labelID3, Integer stat0To100ID3) {

    ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
    answer.addAll(new PieChart.Data(labelID1, stat0To100ID1),
        new PieChart.Data(labelID2, stat0To100ID2),
        new PieChart.Data(labelID3, stat0To100ID3)
    );
    return answer;
  }

}
