package CarParkSim.controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import CarParkSim.view.*;
import CarParkSim.logic.*;

import java.awt.event.*;
import java.util.HashMap;


/**
 *
 * @author Nienke's boys
 */
public class Controller extends AbstractController implements ActionListener {

    private JButton start;
    private JButton stop;
    private JButton faster;
    private JButton slower;
    private JButton input;
    private JButton stats;
    private JSlider floor;
    private JSlider row;
    private JSlider place;
    private JSlider gate;
    private JTextField floortext;
    private JTextField rowtext;
    private JTextField placetext;
    private JTextField gatetext;private HashMap<String, Boolean> selectedStats;
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
     * @param model the model this controller controls
     */

    public Controller(Model model) {
        super(model);

        setSize(450, 100);
        input = new JButton("Input");
        input.addActionListener(this);
        faster = new JButton("Faster");
        faster.addActionListener(this);
        slower = new JButton("Slower");
        slower.addActionListener(this);
        start = new JButton("Start");
        start.addActionListener(this);
        stop = new JButton("Stop");
        stop.addActionListener(this);
        stats = new JButton("Stats");
        stats.addActionListener(this);

        setLayout(null);
        add(slower);
        add(faster);
        add(start);
        add(stop);
        add(input);
        add(stats);
        start.setBounds(100, 35, 90, 30);
        stop.setBounds(200, 35, 90, 30);
        faster.setBounds(330, 35, 90, 30);
        slower.setBounds(430, 35, 90, 30);
        input.setBounds(570, 35, 90, 30);
        stats.setBounds(650, 35, 90, 30);
        setVisible(true);
    }

    /**
     *
     * @param e the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            model.start();
        }

        if (e.getSource() == stop) {
            model.stop();
        }

        if (e.getSource() == faster) {
            model.speedUp();
        }

        if (e.getSource() == slower) {
            model.slowDown();
        }
        
        if (e.getSource() == stats) {
            JFrame statsframe = new JFrame("stats");
            statsframe.setDefaultCloseOperation(statsframe.DISPOSE_ON_CLOSE);
            JPanel statspanel = new JPanel();
            
            selectedStats = new HashMap<>();
//          selectedStats.put("numCarsEntering", true);
//          selectedStats.put("numCarsParked",true);
//          selectedStats.put("numCarsPaying",true);
//          selectedStats.put("numCarsExiting",true);
//          selectedStats.put("numFloors",true);
//          selectedStats.put("time",true);
//          selectedStats.put("payment",true);
        
        
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

            if(checkNumCarsEntering.isSelected())
        {
            selectedStats.put("numCarsEntering", true);
            
        }
        else {
            selectedStats.put("numCarsEntering", false);
        }
        
        if(checkNumCarsParked.isSelected()){
            selectedStats.put("numCarsParked",true);
        }
        else{selectedStats.put("numCarsParked",false);}
        
        if(checkNumCarsPaying.isSelected()){
            selectedStats.put("numCarsPaying",true);
            }
        else{selectedStats.put("numCarsPaying",false);}
        
        if(checkNumCarsExiting.isSelected()){
            selectedStats.put("numCarsExiting",true);
        }
        else {selectedStats.put("numCarsExiting",false);}
        
        if(checkNumFloors.isSelected()){
            selectedStats.put("numFloors",true);
        }
        else{selectedStats.put("numFloors",false);}
        
        if(checkTime.isSelected()){
            selectedStats.put("time",true);
        }
        else{selectedStats.put("time",false);}
        
        if(checkPayment.isSelected()){
            selectedStats.put("payment",true);
        }
        else{selectedStats.put("payment",false);}
        
        //tekstvelden updaten
        fieldNumCarsEntering.setText("Number of cars in entrance queue: " + model.getNumCars("enterq"));

        fieldNumCarsParked.setText("Number of cars parked: " + model.getNumCars("parked"));

        fieldNumCarsPaying.setText("Number of cars in payment queue: " + model.getNumCars("payq"));

        fieldNumCarsExiting.setText("Number of cars in exit queue: " + model.getNumCars("exitq"));

        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("total") + " (" + model.getNumParkingPlaces("free") + " spots free)");

        fieldTime.setText("Time: " + model.getTime());

        fieldPayment.setText("Total revenue " + model.getStat("totalRevenue") + ".");

        //add(fieldNumCarsEntering); 
        //add(fieldNumCarsParked);
        //add(fieldNumCarsPaying);
        //add(fieldNumCarsExiting);
        //add(fieldNumFloors);
        //add(fieldPayment);
        //add(fieldTime);
        statspanel.add(checkNumCarsEntering);
        statspanel.add(checkNumCarsParked);
        statspanel.add(checkNumCarsPaying);
        statspanel.add(checkNumCarsExiting);
        statspanel.add(checkNumFloors);
        statspanel.add(checkTime);
        statspanel.add(checkPayment);
        //add(bluescreen);
        //als check of je t moet adden:
        
        /*
          Wanneer de iets is geselecteerd wordt field toegevoegd en op visible gezet.
          Wanneer het niet is geselecteerd wordt de visibilty van het field op false geplaatst.
        */
        if(selectedStats.get("numCarsEntering"))
        {
           statspanel.add(fieldNumCarsEntering);
           fieldNumCarsEntering.setVisible(true);
        }
        else
        {
            fieldNumCarsEntering.setVisible(false);
        }
       
