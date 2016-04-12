package CarParkSim.view;

import CarParkSim.logic.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Created by Nienke's boys on 12-4-2016.
 */
public class BarView extends AbstractView {

    private final String vrij = "VRIJ";
    private final String passholder = "PASSHOLDER";
    private final String reservering = "RESEVERING";
    private final String adhoc = "ADHOC";
    private String chartTitle;
    private ChartPanel chartPanel;
    private DefaultCategoryDataset dataset;
    JFreeChart barChart;

    /**
     *
     * @param model 
     * @param chartTitle
     */
    public BarView(Model model, String chartTitle) {
        super(model);
        this.chartTitle = chartTitle;
        dataset = new DefaultCategoryDataset();
        barChart = ChartFactory.createBarChart(chartTitle, "Bezetting", "Aantal", createDataset());

        chartPanel = new ChartPanel(barChart);
        barChart.addChangeListener(chartPanel);
    }

    private CategoryDataset createDataset() {
        //dataset.addValue(model.getNumParkingPlaces("free"), vrij , vrij );
        dataset.addValue(model.getStat("currentPassholders"), passholder, passholder);
        dataset.addValue(model.getStat("currentReservingCar"), reservering, reservering);
        dataset.addValue(model.getStat("currentAdHocCar"), adhoc, adhoc);

        return dataset;
    }

    private void updateDataset() {
        dataset.clear();
        // dataset.addValue(model.getNumParkingPlaces("free"), vrij , vrij );
        dataset.addValue(model.getStat("currentPassholders"), passholder, passholder);
        dataset.addValue(model.getStat("currentReservingCar"), reservering, reservering);
        dataset.addValue(model.getStat("currentAdHocCar"), adhoc, adhoc);
    }

    public void updateView() {

        chartPanel.updateUI();
        add(chartPanel);
        updateDataset();
        repaint();
    }

}
