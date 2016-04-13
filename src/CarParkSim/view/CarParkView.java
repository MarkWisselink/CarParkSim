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

    private int floorXDistance = 260;
    private int rowXDistance = 75;
    private int secondRowXOfset = 20; // used only if row is uneven; rows start at 0
    private int yMargin = 60;
    private int placeYDistance = 10;
    private int placeWidth = 20 - 1;
    private int placeHeight = 10 - 1;
    private int prevFloors = 0;
    private int prevRows = 0;
    private int prevPlaces = 0;

    /**
     * Constructor for objects of class CarPark
     *
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

    private boolean parkChanged() {
        boolean ret = false;
        if (prevFloors != model.getNumFloors()) {
            ret = true;
            prevFloors = model.getNumFloors();
        }
        if (prevRows != model.getNumRows()) {
            ret = true;
            prevRows = model.getNumRows();
        }
        if (prevPlaces != model.getNumPlaces()) {
            ret = true;
            prevPlaces = model.getNumPlaces();
        }
        return ret;
    }

    /**
     * go through every location, and make sure to paint the current version
     */
    @Override
    public void updateView() {
        // Create a new car park image if the size has changed.

        if (!size.equals(getSize()) || parkChanged()) {
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
                    switch (state) {
                        case 0:
                            color = Color.WHITE;
                            break;

                        case 1:
                            color = Color.YELLOW;
                            break;

                        case 2:
                            color = Color.DARK_GRAY;
                            break;

                        case 12:
                            color = Color.LIGHT_GRAY;
                            break;

                        case 3:
                            color = Color.BLUE;
                            break;

                        case 4:
                            color = Color.RED;
                            break;

                        case 14:
                            color = Color.PINK;
                            break;

                        default:
                            color = Color.MAGENTA;
                            break;
                    }
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
                location.getFloor() * floorXDistance + (1 + (int) Math.floor(location.getRow() * 0.5)) * rowXDistance + (location.getRow() % 2) * secondRowXOfset,
                yMargin + location.getPlace() * placeYDistance,
                placeWidth,
                placeHeight);
    }
}
