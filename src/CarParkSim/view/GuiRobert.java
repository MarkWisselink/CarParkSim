package CarParkSim.view;
import CarParkSim.logic.*;
import javax.swing.*;

/**
 *
 * @author Nienke's boys
 */
public class GuiRobert extends AbstractView {

    private JTextField fieldNumCars;
    private JTextField fieldNumFloors;
    private JTextField fieldTime;
    
    public GuiRobert(Model model) {
        super(model);
        //LEGE USER INTERFACE MET ALLEMAAL LEGE TEKSTBLOKKEN EN WEET IK VEEL WAT VOOR DINGEN JE ER ALLEMAAL IN WILT STOPPEN.
        fieldNumCars = new JTextField();
        fieldNumFloors = new JTextField();
        fieldTime = new JTextField();
        
        //alle tekstvelden etc aanmaken
    }

    @Override
    public void updateView() {
        //tekstvelden updaten
        int numCars = model.getNumCars();
        fieldNumCars.setText("Number of cars " + numCars + ".");
        fieldNumCars.setEditable(false);        
        int numFloors = model.getNumFloors();
        fieldNumFloors.setText("Number of floors " + numFloors + ".");
        fieldNumFloors.setEditable(false);
        String time = model.getTime();
        fieldTime.setText("Time :" + time + ".");
        fieldTime.setEditable(false);
        this.add(fieldNumCars);
        this.add(fieldNumFloors);
        this.add(fieldTime);
        //Alle releveante info ophalen en die in de tekst velden stoppen. 
        repaint();
    }

}
