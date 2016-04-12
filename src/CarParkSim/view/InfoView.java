package CarParkSim.view;

import CarParkSim.logic.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author Nienke's boys
 */
public class InfoView extends AbstractView implements ActionListener {

    private HashMap<String, Boolean> selectedStats;
    private JTextField fieldNumCarsEntering;
    private JTextField fieldNumCarsParked;
    private JTextField fieldNumCarsPaying;
    private JTextField fieldNumCarsExiting;
    private JTextField fieldNumFloors;
    private JTextField fieldTime;
    private JTextField fieldPayment;
    private JButton bluescreen;
    private JCheckBox checkNumCarsEntering;
    private JCheckBox checkNumCarsParked;
    private JCheckBox checkNumCarsPaying;
    private JCheckBox checkNumCarsExiting;
    private JCheckBox checkNumFloors;
    private JCheckBox checkTime;
    private JCheckBox checkPayment;

    /**
     *
     * @param model the model this view uses
     */
    public InfoView(Model model) {
        super(model);
        selectedStats = new HashMap<>();
//        selectedStats.put("numCarsEntering", true);
//        selectedStats.put("numCarsParked",true);
//        selectedStats.put("numCarsPaying",true);
//        selectedStats.put("numCarsExiting",true);
//        selectedStats.put("numFloors",true);
//        selectedStats.put("time",true);
//        selectedStats.put("payment",true);

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

        fieldPayment = new JTextField(27);
        fieldPayment.setEditable(false);

        bluescreen = new JButton("Button voor BLUESCREEN!");
        //add(bluescreen);
        //image = new ImageIcon(getClass().getResource(""));

        /*
        Onderstaande code voegt alle checkboxen toe. 
         */
        checkNumCarsEntering = new JCheckBox("Show number of cars entering");
        checkNumCarsEntering.setMnemonic(KeyEvent.VK_C);
        checkNumCarsEntering.setSelected(true);

        checkNumCarsParked = new JCheckBox("Show number of cars parked");
        checkNumCarsParked.setMnemonic(KeyEvent.VK_C);
        checkNumCarsParked.setSelected(false);

        checkNumCarsPaying = new JCheckBox("Show number of cars paying");
        checkNumCarsPaying.setMnemonic(KeyEvent.VK_C);
        checkNumCarsPaying.setSelected(false);

        checkNumCarsExiting = new JCheckBox("Show number of cars exiting");
        checkNumCarsExiting.setMnemonic(KeyEvent.VK_C);
        checkNumCarsExiting.setSelected(true);

        checkNumFloors = new JCheckBox("Show number of parkingspots");
        checkNumFloors.setMnemonic(KeyEvent.VK_C);
        checkNumFloors.setSelected(true);

        checkTime = new JCheckBox("Show time");
        checkTime.setMnemonic(KeyEvent.VK_C);
        checkTime.setSelected(false);

        checkPayment = new JCheckBox("Show payment check");
        checkPayment.setMnemonic(KeyEvent.VK_C);
        checkPayment.setSelected(false);

        bluescreen.addActionListener(this);

        //alle tekstvelden etc aanmaken
    }

    @Override
    public void updateView() {

        /*
        Wanneer checkbox is geselecteerd dan plaats <hashmap> true
         */
        if (checkNumCarsEntering.isSelected()) {
            selectedStats.put("numCarsEntering", true);

        }
        else {
            selectedStats.put("numCarsEntering", false);
        }

        if (checkNumCarsParked.isSelected()) {
            selectedStats.put("numCarsParked", true);
        }
        else {
            selectedStats.put("numCarsParked", false);
        }

        if (checkNumCarsPaying.isSelected()) {
            selectedStats.put("numCarsPaying", true);
        }
        else {
            selectedStats.put("numCarsPaying", false);
        }

        if (checkNumCarsExiting.isSelected()) {
            selectedStats.put("numCarsExiting", true);
        }
        else {
            selectedStats.put("numCarsExiting", false);
        }

        if (checkNumFloors.isSelected()) {
            selectedStats.put("numFloors", true);
        }
        else {
            selectedStats.put("numFloors", false);
        }

        if (checkTime.isSelected()) {
            selectedStats.put("time", true);
        }
        else {
            selectedStats.put("time", false);
        }

        if (checkPayment.isSelected()) {
            selectedStats.put("payment", true);
        }
        else {
            selectedStats.put("payment", false);
        }

        //tekstvelden updaten
        fieldNumCarsEntering.setText("Number of cars in entrance queue: " + model.getNumCars("enterq"));

        fieldNumCarsParked.setText("Number of cars parked: " + model.getNumCars("parked"));

        fieldNumCarsPaying.setText("Number of cars in payment queue: " + model.getNumCars("payq"));

        fieldNumCarsExiting.setText("Number of cars in exit queue: " + model.getNumCars("exitq"));

        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("total") + " (" + model.getNumParkingPlaces("free") + " spots free)");

        fieldTime.setText("Time: " + model.getTime());

        fieldPayment.setText("Total revenue " + model.getStat("revenueTotal") + ".");

        //add(fieldNumCarsEntering); 
        //add(fieldNumCarsParked);
        //add(fieldNumCarsPaying);
        //add(fieldNumCarsExiting);
        //add(fieldNumFloors);
        //add(fieldPayment);
        //add(fieldTime);
        add(checkNumCarsEntering);
        add(checkNumCarsParked);
        add(checkNumCarsPaying);
        add(checkNumCarsExiting);
        add(checkNumFloors);
        add(checkTime);
        add(checkPayment);
        //add(bluescreen);
        //als check of je t moet adden:

        /*
          Wanneer de iets is geselecteerd wordt field toegevoegd en op visible gezet.
          Wanneer het niet is geselecteerd wordt de visibilty van het field op false geplaatst.
         */
        if (selectedStats.get("numCarsEntering")) {
            add(fieldNumCarsEntering);
            fieldNumCarsEntering.setVisible(true);
        }
        else {
            fieldNumCarsEntering.setVisible(false);
        }

        if (selectedStats.get("numCarsParked")) {
            add(fieldNumCarsParked);
            fieldNumCarsParked.setVisible(true);
        }
        else {
            fieldNumCarsParked.setVisible(false);
        }

        if (selectedStats.get("numCarsPaying")) {
            add(fieldNumCarsPaying);
            fieldNumCarsPaying.setVisible(true);
        }
        else {
            fieldNumCarsPaying.setVisible(false);
        }

        if (selectedStats.get("numCarsExiting")) {
            add(fieldNumCarsExiting);
            fieldNumCarsExiting.setVisible(true);
        }
        else {
            fieldNumCarsExiting.setVisible(false);
        }

        if (selectedStats.get("numFloors")) {
            add(fieldNumFloors);
            fieldNumFloors.setVisible(true);
        }
        else {
            fieldNumFloors.setVisible(false);
        }
        if (selectedStats.get("time")) {
            add(fieldTime);
            fieldTime.setVisible(true);
        }
        else {
            fieldTime.setVisible(false);
        }

        if (selectedStats.get("payment")) {
            add(fieldPayment);
            fieldPayment.setVisible(true);
        }
        else {
            fieldPayment.setVisible(false);
        }

        //Alle releveante info ophalen en die in de tekst velden stoppen. 
        validate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bluescreen) {
            //doe dingen

            //RYAN RAMPE STAMPEN 
        }

        //als een checkbox ingevuld wordt doe je:
    }

}
