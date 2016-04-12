package CarParkSim.view;

import CarParkSim.logic.*;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * Created by Nienke's boys on 12-4-2016.
 */
public class ChartView extends AbstractView{

    private ChartPanel chartPanel;
    private DefaultPieDataset dataset;
    private JFreeChart pieChart;

    public ChartView(Model model, String chartTitle){
        super(model);
        //this.chartTitle = chartTitle;
        dataset = new DefaultPieDataset( );
        pieChart = ChartFactory.createPieChart("Yolo", createDataset());

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
        dataset.clear();
        dataset.setValue("free spaces",(model.getNumParkingPlaces("free")));
        dataset.setValue("passholder", (model.getStat("currentPassholders")));
        dataset.setValue("reservations", (model.getStat("currentReservingCar")));
        dataset.setValue("adhoc", (model.getStat("currentAdHocCar")));
        dataset.setValue("Badparker", (model.getStat("currentBadParkerCar")));
    }

    public void updateView(){
        chartPanel.updateUI();
        add(chartPanel);
        updateDataset();
        repaint();
    }
}
