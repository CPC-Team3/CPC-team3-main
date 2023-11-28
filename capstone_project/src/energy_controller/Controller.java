package energy_controller;

import java.util.ArrayList;
import energy_controller.ExceptionHandler.InitException;
import energy_controller.ExceptionHandler.energySwitchException;

public class Controller {
	// Attributes	
	int id; // charging station id
	private ArrayList<Energy> EnergySource = new ArrayList<Energy>(); // list of energy source
	Log logger;
	Energy currentEnergySource;
	Boolean output = false;
	
	// Constructor
	Controller(int id){
		/*
		 * input : id of the controller
		 */
		try {
			logger = new Log("charger"+String.valueOf(this.id),"Charger "+ String.valueOf(this.id));
			logger.init();
			this.id = id;
		}catch(InitException e) {
			System.out.print("Unable to initialize Charging Station "+ String.valueOf(id));
		}
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
	
	void aremoveEnergySource(Energy unwantedEnergy) {
		this.EnergySource.remove(unwantedEnergy);
	}
	
	
	
	
	
	
		

}
