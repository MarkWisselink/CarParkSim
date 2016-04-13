package CarParkSim.view;

import CarParkSim.logic.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;

/**
 * Created by Nienke's boys on 12-4-2016.
 */
public class ChartView extends AbstractView {

    private ChartPanel chartPanel;
    private DefaultPieDataset dataset;
    private JFreeChart pieChart;
    private PiePlot plot;

    /**
     *
     * @param model reference to the model
     */
    public ChartView(Model model) {
        super(model);
        dataset = new DefaultPieDataset();
        pieChart = ChartFactory.createPieChart("Piechart occupation", createDataset());

        chartPanel = new ChartPanel(pieChart);
        pieChart.addChangeListener(chartPanel);
    }

    private PieDataset createDataset() {
        dataset.setValue("Free spaces", (model.getNumParkingPlaces("free")));
        dataset.setValue("Passholder", (model.getStat("currentPassholders")));
        dataset.setValue("Reservation", (model.getStat("currentReservingCar")));
        dataset.setValue("Ad hoc", (model.getStat("currentAdHocCar")));
        dataset.setValue("Bad parker", (model.getStat("currentBadParkerCar")));

        return dataset;
    }

    private void updateDataset() {
        dataset.clear();
        dataset.setValue("Free spaces", (model.getNumParkingPlaces("free")));
        dataset.setValue("Passholder", (model.getStat("currentPassholders")));
        dataset.setValue("Reservation", (model.getStat("currentReservingCar")));
        dataset.setValue("Ad hoc", (model.getStat("currentAdHocCar")));
        dataset.setValue("Bad parker", (model.getStat("currentBadParkerCar")));

        // Specify the colors here
        plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Free spaces", Color.GREEN);
        plot.setSectionPaint("Passholder", Color.BLUE);
        plot.setSectionPaint("Reservations", Color.PINK);
        plot.setSectionPaint("Ad hoc", Color.YELLOW);
        plot.setSectionPaint("Bad parker", Color.BLACK);

    }

    public void updateView() {
        chartPanel.updateUI();
        add(chartPanel);
        updateDataset();
        repaint();
    }
}
