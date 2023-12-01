package system;

import capstone.exception_handler.assigningException;
import charging_station.Charger;
import charging_station.ChargingStation;

public class system {
	/*
	 *  combine everything as a system
	 */
	public ChargingStation station1 = new ChargingStation(0);
	
	public Charger charger1 = new Charger(0,0);
	public Charger charger2 = new Charger(1,0);
	public Charger charger3 = new Charger(2,0);
	public Charger charger4 = new Charger(3,0);
	
	
	
	
	public void init(){
		station1.chargers.add(charger1);
		station1.chargers.add(charger2);
		station1.chargers.add(charger3);
		station1.chargers.add(charger3);
				
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
