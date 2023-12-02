package system;

import capstone.Log;
import charging_station.ChargingStation;

public class ChargingSystem extends Thread{
	/*
	 *  combine everything as a system
	 */
	
	// Attribute
	private String bookFilePath;
	private ChargingStation station1; // main charging station
	Log logger;
	int id;
	
	
	// constructor
	public ChargingSystem(String bookFilePath) {
		this.bookFilePath = bookFilePath;
		logger = new Log("system\\system"+String.valueOf(getId_()),"System "+ String.valueOf(getId_()));
		
	}
	
	

	// functionalities
	public void init(){
		logger.info("Initialize charging station");
		station1 = new ChargingStation(0,bookFilePath);
		station1.addCharger(0);
		station1.addCharger(1);
		logger.info("Charging station is ready");
	}
	
	public void run() {
		
		logger.info("starting system1");
		init();
		station1.start();
		try {
			station1.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		logger.info("system1 finish");
	}
	
	// setters and getters
	private int getId_() {
		return id;
	}
}
