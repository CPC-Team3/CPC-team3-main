package simulation;


import capstone.exception_handler.InitException;
import capstone.exception_handler.assigningException;
import capstone.exception_handler.listeningException;
import charging_station.Charger;
import charging_station.ChargingStation;

public class Simulation {

	public static void main(String[] args) {
		ChargingStation station1 = new ChargingStation(0);
		try {
			station1.chargers.add(new Charger(0, 0));
		} catch (InitException e) {
			System.out.println("Error in adding new Charger: " + e.getMessage());
		}
		station1.chargers.get(0).id = 1;
		
		while(true) {
			try {
				station1.listening(null);
				station1.availability(0);
				station1.assigning(0, 0);
				
			}catch (listeningException e) {
	            System.out.println("Error in listening: " + e.getMessage());
	           
	            
	        }catch (assigningException e) {
	            System.out.println("Error in assigning: " + e.getMessage());
	            
	        }
			
			
		}

	}

}

