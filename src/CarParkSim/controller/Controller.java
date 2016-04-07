package CarParkSim.controller;

import javax.swing.*;
import CarParkSim.logic.*;

import java.awt.*;
import java.awt.event.*;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

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

        this.setLayout(null);
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

        if (e.getSource() == input) {
            JTextField floor = new JTextField();
            JTextField row = new JTextField();
            JTextField place = new JTextField();
            Object[] message = {
                    "Floors:", floor,
                    "Rows:", row,
                    "Places:", place
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Edit input", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                if (floor.getText().equals("")) {
                }
                else {
                    model.setNumFloors(parseInt(floor.getText()));
                }
                if (row.getText().equals("")) {
                }
                else {
                    model.setNumRows(parseInt(row.getText()));
                }
                if (place.getText().equals("")) {
                }
                else {
                    model.setNumPlaces(parseInt(place.getText()));
                }

            }
            else {
                return;
            }
        }
    }
}

