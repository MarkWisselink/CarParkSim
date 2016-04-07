package CarParkSim.view;

import CarParkSim.logic.*;
import javax.swing.*;

/**
 *
 * @author Nienke's boys
 */
public class GuiRobert extends AbstractView {

    private JTextField fieldNumCarsEntering;
    private JTextField fieldNumCarsParked;
    private JTextField fieldNumCarsPaying;
    private JTextField fieldNumCarsExiting;
    private JTextField fieldNumFloors;
    private JTextField fieldTime;

    public GuiRobert(Model model) {
        super(model);
        //LEGE USER INTERFACE MET ALLEMAAL LEGE TEKSTBLOKKEN EN WEET IK VEEL WAT VOOR DINGEN JE ER ALLEMAAL IN WILT STOPPEN.
        fieldNumCarsEntering = new JTextField();
        fieldNumCarsEntering.setEditable(false);

        fieldNumCarsParked = new JTextField();
        fieldNumCarsParked.setEditable(false);

        fieldNumCarsPaying = new JTextField();
        fieldNumCarsPaying.setEditable(false);

        fieldNumCarsExiting = new JTextField();
        fieldNumCarsExiting.setEditable(false);

        fieldNumFloors = new JTextField();
        fieldNumFloors.setEditable(false);

        fieldTime = new JTextField();
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

        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("taken") + " (" + model.getNumParkingPlaces("free") + "spots free).");

        fieldTime.setText("Time :" + model.getTime() + ".");

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
