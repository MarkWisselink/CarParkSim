package CarParkSim.controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import CarParkSim.logic.*;

import java.awt.event.*;

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
    private JSlider floor;
    private JSlider row;
    private JSlider place;
    private JSlider gate;
    private JSlider weekday;
    private JSlider weekend;
    private JSlider nightrate;
    private JTextField floortext;
    private JTextField rowtext;
    private JTextField placetext;
    private JTextField gatetext;
    private JTextField weekdaytext;
    private JTextField weekendtext;
    private JTextField nightratetext;

    /**
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

        setLayout(null);
        add(slower);
        add(faster);
        add(start);
        add(stop);
        add(input);
        start.setBounds(100, 35, 90, 30);
        stop.setBounds(200, 35, 90, 30);
        faster.setBounds(330, 35, 90, 30);
        slower.setBounds(430, 35, 90, 30);
        input.setBounds(570, 35, 90, 30);
        setVisible(true);
    }

    /**
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

        if (e.getSource() == input) {
                JFrame frame = new JFrame("Edit input");
                frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
                JPanel mainframe = new JPanel();

                // Labels
                JLabel floorlabel = new JLabel("Floors:");
                JLabel rowlabel = new JLabel("Rows:");
                JLabel placelabel = new JLabel("Places:");
                JLabel gatelabel = new JLabel("Gates:");
                JLabel weekdaylabel = new JLabel("Weekday arrivals:");
                JLabel weekendlabel = new JLabel("Weekend arrivals:");
                JLabel nightratelabel = new JLabel("Night reduction rate:");

                // TextFields
                floortext = new JTextField("3");
                floortext.setEditable(false);
                rowtext = new JTextField("6");
                rowtext.setEditable(false);
                placetext = new JTextField("30");
                placetext.setEditable(false);
                gatetext = new JTextField("2");
                gatetext.setEditable(false);
                weekdaytext = new JTextField("80");
                weekdaytext.setEditable(false);
                weekendtext = new JTextField("100");
                weekendtext.setEditable(false);
                nightratetext = new JTextField("3");
                nightratetext.setEditable(false);

                // Sliders
                floor = new JSlider(1, 3, 3);
                floor.setMajorTickSpacing(1);
                floor.setPaintTicks(true);
                floor.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        model.setNumFloors(floor.getValue());
                        floortext.setText("" + floor.getValue());
                    }
                });

                row = new JSlider(1, 6, 6);
                row.setMajorTickSpacing(1);
                row.setPaintTicks(true);
                row.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        model.setNumRows(row.getValue());
                        rowtext.setText("" + row.getValue());
                    }
                });

                place = new JSlider(1, 30, 30);
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

                gate = new JSlider(1, 3, 2);
                gate.setMajorTickSpacing(1);
                gate.setPaintTicks(true);
                gate.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        model.setNumberOfGates(gate.getValue());
                        gatetext.setText("" + gate.getValue());
                    }
                });

                weekday = new JSlider(20, 160, 80);
                weekday.setMajorTickSpacing(20);
                weekday.setPaintTicks(true);
                weekday.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        model.changeSetting("weekDayArrivals", weekday.getValue());
                        weekdaytext.setText("" + weekday.getValue());
                    }
                });

                weekend = new JSlider(20, 200, 100);
                weekend.setMajorTickSpacing(20);
                weekend.setPaintTicks(true);
                weekend.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        model.changeSetting("weekendArrivals", weekend.getValue());
                        weekendtext.setText("" + weekend.getValue());
                    }
                });

                nightrate = new JSlider(1, 5, 3);
                nightrate.setMajorTickSpacing(1);
                nightrate.setPaintTicks(true);
                nightrate.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                      model.changeSetting("nightReductionRate", nightrate.getValue());
                        nightratetext.setText("" + nightrate.getValue());
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

                mainframe.add(weekdaylabel);
                mainframe.add(weekday);
                mainframe.add(weekdaytext);

                mainframe.add(weekendlabel);
                mainframe.add(weekend);
                mainframe.add(weekendtext);

                mainframe.add(nightratelabel);
                mainframe.add(nightrate);
                mainframe.add(nightratetext);


                // Set frame
                frame.setContentPane(mainframe);
                frame.setSize(300, 200);
                frame.setVisible(true);


            }

        }

    }

