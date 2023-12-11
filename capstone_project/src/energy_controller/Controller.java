package energy_controller;

import java.util.ArrayList;

import capstone.Log;
import capstone.Standard;
import capstone.exception_handler.energySwitchException;

public class Controller extends Thread{
	// Oder in energy source
	
	
	// Attributes	
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	Energy currentEnergySource;
	boolean output = false;
	ArrayList<Weather> wheatherForecast;
	String date;
	
	// Constructor
	public Controller(int id, ArrayList<Weather> wheatherForecast){
		logger = new Log("controller\\controller"+String.valueOf(this.id),"Controller "+ String.valueOf(this.id), Standard.date);
		logger.init();
		this.id = id;
		this.wheatherForecast = wheatherForecast;
		setDate(Standard.date);
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
						switcher(EnergySource.get(Standard.SUNNY));
					} catch (energySwitchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (currentWeather.windy) {
					// set to wind
					try {
						switcher(EnergySource.get(Standard.WINDY));
					} catch (energySwitchException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				setWeather = currentWeather;
					
			}
			// update date and create new logger for new date
			if (this.date != Standard.date) {
				setDate(Standard.date);
				logger = new Log("controller\\controller"+String.valueOf(this.id),"Controller "+ String.valueOf(this.id), Standard.date);
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
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	//test
	public static void main(String[] args) {
		
		Controller energyController1 = new Controller(0, new ArrayList<Weather>());
		energyController1.addEnergy(new Energy("solar","renewable"));
		energyController1.addEnergy(new Energy("wind","renewable"));
		
		energyController1.start();
		
	}
	
	
	
		

}
