package CarParkSim.view;

import CarParkSim.logic.*;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Nienke's boys
 */
public class InfoView extends AbstractView {

    private JTextField fieldNumCarsEntering;
    private JTextField fieldNumCarsParked;
    private JTextField fieldNumCarsPaying;
    private JTextField fieldNumCarsExiting;
    private JTextField fieldNumFloors;
    private JTextField fieldTime;

    public InfoView(Model model) {
        super(model);
        //LEGE USER INTERFACE MET ALLEMAAL LEGE TEKSTBLOKKEN EN WEET IK VEEL WAT VOOR DINGEN JE ER ALLEMAAL IN WILT STOPPEN.
        fieldNumCarsEntering = new JTextField(27);
        fieldNumCarsEntering.setEditable(false);

        fieldNumCarsParked = new JTextField(27);
        fieldNumCarsParked.setEditable(false);

        fieldNumCarsPaying = new JTextField(27);
        fieldNumCarsPaying.setEditable(false);

        fieldNumCarsExiting = new JTextField(27);
        fieldNumCarsExiting.setEditable(false);

        fieldNumFloors = new JTextField(27);
        fieldNumFloors.setEditable(false);

        fieldTime = new JTextField(27);
        fieldTime.setEditable(false);

        //alle tekstvelden etc aanmaken
    }

    @Override
    public void updateView() {
        //tekstvelden updaten
        fieldNumCarsEntering.setText("Number of cars in entrance queue: " + model.getNumCars("enterq"));

        fieldNumCarsParked.setText("Number of cars parked: " + model.getNumCars("parked"));

        fieldNumCarsPaying.setText("Number of cars in payment queue: " + model.getNumCars("payq"));

        fieldNumCarsExiting.setText("Number of cars in exit queue: " + model.getNumCars("exitq"));

        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("total") + " (" + model.getNumParkingPlaces("free") + " spots free)");

        fieldTime.setText("Time :" + model.getTime());

        add(fieldNumCarsEntering);
        add(fieldNumCarsParked);
        add(fieldNumCarsPaying);
        add(fieldNumCarsExiting);
        add(fieldNumFloors);
        add(fieldTime);
        //Alle releveante info ophalen en die in de tekst velden stoppen. 

        validate();
        repaint();
    }

}
