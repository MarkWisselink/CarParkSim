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
        fieldNumCarsEntering.setText("Number of cars in entrance queue " + model.getNumCars("enter") + ".");
        
        fieldNumCarsParked.setText("Number of cars parked " + model.getNumCars("enter") + ".");
        
        fieldNumCarsPaying.setText("Number of cars in payment queue " + model.getNumCars("enter") + ".");
        
        fieldNumCarsExiting.setText("Number of cars in exit queue " + model.getNumCars("enter") + ".");
        
        fieldNumFloors.setText("Total number of parking spots " + model.getNumParkingPlaces() + " (" + "" + "spots free).");
        
        fieldTime.setText("Time :" + model.getTime() + ".");

        this.add(fieldNumCarsEntering);
        this.add(fieldNumCarsParked);
        this.add(fieldNumCarsPaying);
        this.add(fieldNumCarsExiting);
        this.add(fieldNumFloors);
        this.add(fieldTime);
        //Alle releveante info ophalen en die in de tekst velden stoppen. 
        repaint();
    }

}
