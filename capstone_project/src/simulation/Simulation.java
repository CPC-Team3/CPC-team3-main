package simulation;

import java.util.ArrayList;

import capstone.exception_handler.listeningException;
import system.ChargingSystem;
import vehicle.Car;

public class Simulation {
	static ArrayList<int[]> comChannel = new ArrayList<int[]>();

	public static void main(String[] args) throws listeningException, InterruptedException {
		// execute some car
		
		
		
		//execute the system
		ChargingSystem sys1 = new ChargingSystem("bookingFilePath", comChannel);
		sys1.start();
		System.out.println("simulationfinish");
		
	}

}

// car simulator

class carSimulator extends Thread{
	
	ArrayList<int[]> comChannel;
	int id =0;
	
	public carSimulator(ArrayList<int[]> comChannel) {
		this.comChannel = comChannel;
	}
	
	
	public void run(){
		while(true) {
			//spawn car randomly
		}
		
	}
	
	
	Car carSpawn(int id,ArrayList<int[]> comChannel) {
		
		return new Car(id, comChannel);
	}
	
	
	
	
}

