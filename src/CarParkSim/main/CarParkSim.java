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
	private Controller controller;
	
    /**
     *
     * @param args from the command line, currently unused
     */
    public CarParkSim(String[] args) {
		model=new Model();
                carparkView = new CarParkView(model);
                model.addView(carparkView);
		controller=new Controller(model);
		screen=new JFrame("Car Park Simulator");
		screen.setSize(450, 450);
		//screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(carparkView);
		screen.getContentPane().add(controller);
		carparkView.setBounds(0, 0, 200, 370);
		controller.setBounds(0, 210, 450, 50);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
}
