package CarParkSim.logic;

import java.util.*;
import CarParkSim.view.*;

/**
 *
 * @author Nienke's boys
 */
public abstract class AbstractModel {

    private List<AbstractView> views;

    /**
     *
     */
    public AbstractModel() {
        views = new ArrayList<AbstractView>();
    }

    /**
     *
     * @param view a graphical view to show the relevant data saved in the model
     */
    public void addView(AbstractView view) {
        views.add(view);
    }

    /**
     * make sure all views know to update
     */
    public void notifyViews() {
        for (AbstractView v : views) {
            v.updateView();
        }
    }
}
