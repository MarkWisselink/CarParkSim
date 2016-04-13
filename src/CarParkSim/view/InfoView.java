package CarParkSim.view;

import CarParkSim.logic.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
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
    private JTextField fieldPayment;
    private JTextField fieldcurrentReservingCar;
    private JTextField fieldtotalReservingCar;
    private JTextField fieldcurrentAdHocCar;
    private JTextField fieldtotalAdHocCar;
    private JTextField fieldcurrentPassholders;
    private JTextField fieldtotalPassholders;
    private JTextField fieldparkedTotal;
    private JTextField fieldrevenueToday;
    private JTextField fieldrevenueTotal;
    private JTextField fieldrevenueExpected;
    private JButton statsbutton;
    private JCheckBox checkNumCarsEntering;
    private JCheckBox checkNumCarsParked;
    private JCheckBox checkNumCarsPaying;
    private JCheckBox checkNumCarsExiting;
    private JCheckBox checkNumFloors;
    private JCheckBox checkTime;
    private JCheckBox checkcurrentReservingCar;
    private JCheckBox checktotalReservingCar;
    private JCheckBox checkcurrentAdHocCar;
    private JCheckBox checktotalAdHoccar;
    private JCheckBox checkcurrentPassholders;
    private JCheckBox checktotalPassholders;
    private JCheckBox checkparkedTotal;
    private JCheckBox checkrevenueToday;
    private JCheckBox checkrevenueTotal;
    private JCheckBox checkrevenueExpected;
    private JFrame statsframe;

    /**
     *
     * @param model the model this view uses
     */
    public InfoView(Model model) {
        super(model);
        statsbutton = new JButton("Stats");
        add(statsbutton);
        statsbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // this makes sure the button you are pressing is the button variable
                if (e.getSource() == statsbutton) {
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
        add(fieldTime);
        fieldTime.setVisible(true);
        fieldTime.setText("Time: " + model.getTime());

        fieldPayment = new JTextField(27);
        fieldPayment.setEditable(false);

        fieldcurrentAdHocCar = new JTextField(27);
        fieldcurrentAdHocCar.setEditable(false);

        fieldtotalAdHocCar = new JTextField(27);
        fieldtotalAdHocCar.setEditable(false);

        fieldcurrentPassholders = new JTextField(27);
        fieldcurrentPassholders.setEditable(false);

        fieldtotalPassholders = new JTextField(27);
        fieldtotalPassholders.setEditable(false);

        fieldcurrentReservingCar = new JTextField(27);
        fieldcurrentReservingCar.setEditable(false);

        fieldtotalReservingCar = new JTextField(27);
        fieldtotalReservingCar.setEditable(false);

        fieldparkedTotal = new JTextField(27);
        fieldparkedTotal.setEditable(false);

        fieldrevenueToday = new JTextField(27);
        fieldrevenueToday.setEditable(false);

        fieldrevenueTotal = new JTextField(27);
        fieldrevenueTotal.setEditable(false);

        fieldrevenueExpected = new JTextField(27);
        fieldrevenueExpected.setEditable(false);

    }

    /**
     * shows a popup window with checkboxes, to select which stats to show
     */
    public void showFrame() {
        if (statsframe == null) {
            statsframe = new JFrame("Edit stats");
        } else {
            // Do something
        }
        statsframe.setDefaultCloseOperation(statsframe.HIDE_ON_CLOSE);
        JPanel statspanel = new JPanel();

        checkrevenueExpected = new JCheckBox("Show expected revenue");
        checkrevenueExpected.setMnemonic(KeyEvent.VK_C);

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
        checkTime.setSelected(true);

        checkcurrentReservingCar = new JCheckBox("Show current reserving cars");
        checkcurrentReservingCar.setMnemonic(KeyEvent.VK_C);
        checkcurrentReservingCar.setSelected(false);

        checktotalReservingCar = new JCheckBox("Show total reserving cars");
        checktotalReservingCar.setMnemonic(KeyEvent.VK_C);
        checktotalReservingCar.setSelected(false);

        checkcurrentAdHocCar = new JCheckBox("Show current ad hoc cars");
        checkcurrentAdHocCar.setMnemonic(KeyEvent.VK_C);
        checkcurrentAdHocCar.setSelected(false);

        checktotalAdHoccar = new JCheckBox("Show total ad hoc cars");
        checktotalAdHoccar.setMnemonic(KeyEvent.VK_C);
        checktotalAdHoccar.setSelected(false);

        checkcurrentPassholders = new JCheckBox("Show current passholder cars");
        checkcurrentPassholders.setMnemonic(KeyEvent.VK_C);
        checkcurrentPassholders.setSelected(false);

        checktotalPassholders = new JCheckBox("Show total ad hoc cars");
        checktotalPassholders.setMnemonic(KeyEvent.VK_C);
        checktotalPassholders.setSelected(false);

        checkparkedTotal = new JCheckBox("Show total parked cars");
        checkparkedTotal.setMnemonic(KeyEvent.VK_C);
        checkparkedTotal.setSelected(false);

        checkrevenueToday = new JCheckBox("Show today's revenue");
        checkrevenueToday.setMnemonic(KeyEvent.VK_C);
        checkrevenueToday.setSelected(false);

        checkrevenueTotal = new JCheckBox("Show total revenue");
        checkrevenueTotal.setMnemonic(KeyEvent.VK_C);
        checkrevenueTotal.setSelected(false);

        statspanel.add(checkNumCarsEntering);
        statspanel.add(checkNumCarsParked);
        statspanel.add(checkNumCarsPaying);
        statspanel.add(checkNumCarsExiting);
        statspanel.add(checkNumFloors);
        statspanel.add(checkTime);
        statspanel.add(checkcurrentAdHocCar);
        statspanel.add(checktotalAdHoccar);
        statspanel.add(checkcurrentPassholders);
        statspanel.add(checktotalPassholders);
        statspanel.add(checkcurrentReservingCar);
        statspanel.add(checktotalReservingCar);
        statspanel.add(checkparkedTotal);
        statspanel.add(checkrevenueToday);
        statspanel.add(checkrevenueTotal);
        statspanel.add(checkrevenueExpected);

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

        checkcurrentReservingCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkcurrentReservingCar.isSelected()) {
                    add(fieldcurrentReservingCar);
                    fieldcurrentReservingCar.setVisible(true);
                } else {
                    fieldcurrentReservingCar.setVisible(false);
                }
            }
        });

        checktotalReservingCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checktotalReservingCar.isSelected()) {
                    add(fieldtotalReservingCar);
                    fieldtotalReservingCar.setVisible(true);
                } else {
                    fieldtotalReservingCar.setVisible(false);
                }
            }
        });

        checkcurrentAdHocCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkcurrentAdHocCar.isSelected()) {
                    add(fieldcurrentAdHocCar);
                    fieldcurrentAdHocCar.setVisible(true);
                } else {
                    fieldcurrentAdHocCar.setVisible(false);
                }
            }
        });

        checktotalAdHoccar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checktotalAdHoccar.isSelected()) {
                    add(fieldtotalAdHocCar);
                    fieldtotalAdHocCar.setVisible(true);
                } else {
                    fieldtotalAdHocCar.setVisible(false);
                }
            }
        });

        checkcurrentPassholders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkcurrentPassholders.isSelected()) {
                    add(fieldcurrentPassholders);
                    fieldcurrentPassholders.setVisible(true);
                } else {
                    fieldcurrentPassholders.setVisible(false);
                }
            }
        });

        checktotalPassholders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checktotalPassholders.isSelected()) {
                    add(fieldtotalPassholders);
                    fieldtotalPassholders.setVisible(true);
                } else {
                    fieldtotalPassholders.setVisible(false);
                }
            }
        });

        checkparkedTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkparkedTotal.isSelected()) {
                    add(fieldparkedTotal);
                    fieldparkedTotal.setVisible(true);
                } else {
                    fieldparkedTotal.setVisible(false);
                }
            }
        });

        checkrevenueToday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkrevenueToday.isSelected()) {
                    add(fieldrevenueToday);
                    fieldrevenueToday.setVisible(true);
                } else {
                    fieldrevenueToday.setVisible(false);
                }
            }
        });

        checkrevenueTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkrevenueTotal.isSelected()) {
                    add(fieldrevenueTotal);
                    fieldrevenueTotal.setVisible(true);
                } else {
                    fieldrevenueTotal.setVisible(false);
                }
            }
        });

        checkrevenueExpected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkrevenueExpected.isSelected()) {
                    add(fieldrevenueExpected);
                    fieldrevenueExpected.setVisible(true);
                } else {
                    fieldrevenueExpected.setVisible(false);
                }
            }
        });

        statsframe.setContentPane(statspanel);
        statsframe.setSize(250, 650);
        statsframe.setVisible(true);
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

        fieldcurrentReservingCar.setText("Number of reserving cars: " + model.getStat("currentReservingCar") + ".");

        fieldtotalReservingCar.setText("Total reserving cars: " + model.getStat("totalReservingCar") + ".");

        fieldcurrentAdHocCar.setText("Number of ad hoc cars: " + model.getStat("currentAdHocCar") + ".");

        fieldtotalAdHocCar.setText("Total ad hoc cars: " + model.getStat("totalAdHocCar") + ".");

        fieldcurrentPassholders.setText("Number of passholder cars: " + model.getStat("currentPassholders") + ".");

        fieldtotalPassholders.setText("Total passholder cars: " + model.getStat("totalPassholders") + ".");

        fieldparkedTotal.setText("Total parked cars: " + model.getStat("parkedTotal") + ".");

        fieldrevenueToday.setText("Today's revenue: " + model.getStat("revenueToday", true) + ".");

        fieldrevenueTotal.setText("Total revenue: " + model.getStat("revenueTotal", true) + ".");

        fieldrevenueExpected.setText("Expected revenue: " + model.getStat("revenueExpected", true) + ".");

        validate();
        repaint();
    }

}
