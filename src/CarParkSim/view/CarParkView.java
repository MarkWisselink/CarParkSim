package CarParkSim.view;

import CarParkSim.objects.*;
import CarParkSim.logic.*;
import java.awt.*;

/**
 *
 * @author Nienke's boys
 */
public class CarParkView extends AbstractView {

    private Dimension size;
    private Image carParkImage;

    /**
     * Constructor for objects of class CarPark
     * @param model reference to the model
     */
    public CarParkView(Model model) {
        super(model);
        size = new Dimension(0, 0);
    }

    /**
     * 
     * @return Dimension class, with default size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * 
     * @param g paint a graphic into the image
     */
    @Override
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        } else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * go through every location, and make sure to paint the current verion
     */
    @Override
    public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for (int floor = 0; floor < model.getNumFloors(); floor++) {
            for (int row = 0; row < model.getNumRows(); row++) {
                for (int place = 0; place < model.getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    int state = model.getLocInfo(location);
                    Color color;
                    switch(state){
                        case 0: color = Color.WHITE;
                        break;
                        
                        case 1: color = Color.YELLOW;
                        break;
                        
                        case 2: color = Color.DARK_GRAY;
                        break;
                        
                        case 12: color = Color.LIGHT_GRAY;
                        break;
                        
                        case 3: color = Color.BLUE;
                        break;
                        
                        case 4: color = Color.RED;
                        break;
                        
                        case 14: color = Color.PINK;
                        break;
                        
                        default: color = Color.MAGENTA;
                        break;
                    }
                    //Color color = car == null ? Color.white : Color.red;
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
