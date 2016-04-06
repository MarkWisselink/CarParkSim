package CarParkSim.main;

import javax.swing.*;

import CarParkSim.controller.*;
import CarParkSim.logic.*;
import CarParkSim.view.*;

public class CarParkSim {
	private Model model;
	private JFrame screen;
	private AbstractView carparkView;
	private Controller controller;
	
	public CarParkSim(String[] args) {
		model=new Model();
                carparkView = new CarParkView(model);
                model.addView(carparkView);
		controller=new Controller(model);
		screen=new JFrame("Car Park Simulator");
		screen.setSize(450, 285);
		//screen.setResizable(false);
		screen.setLayout(null);
		screen.getContentPane().add(carparkView);
		screen.getContentPane().add(controller);
		carparkView.setBounds(10, 10, 200, 200);
		controller.setBounds(0, 210, 450, 50);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
}
