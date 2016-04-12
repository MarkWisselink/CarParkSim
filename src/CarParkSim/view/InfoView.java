package CarParkSim.view;

import CarParkSim.logic.*;

import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

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
    private JTextField fieldPayment;
    private JButton statsbutton;
    private JCheckBox checkNumCarsEntering;
    private JCheckBox checkNumCarsParked;
    private JCheckBox checkNumCarsPaying;
    private JCheckBox checkNumCarsExiting;
    private JCheckBox checkNumFloors;
    private JCheckBox checkTime;
    private JCheckBox checkPayment;

    public InfoView(Model model) {
        super(model);
        statsbutton = new JButton("Stats");
        add(statsbutton);
        statsbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // this makes sure the button you are pressing is the button variable
                if(e.getSource() == statsbutton) {
                    showFrame();
                }
            }
        });

        fieldNumCarsEntering = new JTextField(27);
        fieldNumCarsEntering.setEditable(false);
        add(fieldNumCarsEntering);
        fieldNumCarsEntering.setVisible(true);
        fieldNumCarsEntering.setText("Number of cars in entrance queue: " + model.getNumCars("enterq"));

        fieldNumCarsParked = new JTextField(27);
        fieldNumCarsParked.setEditable(false);

        fieldNumCarsPaying = new JTextField(27);
        fieldNumCarsPaying.setEditable(false);

        fieldNumCarsExiting = new JTextField(27);
        fieldNumCarsExiting.setEditable(false);
        add(fieldNumCarsExiting);
        fieldNumCarsExiting.setVisible(true);
        fieldNumCarsExiting.setText("Number of cars in exit queue: " + model.getNumCars("exitq"));

        fieldNumFloors = new JTextField(27);
        fieldNumFloors.setEditable(false);
        add(fieldNumFloors);
        fieldNumFloors.setVisible(true);
        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("total") + " (" + model.getNumParkingPlaces("free") + " spots free)");

        fieldTime = new JTextField(27);
        fieldTime.setEditable(false);

        fieldPayment = new JTextField(27);
        fieldPayment.setEditable(false);

    }

    public void showFrame() {
        JFrame statsframe = new JFrame("Edit input");
        statsframe.setDefaultCloseOperation(statsframe.DISPOSE_ON_CLOSE);
        JPanel statspanel = new JPanel();

        statsframe.setContentPane(statspanel);
        statsframe.setSize(300, 300);
        statsframe.setVisible(true);

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

        statspanel.add(checkNumCarsEntering);
        statspanel.add(checkNumCarsParked);
        statspanel.add(checkNumCarsPaying);
        statspanel.add(checkNumCarsExiting);
        statspanel.add(checkNumFloors);
        statspanel.add(checkTime);
        statspanel.add(checkPayment);

        checkNumCarsEntering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNumCarsEntering.isSelected()) {
                    add(fieldNumCarsEntering);
                    fieldNumCarsEntering.setVisible(true);
                } else {
                    fieldNumCarsEntering.setVisible(false);
                }
            }
        });

        checkNumCarsParked.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNumCarsParked.isSelected()) {
                    add(fieldNumCarsParked);
                    fieldNumCarsParked.setVisible(true);
                } else {
                    fieldNumCarsParked.setVisible(false);
                }
            }
        });

        checkNumCarsPaying.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNumCarsPaying.isSelected()) {
                    add(fieldNumCarsPaying);
                    fieldNumCarsPaying.setVisible(true);
                } else {
                    fieldNumCarsPaying.setVisible(false);
                }
            }
        });

        checkNumCarsExiting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNumCarsExiting.isSelected()) {
                    add(fieldNumCarsExiting);
                    fieldNumCarsExiting.setVisible(true);
                } else {
                    fieldNumCarsExiting.setVisible(false);
                }
            }
        });

        checkNumFloors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkNumFloors.isSelected()) {
                    add(fieldNumFloors);
                    fieldNumFloors.setVisible(true);
                } else {
                    fieldNumFloors.setVisible(false);
                }
            }
        });

        checkTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTime.isSelected()) {
                    add(fieldTime);
                    fieldTime.setVisible(true);
                } else {
                    fieldTime.setVisible(false);
                }
            }
        });

        checkPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(fieldNumCarsEntering);
                if (checkPayment.isSelected()) {
                    add(fieldPayment);
                    fieldPayment.setVisible(true);
                } else {
                    fieldPayment.setVisible(false);
                }
            }
        });

    }

    @Override
    public void updateView() {

        //tekstvelden updaten
        fieldNumCarsEntering.setText("Number of cars in entrance queue: " + model.getNumCars("enterq"));

        fieldNumCarsParked.setText("Number of cars parked: " + model.getNumCars("parked"));

        fieldNumCarsPaying.setText("Number of cars in payment queue: " + model.getNumCars("payq"));

        fieldNumCarsExiting.setText("Number of cars in exit queue: " + model.getNumCars("exitq"));

        fieldNumFloors.setText("Total number of parking spots: " + model.getNumParkingPlaces("total") + " (" + model.getNumParkingPlaces("free") + " spots free)");

        fieldTime.setText("Time: " + model.getTime());

        fieldPayment.setText("Total revenue " + model.getStat("totalRevenue") + ".");

        validate();
        repaint();
    }

}
