package simulation;

import java.util.ArrayList;

import capstone.exception_handler.listeningException;
import system.ChargingSystem;
import vehicle.Car;


public class Simulation {
	static ArrayList<int[]> comChannel = new ArrayList<int[]>();
	
	static Car carSpawn(int id) {
		return new Car(id, comChannel);
	}
	
	public static void run(){
		
		// wait certain time (to execute fake car by charging station)
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 30000) {
			
		}
		int id_ = 11;
		for (;id_<20; id_++) {
			carSpawn(id_);
			
		}
		while(true) {
			//spawn car randomly
		}
	}
	

	public static void main(String[] args) throws listeningException, InterruptedException {
		
		//execute the system
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", comChannel);
		sys1.start();
		System.out.println("simulationfinish");
		

		// execute some car
		run();
		
		
		
		
	}

}



