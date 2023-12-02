package energy_controller;

import java.util.ArrayList;

import capstone.Log;
import capstone.exception_handler.energySwitchException;

public class Controller {
	// Attributes	
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	Energy currentEnergySource;
	Boolean output = false;
	
	// Constructor
	Controller(int id){
		logger = new Log("charger"+String.valueOf(this.id),"Charger "+ String.valueOf(this.id));
		logger.init();
		this.id = id;
	}
	
	// energy source switcher
	void switcher(Energy newEnergySource) throws energySwitchException {
		/*
		 * switch the current energy to new energySource
		 */
		this.currentEnergySource = newEnergySource;
	}
		
	// start supply
	void startSupply(){
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
	
	
	
	
	
	
		

}
