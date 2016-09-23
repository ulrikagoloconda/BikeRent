package View;

import helpers.DoughnutChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-22
 */

public class StatView {


/**
 * Created by NIK1114 on 2016-09-18.
 *
 * StatView.showStatView("labelID1", 50, "labelID2", 50);
 * or
 * StatView.DoughnutChartView("labelID1", 50, "labelID2", 50);
 */

  public static void showStatView(String labelID1, Integer stat0To100ID1, String labelID2, Integer stat0To100ID2) {

    if ( (stat0To100ID1 + stat0To100ID2 ) != 100){
      ErrorView.showError("fel" , "fel i diagram-indata", "summan blev inte 100", new Exception("summan blev inte 100 : " + stat0To100ID1 +"+" + stat0To100ID2 ));
      stat0To100ID1 = 25;
      stat0To100ID2 = 75;
    }
    PieChart pieChart = new PieChart();
    pieChart.setData(getChartData(labelID1, stat0To100ID1, labelID2, stat0To100ID2));
    pieChart.setTitle("Statistik");
    pieChart.setLegendSide(Side.LEFT);
    pieChart.setClockwise(false);
    pieChart.setLabelsVisible(false);

    StackPane root = new StackPane();
    root.getChildren().add(pieChart);
    //här kommer det kluriga..
    Scene statViewScean = new Scene(root, 300, 250);
    //Main.getSpider().getMain().getPrimaryStage().setScene(statViewScean);
    //TODO 

  }

  private static ObservableList<PieChart.Data> getChartData(String labelID1, Integer stat0To100ID1, String labelID2, Integer stat0To100ID2) {

    ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();
    answer.addAll(new PieChart.Data(labelID1, stat0To100ID1),
        new PieChart.Data(labelID2, stat0To100ID2)
    );
    return answer;
  }

  public static void DoughnutChartView(String labelID1, Integer stat0To100ID1, String labelID2, Integer stat0To100ID2) {

    if ( (stat0To100ID1 + stat0To100ID2 ) != 100){
      ErrorView.showError("fel" , "fel i diagram-indata", "summan blev inte 100", new Exception("summan blev inte 100 : " + stat0To100ID1 +"+" + stat0To100ID2 ));
      stat0To100ID1 = 25;
      stat0To100ID2 = 75;
    }
    ObservableList<PieChart.Data> pieChartData = getChartData(labelID1, stat0To100ID1, labelID2, stat0To100ID2);

      final DoughnutChart pieChart = new DoughnutChart(pieChartData);
      pieChart.setTitle("Statistik");
      StackPane root = new StackPane();
      root.getChildren().add(pieChart);
      //här kommer det kluriga..
      Scene statViewScean = new Scene(root, 300, 250);
      //Main.getSpider().getMain().getPrimaryStage().setScene(statViewScean);
    //TODO ändra detta

    }
  }