        if(selectedStats.get("numCarsParked"))
        {
            statspanel.add(fieldNumCarsParked);
            fieldNumCarsParked.setVisible(true);
        }
        else
        {
          fieldNumCarsParked.setVisible(false);   
        }
        
        if(selectedStats.get("numCarsPaying")){
            statspanel.add(fieldNumCarsPaying);
            fieldNumCarsPaying.setVisible(true);
        }
        else{
            fieldNumCarsPaying.setVisible(false);
        }
        
        if(selectedStats.get("numCarsExiting")){
            statspanel.add(fieldNumCarsExiting);
            fieldNumCarsExiting.setVisible(true);
        }
        else{
            fieldNumCarsExiting.setVisible(false);
        }
        
        if(selectedStats.get("numFloors")){
            statspanel.add(fieldNumFloors);
            fieldNumFloors.setVisible(true);
        }
        else{
            fieldNumFloors.setVisible(false);
        }
        if(selectedStats.get("time")){
            statspanel.add(fieldTime);
            fieldTime.setVisible(true);
        }
        else{fieldTime.setVisible(false);}
        
        if(selectedStats.get("payment")){
            statspanel.add(fieldPayment);
            fieldPayment.setVisible(true);
        }
        else{fieldPayment.setVisible(false);}
            
          statsframe.setContentPane(statspanel);
            statsframe.setSize(300, 200);
            statsframe.setVisible(true);
        } 
        
        
        if (e.getSource() == input) {
            JFrame frame = new JFrame("Edit input");
            frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
            JPanel mainframe = new JPanel();

            // Labels
            JLabel floorlabel = new JLabel("Floors:");
            JLabel rowlabel = new JLabel("Rows:");
            JLabel placelabel = new JLabel("Places:");
            JLabel gatelabel = new JLabel("Gates:");

            // TextFields
            floortext = new JTextField("3");
            floortext.setEditable(false);
            rowtext = new JTextField("6");
            rowtext.setEditable(false);
            placetext = new JTextField("30");
            placetext.setEditable(false);
            gatetext = new JTextField("2");
            gatetext.setEditable(false);

            // Sliders
            floor = new JSlider(1,3,3);
            floor.setMajorTickSpacing(1);
            floor.setPaintTicks(true);
            floor.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    model.setNumFloors(floor.getValue());
                    floortext.setText("" + floor.getValue());
                }
            });

            row = new JSlider(1,6,6);
            row.setMajorTickSpacing(1);
            row.setPaintTicks(true);
            row.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    model.setNumRows(row.getValue());
                    rowtext.setText("" + row.getValue());
                }
            });

            place = new JSlider(1,30,30);
            place.setMajorTickSpacing(10);
            place.setMinorTickSpacing(1);
            place.setPaintTicks(true);
            place.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    model.setNumPlaces(place.getValue());
                    placetext.setText("" + place.getValue());
                }
            });

            gate = new JSlider(1,3,2);
            gate.setMajorTickSpacing(1);
            gate.setPaintTicks(true);
            gate.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    model.setNumberOfGates(gate.getValue());
                    gatetext.setText("" + gate.getValue());
                }
            });

            // Add to frame
            mainframe.add(floorlabel);
            mainframe.add(floor);
            mainframe.add(floortext);

            mainframe.add(rowlabel);
            mainframe.add(row);
            mainframe.add(rowtext);

            mainframe.add(placelabel);
            mainframe.add(place);
            mainframe.add(placetext);

            mainframe.add(gatelabel);
            mainframe.add(gate);
            mainframe.add(gatetext);

            // Set frame
            frame.setContentPane(mainframe);
            frame.setSize(300, 200);
            frame.setVisible(true);
        
                       
        }
        
    }
    
}


