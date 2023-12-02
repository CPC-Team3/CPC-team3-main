package energy_controller;

import java.util.ArrayList;

import capstone.Log;
import capstone.exception_handler.energySwitchException;

public class Controller extends Thread{
	// Oder in energy source
	int SUNNY = 0;
	int WINDY = 1;
	
	
	// Attributes	
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	Energy currentEnergySource;
	Boolean output = false;
	ArrayList<Weather> wheatherForecast;
	
	// Constructor
	public Controller(int id, ArrayList<Weather> wheatherForecast){
		logger = new Log("controller\\controller"+String.valueOf(this.id),"Controller "+ String.valueOf(this.id));
		logger.init();
		this.id = id;
		this.wheatherForecast = wheatherForecast;
	}
	
	// Functionalities
	
	public void run() {
		startSupply();
		Weather setWeather = new Weather();
		while (true) {
			Weather currentWeather = new Weather();
			
			if (currentWeather.equals(setWeather)){
				if (currentWeather.sunny) {
					
					// set to solar
					try {
						switcher(EnergySource.get(SUNNY));
					} catch (energySwitchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (currentWeather.windy) {
					// set to wind
					try {
						switcher(EnergySource.get(WINDY));
					} catch (energySwitchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				setWeather = currentWeather;
					
			}
					
			
		}
	}
	
	public void addEnergy(Energy newEnergy) {
		/*
		 * Add new energy source to the controller
		 */
		logger.info("Adding new energy source - " + newEnergy);
		EnergySource.add(newEnergy);		
	}
	
	// energy source switcher
	void switcher(Energy newEnergySource) throws energySwitchException {
		/*
		 * switch the current energy to new energySource
		 */
		logger.info("Change energy source to " + newEnergySource);
		this.currentEnergySource = newEnergySource;
	}
		
	// start supply
	void startSupply(){
		currentEnergySource = EnergySource.get(0);
		logger.info("Start supplying with current energy " + this.currentEnergySource);
		this.output = true;
	}
	
	// stop supply
	void stopSupply(){
		this.output = true;
	}
		
	// setters and getters
	public ArrayList<Energy> getEnergySource() {
		return EnergySource;
	}

	public void setEnergySource(ArrayList<Energy> energySource) {
		EnergySource = energySource;
	}
	
	void addEnergySource(Energy newEnergy) {
		this.EnergySource.add(newEnergy);
	}
	
	void removeEnergySource(Energy unwantedEnergy) {
		this.EnergySource.remove(unwantedEnergy);
	}
	
	
	//test
	public static void main(String[] args) {
		
		Controller energyController1 = new Controller(0, new ArrayList<Weather>());
		energyController1.addEnergy(new Energy("solar","renewable"));
		energyController1.addEnergy(new Energy("wind","renewable"));
		
		energyController1.start();
		
	}
	
	
	
		

}
