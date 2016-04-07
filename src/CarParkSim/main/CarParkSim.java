package CarParkSim.main;

import javax.swing.*;

import CarParkSim.controller.*;
import CarParkSim.logic.*;
import CarParkSim.view.*;

/**
 *
 * @author Nienke's boys
 */
public class CarParkSim {
	private Model model;
	private JFrame screen;
	private AbstractView carparkView;
        private AbstractView infoView;
	private Controller controller;
	
    /**
     *
     * @param args from the command line, currently unused
     */
    public CarParkSim(String[] args) {
		model=new Model();
                carparkView = new CarParkView(model);
                infoView = new GuiRobert(model);
		model.addView(carparkView);
                model.addView(infoView);
		controller=new Controller(model);
		screen=new JFrame("Car Park Simulator");
		screen.setSize(1200, 500);
		screen.setLayout(null);
                screen.getContentPane().add(infoView);
		screen.getContentPane().add(carparkView);
		screen.getContentPane().add(controller);
                infoView.setBounds(850,100,320,150);
		carparkView.setBounds(0, 0, 850, 370);
		controller.setBounds(0, 370, 800, 130);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                screen.setVisible(true);
	}
}
