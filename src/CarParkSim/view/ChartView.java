package CarParkSim.view;

import CarParkSim.logic.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Created by Nienke's boys on 12-4-2016.
 */
public class ChartView extends AbstractView{


    private final String vrij = "VRIJ";
    private final String passholder = "PASSHOLDER";
    private final String reservering = "RESEVERING";
    private final String adhoc = "ADHOC";
    private String chartTitle;
    private ChartPanel chartPanel;
    private DefaultPieDataset dataset;
    JFreeChart pieChart;

    public ChartView(Model model, String chartTitle){
        super(model);
        this.chartTitle = chartTitle;
        dataset = new DefaultPieDataset( );
        pieChart = ChartFactory.createPieChart("Piechart occupation", createDataset());

        chartPanel =  new ChartPanel( pieChart);
        pieChart.addChangeListener(chartPanel);
    }
    private PieDataset createDataset(){
        dataset.setValue("free spaces",(model.getNumParkingPlaces("free")));
        dataset.setValue("passholder", (model.getStat("currentPassholders")));
        dataset.setValue("reservations", (model.getStat("currentReservingCar")));
        dataset.setValue("adhoc", (model.getStat("currentAdHocCar")));
        dataset.setValue("Badparker", (model.getStat("currentBadParkerCar")));

        return dataset;
    }

    private void updateDataset(){
      //  dataset.clear();
        dataset.setValue("free spaces",(model.getNumParkingPlaces("free")));
        dataset.setValue("passholder", (model.getStat("currentPassholders")));
        dataset.setValue("reservations", (model.getStat("currentReservingCar")));
        dataset.setValue("adhoc", (model.getStat("currentAdHocCar")));
        dataset.setValue("Badparker", (model.getStat("currentBadParkerCar")));
    }

    public void updateView(){

        //barChart = ChartFactory.createBarChart(chartTitle, "Bezetting" , "Aantal", createDataset());
//barChart.fireChartChanged();
        chartPanel.updateUI();
        add(chartPanel);
        updateDataset();
        repaint();
    }
    /*public static void main( String[ ] args )
    {
        ChartView chart = new ChartView(model, "Plekgebruik in de Garage", "Plekgebruik in de Garage");
                chart.pack( );
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible( true );
    }*/

}




/**
 dataset.addValue( 5.0 , audi , speed );
 dataset.addValue( 6.0 , audi , userrating );
 dataset.addValue( 10.0 , audi , millage );
 dataset.addValue( 4.0 , audi , safety );

 dataset.addValue( 4.0 , ford , speed );
 dataset.addValue( 2.0 , ford , userrating );
 dataset.addValue( 3.0 , ford , millage );
 dataset.addValue( 6.0 , ford , safety );
 */




