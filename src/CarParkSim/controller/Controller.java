package CarParkSim.controller;

import javax.swing.*;
import CarParkSim.logic.*;
import java.awt.event.*;

/**
 *
 * @author Nienke's boys
 */
public class Controller extends AbstractController implements ActionListener {

    private JButton start;
    private JButton stop;

    /**
     *
     * @param model the model this controller controls
     */

    public Controller(Model model) {
        super(model);

        setSize(450, 100);
        start = new JButton("Start");
        start.addActionListener(this);
        stop = new JButton("Stop");
        stop.addActionListener(this);

        this.setLayout(null);
        add(start);
        add(stop);
        start.setBounds(300, 35, 70, 30);
        stop.setBounds(400, 35, 70, 30);
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
    }
}
