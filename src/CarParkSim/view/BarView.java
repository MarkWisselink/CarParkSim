package CarParkSim.view;

import CarParkSim.logic.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * Created by Nienke's boys on 12-4-2016.
 */
public class BarView extends AbstractView {

    // private final String vrij = "VRIJ";
    private final String passholder = "PASSHOLDER";
    private final String reservering = "RESEVERING";
    private final String adhoc = "ADHOC";
    private final String badparker = "BADPARKER";
    private String chartTitle;
    private ChartPanel chartPanel;
    private DefaultCategoryDataset dataset;
    private JFreeChart barChart;

    /**
     *
     * @param model reference to the model
     */
    public BarView(Model model) {
        super(model);
        this.chartTitle = "Barchart occupation";
        dataset = new DefaultCategoryDataset();
        barChart = ChartFactory.createBarChart(chartTitle, "Occupation", "Amount", createDataset());
        chartPanel = new ChartPanel(barChart);
        barChart.addChangeListener(chartPanel);
    }

    private CategoryDataset createDataset() {
        //dataset.addValue(model.getNumParkingPlaces("free"), vrij , vrij );
        dataset.addValue(model.getStat("currentPassholders"), passholder, passholder);
        dataset.addValue(model.getStat("currentReservingCar"), reservering, reservering);
        dataset.addValue(model.getStat("currentAdHocCar"), adhoc, adhoc);
        dataset.addValue(model.getStat("currentBadParkerCar"), badparker, badparker);

        return dataset;
    }

    private void updateDataset() {
        dataset.clear();
        // dataset.addValue(model.getNumParkingPlaces("free"), vrij , vrij );
        dataset.addValue(model.getStat("currentPassholders"), passholder, passholder);
        dataset.addValue(model.getStat("currentReservingCar"), reservering, reservering);
        dataset.addValue(model.getStat("currentAdHocCar"), adhoc, adhoc);
        dataset.addValue(model.getStat("currentBadParkerCar"), badparker, badparker);

        CategoryPlot p = barChart.getCategoryPlot();

        CategoryAxis axis = p.getDomainAxis();
        axis.setLowerMargin(0.1);
        axis.setUpperMargin(0.1);
        axis.setCategoryMargin(0.1);
        BarRenderer renderer = (BarRenderer) p.getRenderer();
        renderer.setItemMargin(0.1);
    }

    public void updateView() {

        chartPanel.updateUI();
        add(chartPanel);
        updateDataset();
        repaint();
    }

}
