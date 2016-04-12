package CarParkSim.view;

import javax.swing.*;
import CarParkSim.logic.*;

/**
 *
 * @author Nienke's boys
 */
public abstract class AbstractView extends JPanel {

    /**
     *
     */
    protected Model model;

    /**
     *
     * @param model the model to be viewed
     */
    public AbstractView(Model model) {
		this.model=model;
		//model.addView(this);
	}
	
    /**
     *
     * @return find out what model this view is using
     */
    public Model getModel() {
		return model;
	}
	
    /**
     * repaint
     */
    public void updateView() {
		repaint();
	}
}
