package simulation;

import capstone.exception_handler.listeningException;
import system.ChargingSystem;

public class Simulation {

	public static void main(String[] args) throws listeningException, InterruptedException {
		ChargingSystem system1 = new ChargingSystem("bookFilePath");
		system1.run();
		System.out.println("simulationfinish");
		
	}

}

