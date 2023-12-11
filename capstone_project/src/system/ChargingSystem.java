package system;

import java.util.ArrayList;

import capstone.Log;
import capstone.Standard;
import charging_station.ChargingStation;
import energy_controller.Controller;
import energy_controller.Energy;
import energy_controller.Weather;

public class ChargingSystem extends Thread{
	/*
	 *  combine everything as a system
	 */
	
	// Attribute
	public String bookFilePath;
	public ChargingStation station1; // main charging station
	public Controller energyController1;
	ArrayList<int[]> carComChannel;
	Log logger;
	int id;
	String date;
	
	// constructor
	public ChargingSystem(String bookFilePath, ArrayList<int[]> carComChannel) {
		this.bookFilePath = bookFilePath;
		logger = new Log("system\\system"+String.valueOf(getId_()),"System "+ String.valueOf(getId_()), Standard.date);
		this.carComChannel = carComChannel;
		setDate(Standard.date);
		
	}

	// functionalities
	public void init(){
		/*
		 * this function is to initialize each component
		 */
		
		// initialize charging station
		logger.info("Initialize charging station");
		station1 = new ChargingStation(0,bookFilePath, carComChannel);
		station1.addCharger(0);
		station1.addCharger(1);
		logger.info("Charging station is ready");
		
		// initialize energy controller
		logger.info("Initialize energy controller");
		energyController1 = new Controller(0, new ArrayList<Weather>());
		energyController1.addEnergy(new Energy("solar","renewable"));
		energyController1.addEnergy(new Energy("wind","renewable"));
		//add energy source
		logger.info("energy controller is ready");
	}
	
	public void run() {
		
		logger.info("starting system1");
		init();
		
		logger.info("starting all the component");
		station1.start();
		energyController1.start();
		
		try {
			station1.join();
			energyController1.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		// update date and create new logger for new date
		if (this.date != Standard.date) {
			setDate(Standard.date);
			logger = new Log("system\\system"+String.valueOf(getId_()),"System "+ String.valueOf(getId_()), Standard.date);
		}
		logger.info("system1 finish");
	}
	
	// setters and getters
	private int getId_() {
		return id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	//simulation
	public static void main(String[] args) {
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", new ArrayList<int[]>());
		sys1.start();
		
	}
}
