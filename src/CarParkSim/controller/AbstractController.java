package CarParkSim.controller;

import CarParkSim.logic.*;
import javax.swing.*;

/**
 *
 * @author Nienke's boys
 */
public abstract class AbstractController extends JPanel {

    protected Model model;

    /**
     *
     * @param model the model that this controller controls
     */
    public AbstractController(Model model) {
        this.model = model;
    }
}
